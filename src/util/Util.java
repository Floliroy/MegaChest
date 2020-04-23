package util;

import java.util.ArrayList;

import personnages.Personnage;

public class Util {
	
	public static String imprimeList(ArrayList<Personnage> liste) {
		String retour = "";
		for(Personnage perso : liste) {
			retour += perso.getNom() + ", ";
		}
		return retour.substring(0, retour.length() - 2);
	}
	
}
