package partie;


import com.google.gson.annotations.Expose;



public class Joueur {
	
	/** L'equipe du joueur */
	private Equipe equipe;
	/** booleen indiquant si le joueur a la main */
	@Expose
	private Boolean tour;
	/** couleur du joueur */
	@Expose
	private String couleur;
	/** nom du joueur */
	@Expose
	private String nom;
	
	/**
	 * Constructeur de Joueur initialisant son équipe 
	 * @param tour Si le joueur commence ou non
	 * @param couleur La couleur du joueur
	 */
	public Joueur(Boolean tour, String couleur) {
		this.equipe = new Equipe();
		this.tour = tour;
		this.couleur = couleur;
	}	

	///////////////////////
	// GETTERS & SETTERS //
	///////////////////////
	
	/**
	 * Getter de l'équipe du joueur
	 * @return L'équipe du joueur
	 */
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	/**
	 * Permet de savoir si le joueur a la main
	 * @return true s'il l'a sinon false
	 */
	public Boolean isTour() {
		return tour;
	}
	
	/**
	 * Permet de donner ou non la main au joueur
	 * @param tour La valeur souhaitée
	 */
	public void setTour(Boolean tour) {
		this.tour = tour;
	}
	
	/**
	 * Setter du nom du joueur
	 * @param nom Le nom souhaité
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Getter du nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Getter de la couleur du joueur
	 * @return La couleur du joueur
	 */
	public String getCouleur() {
		return couleur;
	}
	

}
