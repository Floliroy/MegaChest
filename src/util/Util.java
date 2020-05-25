package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import objets.ArcCourbe;
import objets.*;
import personnages.*;

import plateau.Case;


public class Util {
	
	/**
	 * Permet d'obtenir une ArrayList contenant tous les objets disponibles.
	 * 
	 * @return ArrayList<Objet> qui contient tous les objets
	 */
	public static ArrayList<Objet> listeObjet(){
		ArrayList<Objet> allObjets = new ArrayList<>();
		
		allObjets.add(new ArcCourbe());
		allObjets.add(new BFGlaive());
		allObjets.add(new Bottes());
		allObjets.add(new CeintureGeant());
		allObjets.add(new Zele());
		
		return allObjets;
	}
	
	/**
	 * Permet d'obtenir un Objet aléatoire parmis ceux existant
	 * 
	 * @return Un objet aléatoire
	 */
	public static Objet getRandomObjet() {
		
		ArrayList<Objet> allObjets = Util.listeObjet();
		Collections.shuffle(allObjets);
		return allObjets.get(0);
	}
	
	/**
	 * Permet d'obtenir une ArrayList contenant tous les personnages disponibles.
	 * 
	 * @return ArrayList<Personnage> qui contient tous les personnages
	 */
	public static ArrayList<Personnage> listePersonnages(){
		
		ArrayList<Personnage> allPersonnages = new ArrayList<>();
		
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
		allPersonnages.add(new Yasuo());
		
		Collections.shuffle(allPersonnages);
		return allPersonnages;
	}
	
	/**
	 * Calcule la distance entre une case de depart et une case d'arrivée sous la forme (X,Y).
	 * 
	 * @param depart case de depart
	 * @param positionXDestination colonne d'arrivée
	 * @param positionYDestination ligne d'arrivée
	 * 
	 * @return int distance entre les deux cases
	 */
	public static int distanceCase(Case depart, int positionXDestination, int positionYDestination) {
		return Math.abs(depart.getPositionX() - positionXDestination) + Math.abs(depart.getPositionY() - positionYDestination);
	}

	/**
	 * Ajoute la transparence jaune lors de la selection d'un personnage sur l'UI.
	 * 
	 * @return color transparence jaune
	 */
	public static HashMap<String, Integer> getYellowTransparency(){
		HashMap<String, Integer> color = new HashMap<>();
		color.put("red", 255);
		color.put("green", 255);
		color.put("blue", 0);
		color.put("alpha", 100);
		return color;
	}
	
	/**
	 * Ajoute la transparence verte sur un personnage
	 * 
	 * @return color transparence verte
	 */
	public static HashMap<String, Integer> getGreenTransparency(){
		HashMap<String, Integer> color = new HashMap<>();
		color.put("red", 0);
		color.put("green", 255);
		color.put("blue", 0);
		color.put("alpha", 100);
		return color;
	}
	
	/**
	 * Ajoute la transparence rouge sur un personnage
	 * 
	 * @return color transparence rouge
	 */
	public static HashMap<String, Integer> getRedTransparency(){
		HashMap<String, Integer> color = new HashMap<>();
		color.put("red", 255);
		color.put("green", 0);
		color.put("blue", 0);
		color.put("alpha", 100);
		return color;
	}
	
	/**
	 * Ajoute la transparence grise sur un personnage déjà choisis pendant la phase de sélection 
	 * des deux joueurs.
	 * 
	 * @return transparence grise
	 */
	public static HashMap<String, Integer> getGrayTransparency(){
		HashMap<String, Integer> color = new HashMap<>();
		color.put("red", 0);
		color.put("green", 0);
		color.put("blue", 0);
		color.put("alpha", 200);
		return color;
	}

}
