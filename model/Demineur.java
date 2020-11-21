package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import view.Position;

public class Demineur {
	private int nbLignes;
	private int nbColonnes;
	private boolean bombe[][];
	private boolean visible[][];

	public Demineur(int nbLignes, int nbColonnes) {
		init(nbLignes, nbColonnes);
	}

	public void init(int nbLignes, int nbColonnes) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.bombe = new boolean[nbLignes][nbColonnes];
		this.visible = new boolean[nbLignes][nbColonnes];
		initBombe();
	}

	private void initBombe() {
		int nbBombes = (int) (nbCellules() * 0.2);
		Random random = new Random();
		do {

			int ligne = random.nextInt(getNbLignes());
			int colonne = random.nextInt(getNbColonnes());

			if (!bombe[ligne][colonne]) {
				bombe[ligne][colonne] = true;
				nbBombes--;
			}

		} while (nbBombes > 0);
	}

	public int nbCellules() {
		return getNbLignes() * getNbColonnes();
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public void afficher() {
		System.out.println("Demineur");
		for (int ligne = 0; ligne < getNbLignes(); ligne++)
			afficherLigne(ligne);
	}

	public void afficherLigne(int ligne) {
		for (int colonne = 0; colonne < getNbColonnes(); colonne++)
			afficherCellule(ligne, colonne);
		System.out.println();
	}

	public void afficherCellule(int ligne, int colonne) {
		if (!visible[ligne][colonne])
			System.out.print("-");
		else if (bombe[ligne][colonne])
			System.out.print("B");
		else
			System.out.print(nbBombes(ligne, colonne));
	}

	public List<Position> cellVoisines(Position position) {
		ArrayList<Position> res = new ArrayList<>();

		for (int l = position.l - 1; l <= position.l + 1; l++)
			for (int c = position.c - 1; c <= position.c + 1; c++) {
				Position p = new Position(l, c);

				if (p.equals(position))
					continue;
				
				if(!valide(p))
					continue;
				
				res.add(p);
			}

		return res;

	}

	public int nbBombes(int ligne, int colonne) {
		int res = 0;

		for (Position position : cellVoisines(new Position(ligne, colonne)))
			if (bombe(position))
				res++;

		return res;
	}

	private boolean bombe(Position position) {
		return bombe(position.l, position.c);
	}

	public void decouvrir(int ligne, int colonne) {
		if (visible(ligne, colonne))
			return;

		visible[ligne][colonne] = true;

		for(Position p : cellVoisines(new Position(ligne, colonne)))
			decouvrir2(p);
	}
	
	public void decouvrir2(Position p) {	
		if (visible(p))
			return;
		
		if (bombe(p))
			return;
		
		if (nbBombes(p.l, p.c) != 0)
			return;
		
		visible[p.l][p.c] = true;
		
		for(Position p2 : cellVoisines(p))
			decouvrir2(p2);
	}
	
	

	private boolean visible(Position p) {
		return visible(p.l,p.c);
	}

	public boolean visible(int ligne, int colonne) {
		return visible[ligne][colonne];
	}

	public boolean bombe(int ligne, int colonne) {
		return bombe[ligne][colonne];
	}

	public boolean valide(Position position) {
		return position.l >= 0 && position.l < getNbLignes() && position.c >= 0 && position.c < getNbColonnes();
	}

	public void decouvrir(Position position) {
		decouvrir(position.l, position.c);

	}
}
