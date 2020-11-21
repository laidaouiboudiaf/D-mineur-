package model;

public class Main {

	public static void main(String[] args) {

		Demineur demineur = new Demineur(10, 15);

		demineur.afficher();

		demineur.decouvrir(2, 3);

		demineur.afficher();
		
		demineur.init(20, 10);
	}

}
