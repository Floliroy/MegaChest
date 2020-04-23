package personnages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import objets.Objet;
import objets.TypeStat;

public abstract class Personnage {
	
	////////////////
	// CONSTANTES //
	////////////////
	
	private final static int TAILLE_MAX_LISTE_OBJET = 3;
	
	///////////////
	// ATTRIBUTS //
	///////////////
	
	/** Le nom du personnage */
	private String nom;
	
	/** La sant� du personnage */
	private Integer vie;
	
	/** Le vie maximale du personnage */
	private Integer vieBase;
	
	/** Les points de d�placement du personnage */
	private Integer deplacements;
	
	/** Les d�g�ts du perssonage */
	private Integer degats;
	
	/** La port�e d'attaque du personnage */
	private Integer portee;
	
	/** La vitesse d'attaque du personnage */
	private Integer vitesse;
	
	/** L'element du personnage */
	private Element element;
	
	/** L'origine du personnage */
	private Origine origine;
	
	/** Les bonus en fonction de l'equipe du personnage, calculer grace a son element et son origine */
	private HashMap<TypeStat, Integer> bonusEquipe;
	
	/** Le chemin de l'image de l'icone du personnage */
	private String cheminImage;
	
	/** La liste d'objets �quip�s */
	private List<Objet> listObjets;
	
	//////////////////
	// CONSTRUCTEUR //
	//////////////////
	
	/**
	 * Constructeur permettant de construire un personnage
	 * @param nom Le nom souhait�
	 * @param vie La sant� souhait�e
	 * @param deplacements Les points de d�placements souhait�es
	 * @param degats Les d�g�ts souhait�s
	 * @param portee La port�e d'attaque souhait�e
	 * @param vitesse La vitesse d'attaque souhait�e
	 * @param element L'element du personnage
	 * @param origine L'origine du personnage
	 * @param cheminImage Le chemin de l'image de l'icone du personnage
	 */
	public Personnage(String nom, Integer vie, Integer deplacements, Integer degats, Integer portee, Integer vitesse, Element element, Origine origine, String cheminImage) {
		this.nom = nom;
		this.vie = vie;
		this.vieBase = vie;
		this.deplacements = deplacements;
		this.degats = degats;
		this.portee = portee;
		this.vitesse = vitesse;
		this.element = element;
		this.origine = origine;
		this.cheminImage = cheminImage;
		listObjets = new ArrayList<>();
		bonusEquipe = new HashMap<>();
		for(TypeStat stat : TypeStat.values()) {
			bonusEquipe.put(stat, 0);
		}
	}
	
	//////////////
	// METHODES //
	//////////////
	
	/**
	 * Permet d'attaquer un autre personnage
	 * @param adversaire Le personnage a attaquer
	 */
	public void attaque(Personnage adversaire) {
		adversaire.subirDegats(getDegatsAvecBoost());
	}
	
	/**
	 * Permet de recevoir des d�g�ts<br>
	 * Il est possible d'avoir moins de 0 de vie et d'etre encore en vie avec les bonus
	 * @param degatsRecus Le nombres de degats recus
	 */
	public void subirDegats(Integer degatsRecus) {
		degatsRecus = degatsRecus >= 0 ? degatsRecus : 0;
		vie -= degatsRecus;
	}
	
	/**
	 * Permet de savoir si le personnage est vivant ou non
	 * @return S'il est vivant
	 */
	public Boolean isVivant() {
		return getVieAvecBoost() > 0;
	}
	
	public void imprimeEtat() {
		if(isVivant()) {
			System.out.println("Il reste " + getVieAvecBoost() + " points de vie a " + nom);
		}else {
			System.out.println(nom + " est mort.");
		}
	}
	
	/**
	 * Permet de connaitre la valeur du boost d'une statistique
	 * @param typeStat Le type de statistique dont on veut connaitre le boost
	 * @return La valeur du boost de cette statistique
	 */
	private Integer getBoostEquipement(TypeStat typeStat) {
		Integer retour = 0;
		for(Objet objet : listObjets) {
			if(objet.getTypeStat() == typeStat) {
				retour += objet.getStat();
			}
		}
		return retour;
	}
	
	/**
	 * Permet de r�cup�rer la vie actuelle avec le boost de l'�quipement
	 * @return Renvoit la vie actuelle boost�e
	 */
	public Integer getVieAvecBoost() {
		return vie + getBoostEquipement(TypeStat.VIE) + bonusEquipe.get(TypeStat.VIE);
	}
	
	/**
	 * Permet de r�cup�rer la vie maximale avec le boost de l'�quipement
	 * @return Renvoit la vie maximale boost�e
	 */
	public Integer getVieBaseAvecBoost() {
		return vieBase + getBoostEquipement(TypeStat.VIE) + bonusEquipe.get(TypeStat.VIE);
	}
	
	/**
	 * Permet de r�cup�rer le nombre de points de d�placements avec le boost de l'�quipement
	 * @return Renvoit le d�placement boost�
	 */
	public Integer getDeplacementsAvecBoost() {
		return deplacements + getBoostEquipement(TypeStat.DEPLACEMENTS) + bonusEquipe.get(TypeStat.DEPLACEMENTS);
	}
	
	/**
	 * Permet de r�cup�rer le nombre de d�g�ts avec le boost de l'�quipement
	 * @return Renvoit les d�g�ts boost�s
	 */
	public Integer getDegatsAvecBoost() {
		return degats + getBoostEquipement(TypeStat.DEGATS) + bonusEquipe.get(TypeStat.DEGATS);
	}
	
