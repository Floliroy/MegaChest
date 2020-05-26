package partie;

import java.util.ArrayList;
import java.util.HashMap;

import objets.TypeStat;
import personnages.Element;
import personnages.Origine;
import personnages.Personnage;

public class Equipe {
	
	/** Taille de l'équipe au début de la partie */
	public static final int TAILLE_EQUIPE = 6;
	
	/* -------------------------------------------------- */
	
	/** Liste des personnages composant une equipe */
	private ArrayList<Personnage> listePersonnages;
	
	/* -------------------------------------------------- */
	
	/**
	 * Constructeur pour initialiser l'equipe
	 */
	public Equipe() {
		listePersonnages = new ArrayList<Personnage>();
	}
	
	/**
	 * Methode permettant de calculer les bonus a affecter aux personnages en fonction des origines et elements de l'equipe.<br>
	 * Ionia -> PM ; Bilgewater -> PO ; Demacia -> PV ; Noxus -> Degats<br>
	 * Vent -> PM ; Eau -> PO ; Terre -> PV ; Feu -> Degats
	 */
	public void calculerBonusEquipe() {
		HashMap<Element, Integer> bonusElement = getNombreElement();
		HashMap<Origine, Integer> bonusOrigine = getNombreOrigine();
		
		for(Personnage personnage : listePersonnages) {
			switch(personnage.getElement()) {
			case FEU:
				if(bonusElement.get(Element.FEU) >= 4) {
					personnage.putBonusEquipe(TypeStat.DEGATS, 30);
				}else if(bonusElement.get(Element.FEU) >= 2) {
					personnage.putBonusEquipe(TypeStat.DEGATS, 15);
				}
				break;
			case EAU:
				if(bonusElement.get(Element.EAU) >= 4) {
					personnage.putBonusEquipe(TypeStat.PORTEE, 2);
				}else if(bonusElement.get(Element.EAU) >= 2) {
					personnage.putBonusEquipe(TypeStat.PORTEE, 1);
				}
				break;
			case TERRE:
				if(bonusElement.get(Element.TERRE) >= 4) {
					personnage.putBonusEquipe(TypeStat.VIE, 100);
				}else if(bonusElement.get(Element.TERRE) >= 2) {
					personnage.putBonusEquipe(TypeStat.VIE, 50);
				}
				break;
			case VENT:
				if(bonusElement.get(Element.VENT) >= 4) {
					personnage.putBonusEquipe(TypeStat.DEPLACEMENTS, 2);
				}else if(bonusElement.get(Element.VENT) >= 2) {
					personnage.putBonusEquipe(TypeStat.DEPLACEMENTS, 1);
				}
				break;
			}
			
			switch(personnage.getOrigine()) {
			case BILGEWATER:
				if(bonusOrigine.get(Origine.BILGEWATER) >= 4) {
					personnage.putBonusEquipe(TypeStat.PORTEE, 2);
				}else if(bonusOrigine.get(Origine.BILGEWATER) >= 2) {
					personnage.putBonusEquipe(TypeStat.PORTEE, 1);
				}
				break;
			case DEMACIA:
				if(bonusOrigine.get(Origine.DEMACIA) >= 4) {
					personnage.putBonusEquipe(TypeStat.VIE, 100);
				}else if(bonusOrigine.get(Origine.DEMACIA) >= 2) {
					personnage.putBonusEquipe(TypeStat.VIE, 50);
				}
				break;
			case NOXUS:
				if(bonusOrigine.get(Origine.NOXUS) >= 4) {
					personnage.putBonusEquipe(TypeStat.DEGATS, 30);
				}else if(bonusOrigine.get(Origine.NOXUS) >= 2) {
					personnage.putBonusEquipe(TypeStat.DEGATS, 15);
				}
				break;
			case IONIA:
				if(bonusOrigine.get(Origine.IONIA) >= 4) {
					personnage.putBonusEquipe(TypeStat.DEPLACEMENTS, 2);
				}else if(bonusOrigine.get(Origine.IONIA) >= 2) {
					personnage.putBonusEquipe(TypeStat.DEPLACEMENTS, 1);
				}
				break;
			}
		}			
	}
	
