package util;

import java.util.ArrayList;

import personnages.Personnage;

public class Util {
	
	/**
	 * Renvoit tous les elements d'une liste séparés par une virgule
	 * @param liste La liste que l'on souhaite imprimer
	 * @return Les elements séparés par une virgule
	 */
	public static String imprimeList(ArrayList<?> liste) {
		String retour = "";
		if(!liste.isEmpty()) {
			if(liste.get(0) instanceof Personnage) {
				for(Object element : liste) {
					Personnage perso = (Personnage) element;
					retour += perso.getNom() + ", ";
				}
			}
			retour = retour.substring(0, retour.length() - 2);
		}
		return retour;
	}
	
}
