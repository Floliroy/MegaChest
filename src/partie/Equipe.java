package partie;

import java.util.ArrayList;
import java.util.HashMap;

import objets.TypeStat;
import personnages.Element;
import personnages.Origine;
import personnages.Personnage;

public class Equipe {
	
	/** TODO */
	private static final int TAILLE_EQUIPE = 1;
	
	/* -------------------------------------------------- */
	
	/** Liste des personnages composant une equipe */
	private ArrayList<Personnage> listePersonnages;
	
	/* -------------------------------------------------- */
	
	/**
	 * TODO
	 */
	public Equipe() {
		listePersonnages = new ArrayList<Personnage>();
	}
	
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
	
	private void increaseHashMapElement(HashMap<Element, Integer> map, Element element) {
		if(!map.entrySet().iterator().hasNext()) {
			map.put(element, 1);
		}else {
			map.replace(element, map.get(element) + 1);
		}
	}
	
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
	
	private void increaseHashMapOrigine(HashMap<Origine, Integer> map, Origine origine) {
		if(!map.entrySet().iterator().hasNext()) {
			map.put(origine, 1);
		}else {
			map.replace(origine, map.get(origine) + 1);
		}
	}
	
	/**
	 * TODO
	 * @return
	 */
	public ArrayList<Personnage> getListePersonnages() {
		return listePersonnages;
	}

	/**
	 * TODO
	 * @param equipe
	 */
	public void setListePersonnages(ArrayList<Personnage> listePersonnages) {
		this.listePersonnages = listePersonnages;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public boolean isComplete() {
		return listePersonnages.size() == TAILLE_EQUIPE;
	}
	
	public boolean isEmpty() {
		return listePersonnages.isEmpty();
	}
	
	/**
	 * 
	 * @param personnage
	 * @return
	 */
	public boolean isDansEquipe(Personnage personnage) {
		return listePersonnages.contains(personnage);
	}
	
	/**
	 * TODO
	 * @param personnage
	 */
	public void addEquipe(Personnage personnage) {
		if(!isComplete() && !isDansEquipe(personnage))
			listePersonnages.add(personnage);
	}
	
	/**
	 * TODO
	 * @param personnage
	 */
	public void removeEquipe(Personnage personnage) {
		listePersonnages.remove(personnage);
	}
	
	
}