	/**
	 * Permet de r�cup�rer la port�e avec le boost de l'�quipement
	 * @return Renvoit la port�e boost�e
	 */
	public Integer getPorteeAvecBoost() {
		return portee + getBoostEquipement(TypeStat.PORTEE) + bonusEquipe.get(TypeStat.PORTEE);
	}
	
	/**
	 * Permet de r�cup�rer la vitesse avec le boost de l'�quipement
	 * @return Renvoit la vitesse boost�e
	 */
	public Integer getVitesseAvecBoost() {
		return vitesse + getBoostEquipement(TypeStat.VITESSE) + bonusEquipe.get(TypeStat.VITESSE);
	}
	
	
	///////////////////////
	// GETTERS & SETTERS //
	///////////////////////
	
	/**
	 * Permet de conna�tre le nom du personnage
	 * @return Renvoit son nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Permet de renseigner le nom du personnage
	 * @param nom Nom du personnage souhait�
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Permet de conna�tre la sant� actuelle du personnage
	 * @return Renvoit sa vie
	 */
	public Integer getVie() {
		return vie;
	}

	/**
	 * Permet de renseigner la sant� du personnage
	 * @param vie Vie du personnage souhait�
	 */
	public void setVie(Integer vie) {
		this.vie = vie;
	}

	/**
	 * Permet de conna�tre la sant� maximale du personnage
	 * @return Renvoit sa vie de base
	 */
	public Integer getVieBase() {
		return vieBase;
	}

	/**
	 * Permet de conna�tre les points de d�placement du personnage
	 * @return Renvoit ses points de d�placements
	 */
	public Integer getDeplacements() {
		return deplacements;
	}

	/**
	 * Permet de renseigner les points de d�placements du personnage
	 * @param deplacements Le nombre de points de d�palcements souhait�s
	 */
	public void setDeplacements(Integer deplacements) {
		this.deplacements = deplacements;
	}

	/**
	 * Permet de conna�tre la force de frappe du personnage
	 * @return Renvoit ses d�g�ts
	 */
	public Integer getDegats() {
		return degats;
	}

	/**
	 * Permet de renseigner les d�g�ts du personnage
	 * @param degats Les d�g�ts du personnage souhait�s
	 */
	public void setDegats(Integer degats) {
		this.degats = degats;
	}

	/**
	 * Permet de conna�tre la port�e d'attaque du personnage
	 * @return Renvoit sa port�e
	 */
	public Integer getPortee() {
		return portee;
	}

	/**
	 * Permet de renseigner la port�e d'attaque du personnage
	 * @param portee La port�e du personnage souhait�e
	 */
	public void setPortee(Integer portee) {
		this.portee = portee;
	}

	/**
	 * Permet de conna�tre la vitesse d'attaque du personnage
	 * @return Renvoit sa vitesse
	 */
	public Integer getVitesse() {
		return vitesse;
	}

	/**
	 * Permet de renseigner la vitesse d'attaque du personnage
	 * @param vitesse La vitesse du personnage souhait�e
	 */
	public void setVitesse(Integer vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * Permet de conna�tre l'�l�ment du personnage
	 * @return Renvoit son �l�ment
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * Permet de renseigner l'�l�ment du personnage
	 * @param element L'�l�ment du personnage souhait�
	 */
	public void setElement(Element element) {
		this.element = element;
	}

	/**
	 * Permet de conna�tre l'origine du personnage
	 * @return Renvoit son origine
	 */
	public Origine getOrigine() {
		return origine;
	}

	/**
	 * Permet de renseigner l'origine du personnage
	 * @param element L'origine du personnage souhait�e
	 */
	public void setOrigine(Origine origine) {
		this.origine = origine;
	}

	/**
	 * TODO
	 * @return
	 */
	public HashMap<TypeStat, Integer> getBonusEquipe() {
		return bonusEquipe;
	}

	/**
	 * TODO
	 * @param bonusEquipe
	 */
	public void setBonusEquipe(HashMap<TypeStat, Integer> bonusEquipe) {
		this.bonusEquipe = bonusEquipe;
	}
	
	/**
	 * TODO
	 * @param typeStat
	 * @param value
	 */
	public void putBonusEquipe(TypeStat typeStat, Integer value) {
		bonusEquipe.replace(typeStat, bonusEquipe.get(typeStat) + value);
	}

	/**
	 * Permet de conna�tre le chemin de l'image de l'icone du personnage
	 * @return Renvoit le chemin de l'image
	 */
	public String getCheminImage() {
		return cheminImage;
	}

	/**
	 * Permet de renseigner le chemin de l'image de l'icone du personnage
	 * @param cheminImage Le chemin de l'image souhait�
	 */
	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}

	/**
	 * Permet de conna�tre la liste des objets �quip�s par le personnage
	 * @return Renvoit sa liste d'objets
	 */
	public List<Objet> getListObjets() {
		return listObjets;
	}

	/**
	 * Permet de renseigner la liste d'objets que le personnage � d'�quiper
	 * @param listObjets La liste d'objets � �quiper
	 */
	public void setListObjets(List<Objet> listObjets) {
		this.listObjets = listObjets;
	}
	
	/**
	 * Permet d'ajouter un objet � la liste des objets �quip�s
	 * @param objet
	 */
	public void addListObjets(Objet objet) {
		if(listObjets.size() < TAILLE_MAX_LISTE_OBJET) {
			listObjets.add(objet);
		}
	}
}
