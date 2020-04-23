package util;

import java.util.ArrayList;

import personnages.Personnage;
import plateau.Plateau;

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
	
	/**
	 * Renvoit la liste des personnages avec leurs coordonnées
	 * @param equipe La liste des personnages
	 * @param plateau Le plateau de jeu
	 * @return Renvoit la liste formatées
	 */
	public static String imprimeListPersoCoor(ArrayList<Personnage> equipe, Plateau plateau) {
		String retour = "";
		if(!equipe.isEmpty()) {
			for(Personnage perso : equipe) {
				retour += perso.getNom() + " " + plateau.getCase(perso).dumpCase() + ", ";
			}
			retour = retour.substring(0, retour.length() - 2);
		}
		return retour;
	}
	
}
