package test;

import java.util.ArrayList;

import plateau.Case;
import plateau.Plateau;
import personnages.Pyke;
import personnages.Fizz;
import personnages.Personnage;

public class TestPlateau {

	public static void main(String []args) {
		
		Plateau plateau = new Plateau();
		
		System.out.println("\tCreation du personnage Pyke\n");
		Pyke pyke = new Pyke();
		System.out.println("PM = " + pyke.getDeplacementsAvecBoost() + " | Porte = " + pyke.getPorteeAvecBoost());
		int positionX = 0;
		int positionY = 0;
		System.out.println(pyke.getNom() + " est positione sur la case [" + positionX + "," + positionY + "]");
		plateau.getCase(positionX, positionY).setPersonnage(pyke);
		System.out.println("-------------------------------------------");
		
		
		System.out.println("\tCase atteignable par " + pyke.getNom() + ":\n");
		ArrayList<Case> listeCasesAtteignables = plateau.getCasesAtteignables(pyke);
		System.out.println("Nombre de cases atteignables par " + pyke.getNom() + " : " + listeCasesAtteignables.size());
		if(listeCasesAtteignables.size() > 0)
			for(Case atteignable : listeCasesAtteignables)
				System.out.print("[" + atteignable.getPositionX() + "," + atteignable.getPositionY() + "]");
		System.out.println("\n-------------------------------------------");
		
		System.out.println("\tCase attaquable par " + pyke.getNom() + ":\n");
		ArrayList<Case> listeCasesAttaquables = plateau.getCasesAPorte(pyke);
		System.out.println("Nombre de cases attaquables par " + pyke.getNom() + " : " + listeCasesAttaquables.size());
		if(listeCasesAttaquables.size() > 0)
			for(Case attaquables : listeCasesAttaquables)
				System.out.print("[" + attaquables.getPositionX() + "," + attaquables.getPositionY() + "]");
		System.out.println("-------------------------------------------");
		
		System.out.println("\tCreation du personnage Fizz");
		Fizz fizz  = new Fizz();
	}
}
