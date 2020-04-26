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
	
	/** Les points de déplacement du personnage */
	private Integer deplacements;
	
	/** Les points de déplacement de base du personnage */
	private Integer deplacementsBase;
	
	/** Les dégâts du perssonage */
	private Integer degats;
	
	/** La portée d'attaque du personnage */
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
	
	/** La liste d'objets équipés */
	private List<Objet> listObjets;
	
	//////////////////
	// CONSTRUCTEUR //
	//////////////////
	
	/**
	 * Constructeur permettant de construire un personnage
	 * @param nom Le nom souhaité
	 * @param vie La santé souhaitée
	 * @param deplacements Les points déplacements souhaitées
	 * @param degats Les dégâts souhaités
	 * @param portee La portée d'attaque souhaitée
	 * @param vitesse La vitesse d'attaque souhaitée
	 * @param element L'element du personnage
	 * @param origine L'origine du personnage
	 * @param cheminImage Le chemin de l'image de l'icone du personnage
	 */
	public Personnage(String nom, Integer vie, Integer deplacements, Integer degats, Integer portee, Integer vitesse, Element element, Origine origine, String cheminImage) {
		this.nom = nom;
		this.vie = vie;
		this.vieBase = vie;
		this.deplacements = deplacements;
		this.deplacementsBase = deplacements;
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
	 * Permet de recevoir des dégâts<br>
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
	 * Permet de récupérer la vie actuelle avec le boost de l'équipement
	 * @return Renvoit la vie actuelle boostée
	 */
	public Integer getVieAvecBoost() {
		return vie + getBoostEquipement(TypeStat.VIE) + bonusEquipe.get(TypeStat.VIE);
	}
	
	/**
	 * Permet de récupérer la vie maximale avec le boost de l'équipement
	 * @return Renvoit la vie maximale boostée
	 */
	public Integer getVieBaseAvecBoost() {
		return vieBase + getBoostEquipement(TypeStat.VIE) + bonusEquipe.get(TypeStat.VIE);
	}
	
	/**
	 * Permet de récupérer le nombre de points de déplacements avec le boost de l'équipement
	 * @return Renvoit le déplacement boosté
	 */
	public Integer getDeplacementsAvecBoost() {
		return deplacements + getBoostEquipement(TypeStat.DEPLACEMENTS) + bonusEquipe.get(TypeStat.DEPLACEMENTS);
	}
	
	/**
	 * Permet de récupérer le nombre de points de déplacements de base avec le boost de l'équipement
	 * @return Renvoit le déplacement boosté
	 */
	public Integer getDeplacementsBaseAvecBoost() {
		return deplacementsBase + getBoostEquipement(TypeStat.DEPLACEMENTS) + bonusEquipe.get(TypeStat.DEPLACEMENTS);
	}
	
	/**
	 * Permet de récupérer le nombre de dégâts avec le boost de l'équipement
	 * @return Renvoit les dégâts boostés
	 */
	public Integer getDegatsAvecBoost() {
		return degats + getBoostEquipement(TypeStat.DEGATS) + bonusEquipe.get(TypeStat.DEGATS);
	}
	
	/**
	 * Permet de récupérer la port�e avec le boost de l'�quipement
	 * @return Renvoit la port�e boostée
	 */
	public Integer getPorteeAvecBoost() {
		return portee + getBoostEquipement(TypeStat.PORTEE) + bonusEquipe.get(TypeStat.PORTEE);
	}
	
	/**
	 * Permet de récupérer la vitesse avec le boost de l'�quipement
	 * @return Renvoit la vitesse boostée
	 */
	public Integer getVitesseAvecBoost() {
		return vitesse + getBoostEquipement(TypeStat.VITESSE) + bonusEquipe.get(TypeStat.VITESSE);
	}
	
	
	///////////////////////
	// GETTERS & SETTERS //
	///////////////////////
	
	/**
	 * Permet de connaître le nom du personnage
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
	 * Permet de connaître la santé actuelle du personnage
	 * @return Renvoit sa vie
	 */
	public Integer getVie() {
		return vie;
	}

	/**
	 * Permet de renseigner la santé du personnage
	 * @param vie Vie du personnage souhait�
	 */
	public void setVie(Integer vie) {
		this.vie = vie;
	}

	/**
	 * Permet de connaître la santé maximale du personnage
	 * @return Renvoit sa vie de base
	 */
	public Integer getVieBase() {
		return vieBase;
	}

	/**
	 * Permet de connaître les points de déplacements du personnage
	 * @return Renvoit ses points de déplacements
	 */
	public Integer getDeplacements() {
		return deplacements;
	}

	/**
	 * Permet de renseigner les points de déplacements du personnage
	 * @param deplacements Le nombre de points de déplacements souhaités
	 */
	public void setDeplacements(Integer deplacements) {
		this.deplacements = deplacements;
	}

	/**
	 * Permet de connaître les points de déplacements de base du personnage
	 * @return Renvoit ses points de déplacements de base 
	 */
	public Integer getDeplacementsBase() {
		return deplacementsBase;
	}

	/**
	 * Permet de renseigner les points de déplacements de base du personnage
	 * @param deplacements Le nombre de points de déplacements de base souhaités
	 */
	public void setDeplacementsBase(Integer deplacementsBase) {
		this.deplacementsBase = deplacementsBase;
	}

	/**
	 * Permet de connaître la force de frappe du personnage
	 * @return Renvoit ses dégâts
	 */
	public Integer getDegats() {
		return degats;
	}

	/**
	 * Permet de renseigner les dégâts du personnage
	 * @param degats Les dégâts du personnage souhaités
	 */
	public void setDegats(Integer degats) {
		this.degats = degats;
	}

	/**
	 * Permet de connaître la portée d'attaque du personnage
	 * @return Renvoit sa portée
	 */
	public Integer getPortee() {
		return portee;
	}

	/**
	 * Permet de renseigner la portée d'attaque du personnage
	 * @param portee La portée du personnage souhaitée
	 */
	public void setPortee(Integer portee) {
		this.portee = portee;
	}

	/**
	 * Permet de connaître la vitesse d'attaque du personnage
	 * @return Renvoit sa vitesse
	 */
	public Integer getVitesse() {
		return vitesse;
	}

	/**
	 * Permet de renseigner la vitesse d'attaque du personnage
	 * @param vitesse La vitesse du personnage souhaitée
	 */
	public void setVitesse(Integer vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * Permet de connaître l'élément du personnage
	 * @return Renvoit son élément
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
	 * Permet de connaître l'origine du personnage
	 * @return Renvoit son origine
	 */
	public Origine getOrigine() {
		return origine;
	}

	/**
	 * Permet de renseigner l'origine du personnage
	 * @param element L'origine du personnage souhaitée
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
	 * Permet de connaître le chemin de l'image de l'icone du personnage
	 * @return Renvoit le chemin de l'image
	 */
	public String getCheminImage() {
		return cheminImage;
	}

	/**
	 * Permet de renseigner le chemin de l'image de l'icone du personnage
	 * @param cheminImage Le chemin de l'image souhaité
	 */
	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}

	/**
	 * Permet de connaître la liste des objets équipés par le personnage
	 * @return Renvoit sa liste d'objets
	 */
	public List<Objet> getListObjets() {
		return listObjets;
	}

	/**
	 * Permet de renseigner la liste d'objets que le personnage à d'équiper
	 * @param listObjets La liste d'objets à équiper
	 */
	public void setListObjets(List<Objet> listObjets) {
		this.listObjets = listObjets;
	}
	
	/**
	 * Permet d'ajouter un objet à la liste des objets équipés
	 * @param objet
	 */
	public void addListObjets(Objet objet) {
		if(listObjets.size() < TAILLE_MAX_LISTE_OBJET) {
			listObjets.add(objet);
		}
	}
	
	public String dumpCaracteristique() {
		String print;
		print = "PV : " + vieBase + /*"\n"*/ " | ";
		print += "PM : " + deplacements + /*"\n"*/ " | ";
		print += "Deg : " + degats + /*"\n"*/ " | ";
		print += "PO : " + portee + /*"\n"*/ " | ";
		print += "Vit : " + vitesse + "";	
		return print;
	}
}
