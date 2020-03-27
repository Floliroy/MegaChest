package personnages;

import java.util.ArrayList;
import java.util.List;

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
	
	/** La santé du personnage */
	private Integer vie;
	
	/** Les points de déplacement du personnage */
	private Integer deplacements;
	
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
	
	/** Le chemin de l'image de l'icone du personnage */
	private String cheminImage;
	
	/** La liste d'objets équipés */
	private List<String> listObjets; //TODO: Remplacer String par la classe Objet
	
	//////////////////
	// CONSTRUCTEUR //
	//////////////////
	
	/**
	 * Constructeur permettant de construire un personnage
	 * @param nom Le nom souhaité
	 * @param vie La santé souhaitée
	 * @param deplacements Les points de déplacements souhaitées
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
		this.deplacements = deplacements;
		this.degats = degats;
		this.portee = portee;
		this.vitesse = vitesse;
		this.element = element;
		this.origine = origine;
		this.cheminImage = cheminImage;
		listObjets = new ArrayList<>();
	}
	
	//////////////
	// METHODES //
	//////////////
	
	/* Empty for now ... */
	
	
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
	 * @param nom Nom du personnage souhaité
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
	 * @param vie Vie du personnage souhaité
	 */
	public void setVie(Integer vie) {
		this.vie = vie;
	}

	/**
	 * Permet de connaître les points de déplacement du personnage
	 * @return Renvoit ses points de déplacements
	 */
	public Integer getDeplacements() {
		return deplacements;
	}

	/**
	 * Permet de renseigner les points de déplacements du personnage
	 * @param deplacements Le nombre de points de dépalcements souhaités
	 */
	public void setDeplacements(Integer deplacements) {
		this.deplacements = deplacements;
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
	 * Permet de renseigner l'élément du personnage
	 * @param element L'élément du personnage souhaité
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
	public List<String> getListObjets() { //TODO: Remplacer String par la classe Objet
		return listObjets;
	}

	/**
	 * Permet de renseigner la liste d'objets que le personnage à d'équiper
	 * @param listObjets La liste d'objets à équiper
	 */
	public void setListObjets(List<String> listObjets) { //TODO: Remplacer String par la classe Objet
		this.listObjets = listObjets;
	}
	
	/**
	 * Permet d'ajouter un objet à la liste des objets équipés
	 * @param objet
	 */
	public void addListObjets(String objet) { //TODO: Remplacer String par la classe Objet
		if(listObjets.size() < TAILLE_MAX_LISTE_OBJET) {
			listObjets.add(objet);
		}
	}
}