	/**
	 * Permet de savoir combien de personnage de chaque element il y a dans l'equipe
	 * @return HashMap comportant le nombre de personnage par element
	 */
	private HashMap<Element, Integer> getNombreElement() {
		HashMap<Element, Integer> map = new HashMap<>();
		
		for(Personnage personnage : listePersonnages) {
			switch(personnage.getElement()) {
			case FEU:
				increaseHashMapElement(map, Element.FEU);
				break;
			case EAU:
				increaseHashMapElement(map, Element.EAU);
				break;
			case TERRE:
				increaseHashMapElement(map, Element.TERRE);
				break;
			case VENT:
				increaseHashMapElement(map, Element.VENT);
				break;
			}
		}
		
		return map;
	}
	
	/**
	 * Augmente de 1 le nombre de personnage d'un element donné<br>
	 * Initialise la valeur si l'element n'est pas connu de la map
	 * @param map La HashMap ou sont stockés le nombre de personnage par element
	 * @param element L'element dont l'on souhaite augmenté le nombre de personnage
	 */
	private void increaseHashMapElement(HashMap<Element, Integer> map, Element element) {
		if(!map.containsKey(element)) {
			map.put(element, 1);
		}else {
			map.replace(element, map.get(element) + 1);
		}
	}
	
	/**
	 * Permet de savoir combien de personnage de chaque origine il y a dans l'equipe
	 * @return HashMap comportant le nombre de personnage par origine
	 */
	private HashMap<Origine, Integer> getNombreOrigine() {
		HashMap<Origine, Integer> map = new HashMap<>();
		
		for(Personnage personnage : listePersonnages) {
			switch(personnage.getOrigine()) {
			case BILGEWATER:
				increaseHashMapOrigine(map, Origine.BILGEWATER);
				break;
			case DEMACIA:
				increaseHashMapOrigine(map, Origine.DEMACIA);
				break;
			case NOXUS:
				increaseHashMapOrigine(map, Origine.NOXUS);
				break;
			case IONIA:
				increaseHashMapOrigine(map, Origine.IONIA);
				break;
			}
		}
		
		return map;
	}
	
	/**
	 * Augmente de 1 le nombre de personnage d'une origine donnée<br>
	 * Initialise la valeur si l'origine n'est pas connu de la map
	 * @param map La HashMap ou sont stockés le nombre de personnage par origine
	 * @param origine L'origine dont l'on souhaite augmenté le nombre de personnage
	 */
	private void increaseHashMapOrigine(HashMap<Origine, Integer> map, Origine origine) {
		if(!map.containsKey(origine)) {
			map.put(origine, 1);
		}else {
			map.replace(origine, map.get(origine) + 1);
		}
	}
	
	/**
	 * Permet de savoir si l'equipe est complete ou non
	 * @return true si complete sinon false
	 */
	public boolean isComplete() {
		return listePersonnages.size() == TAILLE_EQUIPE;
	}
	
	/**
	 * Permet de savoir si l'equipe ne contient aucun personnage
	 * @return true si aucun personnage sinon false
	 */
	public boolean isEmpty() {
		return listePersonnages.isEmpty();
	}
	
	/**
	 * Permet de savoir si un personnage donné est présent dans l'équipe
	 * @param personnage Le personnage dont on veut savoir s'il est présent
	 * @return true s'il est présent sinon false
	 */
	public boolean isDansEquipe(Personnage personnage) {
		return listePersonnages.contains(personnage);
	}
	
	/**
	 * Getter sur la liste des personnages de l'équipe
	 * @return Les personnages dans l'équipe
	 */
	public ArrayList<Personnage> getListePersonnages() {
		return listePersonnages;
	}

	/**
	 * Permet d'ajouter un personnage à l'équipe
	 * @param personnage Le personnage a ajouter
	 */
	public void addEquipe(Personnage personnage) {
		if(!isComplete() && !isDansEquipe(personnage))
			listePersonnages.add(personnage);
	}
	
	/**
	 * Permet de retirer un personnage de l'équipe
	 * @param personnage Le personnage à retirer de l'équipe
	 */
	public void removeEquipe(Personnage personnage) {
		listePersonnages.remove(personnage);
	}

}
