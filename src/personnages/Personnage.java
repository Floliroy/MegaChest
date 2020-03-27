package personnages;

import java.util.ArrayList;
import java.util.List;

public class Personnage {
	
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
	
	/** Les dégâts du perssonage */
	private Integer degats;
	
	/** La portée d'attaque du personnage */
	private Integer portee;
	
	/** La vitesse d'attaque du personnage */
	private Integer vitesse;
	
	/** La liste d'objets équipés */
	private List<String> listObjets; //TODO: Remplacer String par la classe Objet
	
	//////////////////
	// CONSTRUCTEUR //
	//////////////////
	
	/**
	 * Constructeur permettant de construire un personnage
	 * @param nom Le nom souhaité
	 * @param vie La santé souhaitée
	 * @param degats Les dégâts souhaités
	 * @param portee La portée d'attaque souhaitée
	 * @param vitesse La vitesse d'attaque souhaitée
	 */
	public Personnage(String nom, Integer vie, Integer degats, Integer portee, Integer vitesse) {
		this.nom = nom;
		this.vie = vie;
		this.degats = degats;
		this.portee = portee;
		this.vitesse = vitesse;
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
		if(listObjets.size() <= TAILLE_MAX_LISTE_OBJET) {
			listObjets.add(objet);
		}
	}
}
