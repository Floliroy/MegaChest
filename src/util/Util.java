package util;

import java.util.ArrayList;

<<<<<<< Upstream, based on branch 'dev' of https://github.com/Floliroy/MegaChest.git
import personnages.Personnage;
import plateau.Plateau;
=======
import personnages.*;
import plateau.Case;
>>>>>>> 98d77ee Ajout du calcule de la distance entre deux case + listePersonnages

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
	
<<<<<<< Upstream, based on branch 'dev' of https://github.com/Floliroy/MegaChest.git
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
	
=======
	public static ArrayList<Personnage> listePersonnages(){
		
		ArrayList<Personnage> allPersonnages = new ArrayList<Personnage>();
		
		allPersonnages.add(new Ahri());
		allPersonnages.add(new Fizz());
		allPersonnages.add(new Illaoi());
		allPersonnages.add(new Jarvan());
		allPersonnages.add(new Kayle());
		allPersonnages.add(new Kled());
		allPersonnages.add(new Poppy());
		allPersonnages.add(new Pyke());
		allPersonnages.add(new Quinn());
		allPersonnages.add(new Shyvana());
		allPersonnages.add(new Sion());
		allPersonnages.add(new Sona());
		allPersonnages.add(new Swain());
		allPersonnages.add(new Talon());
		allPersonnages.add(new Wukong());
		
		return allPersonnages;
	}
	
	public static int distanceCase(Case depart, int positionXDestination, int positionYDestination) {
		return Math.abs(depart.getPositionX() - positionXDestination) + Math.abs(depart.getPositionY() - positionYDestination);
	}
>>>>>>> 98d77ee Ajout du calcule de la distance entre deux case + listePersonnages
}
