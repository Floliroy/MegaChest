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
	
	/** La sant� du personnage */
	private Integer vie;
	
	/** Les d�g�ts du perssonage */
	private Integer degats;
	
	/** La port�e d'attaque du personnage */
	private Integer portee;
	
	/** La vitesse d'attaque du personnage */
	private Integer vitesse;
	
	/** La liste d'objets �quip�s */
	private List<String> listObjets; //TODO: Remplacer String par la classe Objet
	
	//////////////////
	// CONSTRUCTEUR //
	//////////////////
	
	/**
	 * Constructeur permettant de construire un personnage
	 * @param nom Le nom souhait�
	 * @param vie La sant� souhait�e
	 * @param degats Les d�g�ts souhait�s
	 * @param portee La port�e d'attaque souhait�e
	 * @param vitesse La vitesse d'attaque souhait�e
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
	 * Permet de conna�tre la liste des objets �quip�s par le personnage
	 * @return Renvoit sa liste d'objets
	 */
	public List<String> getListObjets() { //TODO: Remplacer String par la classe Objet
		return listObjets;
	}

	/**
	 * Permet de renseigner la liste d'objets que le personnage � d'�quiper
	 * @param listObjets La liste d'objets � �quiper
	 */
	public void setListObjets(List<String> listObjets) { //TODO: Remplacer String par la classe Objet
		this.listObjets = listObjets;
	}
	
	/**
	 * Permet d'ajouter un objet � la liste des objets �quip�s
	 * @param objet
	 */
	public void addListObjets(String objet) { //TODO: Remplacer String par la classe Objet
		if(listObjets.size() <= TAILLE_MAX_LISTE_OBJET) {
			listObjets.add(objet);
		}
	}
}
