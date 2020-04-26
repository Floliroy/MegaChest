package test;

import personnages.Fizz;
import personnages.Personnage;
import personnages.Pyke;
import plateau.Plateau;

public class TestPlateau {

	public static void main(String []args) {
		
		Plateau plateau = new Plateau();
		Personnage fizz = new Fizz();
		Personnage pyke = new Pyke();
		
		plateau.placerPersonnage(1, 2, fizz);
		plateau.placerPersonnage(4, 3, pyke);
		plateau.afficherPlateau();
		
	}
}
