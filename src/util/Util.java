package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import plateau.Plateau;
import personnages.*;
import plateau.Case;


public class Util {
	
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
		allPersonnages.add(new Yasuo());
		
		Collections.shuffle(allPersonnages);
		return allPersonnages;
	}
	
	public static int distanceCase(Case depart, int positionXDestination, int positionYDestination) {
		return Math.abs(depart.getPositionX() - positionXDestination) + Math.abs(depart.getPositionY() - positionYDestination);
	}
	
	public static HashMap<String, Integer> getYellowTransparency(){
		HashMap<String, Integer> color = new HashMap<>();
		color.put("red", 255);
		color.put("green", 255);
		color.put("blue", 0);
		color.put("alpha", 100);
		return color;
	}
	
	public static HashMap<String, Integer> getGrayTransparency(){
		HashMap<String, Integer> color = new HashMap<>();
		color.put("red", 0);
		color.put("green", 0);
		color.put("blue", 0);
		color.put("alpha", 200);
		return color;
	}

}
