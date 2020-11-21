package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import model.Demineur;

public class DemineurPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final DemineurFrame frame;

	public DemineurPanel(DemineurFrame frame) {
		this.frame = frame;
		setBackground(Color.PINK);

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				System.out.println(" x = " + e.getX() +  " y " + e.getY());
			
				Position position = getCellPosition(new Point(e.getX(), e.getY()));
				
				if (position == null)
					return;
				
				getDemineur().decouvrir(position);
				repaint();
			}
		});

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		afficher(g);
	}

	public void afficher(Graphics g) {
		for (int ligne = 0; ligne < getDemineur().getNbLignes(); ligne++)
			afficherLigne(g, ligne);
	}

	public void afficherLigne(Graphics g, int ligne) {
		for (int colonne = 0; colonne < getDemineur().getNbColonnes(); colonne++)
			afficherCellule(g, ligne, colonne);
	}

	public void afficherCellule(Graphics g, int ligne, int colonne) {
		Point cellPoint = getCellPoint(new Position(ligne, colonne));

		Color color = Color.WHITE;

		if (!getDemineur().visible(ligne, colonne))
			color = Color.YELLOW;
		else if (getDemineur().bombe(ligne, colonne))
			color = Color.BLACK;
		else
			color = Color.GRAY;

		g.setColor(color);

		g.fillRect(cellPoint.x, cellPoint.y, getCellWidth(), getCellHeight());
		
		g.setColor(Color.BLUE);
		g.drawRect(cellPoint.x, cellPoint.y, getCellWidth(), getCellHeight());

		if (getDemineur().visible(ligne, colonne) && !getDemineur().bombe(ligne, colonne)) {
			g.setColor(Color.BLACK);
			g.drawString("" + getDemineur().nbBombes(ligne, colonne), cellPoint.x + getCellWidth()/2, cellPoint.y + getCellHeight()/2);
		}
		
		
	}

	public Demineur getDemineur() {
		return frame.getDemineur();
	}

	public int getCellHeight() {
		return getHeight() / (getDemineur().getNbLignes() + 2);
	}

	public int getCellWidth() {
		return getWidth() / (getDemineur().getNbColonnes() + 2);
	}

	public Point getCellPoint(Position position) {
		return new Point((position.c + 1) * getCellWidth(), (position.l + 1) * getCellHeight());
	}
	
	private Position getCellPosition(Point point) {
		Position position = new Position((point.y / getCellHeight()) - 1, (point.x / getCellWidth()) - 1);
		return getDemineur().valide(position) ? position : null;
	}

}
