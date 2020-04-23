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
		ArrayList<Case> listeCasesAtteignablesPyke = plateau.getCasesAtteignables(pyke);
		System.out.println("Nombre de cases atteignables par " + pyke.getNom() + " : " + listeCasesAtteignablesPyke.size());
		if(listeCasesAtteignablesPyke.size() > 0)
			for(Case atteignable : listeCasesAtteignablesPyke)
				System.out.print("[" + atteignable.getPositionX() + "," + atteignable.getPositionY() + "]");
		System.out.println("\n-------------------------------------------");
		
		System.out.println("\tCase attaquable par " + pyke.getNom() + ":\n");
		ArrayList<Case> listeCasesAttaquablesPyke = plateau.getCasesAPorte(pyke);
		System.out.println("Nombre de cases attaquables par " + pyke.getNom() + " : " + listeCasesAttaquablesPyke.size());
		if(listeCasesAttaquablesPyke.size() > 0)
			for(Case attaquables : listeCasesAttaquablesPyke)
				System.out.print("[" + attaquables.getPositionX() + "," + attaquables.getPositionY() + "]");
		System.out.println("-------------------------------------------");
		
		
		System.out.println("\n\tCreation du personnage Fizz");
		Fizz fizz  = new Fizz();
		System.out.println("PM = " + fizz.getDeplacementsAvecBoost() + " | Porte = " + fizz.getPorteeAvecBoost());
		positionX = 0;
		positionY = 2;
		System.out.println(fizz.getNom() + " est positione sur la case [" + positionX + "," + positionY + "]");
		plateau.getCase(positionX, positionY).setPersonnage(fizz);
		System.out.println("-------------------------------------------");
		
		System.out.println("\tCase atteignable par " + fizz.getNom() + ":\n");
		ArrayList<Case> listeCasesAtteignablesFizz = plateau.getCasesAtteignables(fizz);
		System.out.println("Nombre de cases atteignables par " + fizz.getNom() + " : " + listeCasesAtteignablesFizz.size());
		if(listeCasesAtteignablesFizz.size() > 0)
			for(Case atteignable : listeCasesAtteignablesFizz)
				System.out.print("[" + atteignable.getPositionX() + "," + atteignable.getPositionY() + "]");
		System.out.println("\n-------------------------------------------");
		
		System.out.println("\tCase attaquable par " + fizz.getNom() + ":\n");
		ArrayList<Case> listeCasesAttaquablesFizz = plateau.getCasesAPorte(fizz);
		System.out.println("Nombre de cases attaquables par " + fizz.getNom() + " : " + listeCasesAttaquablesFizz.size());
		if(listeCasesAttaquablesFizz.size() > 0)
			for(Case attaquables : listeCasesAttaquablesFizz)
				System.out.print("[" + attaquables.getPositionX() + "," + attaquables.getPositionY() + "]");
		
		System.out.println("\n");
		plateau.afficherPlateau();
		
	}
}
