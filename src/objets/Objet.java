package objets;

public abstract class Objet {
	
	/** Valeur de la statistique de l'objet */
	private Integer stat;
	
	/** Le type de statistique de l'objet */ 
	private TypeStat typeStat;
	
	/** Le nom de l'objet */
	private String nom;
	
	/**
	 * Contructeur de la classe Objet 
	 * @param stat Valeur de la statistique de l'objet
	 * @param typeStat Le type de statistique de l'objet
	 * @param nom Le nom de l'objet
	 */
	public Objet(Integer stat, TypeStat typeStat, String nom) {
		this.stat = stat;
		this.typeStat = typeStat;
		this.nom=nom;
	}

	/**
	 * Permet de conna�tre la valeur de la statistique de l'objet
	 * @return Renvoit la valeur de la statistique
	 */
	public Integer getStat() {
		return stat;
	}

	/**
	 * Permet de renseigner la valeur de la statistique de l'objet
	 * @param stat Valeur de la statistique de l'objet 
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}

	/**
	 * Permet de conna�tre le type de la statistique de l'objet
	 * @return Renvoit le type de la statistique de l'objet
	 */
	public TypeStat getTypeStat() {
		return typeStat;
	}

	/**
	 * Permet de renseigner le type de la statistique de l'objet
	 * @param typeStat Type de la statistique de l'objet
	 */
	public void setTypeStat(TypeStat typeStat) {
		this.typeStat = typeStat;
	}

	/**
	 * Permet de conna�tre le nom de l'objet
	 * @return Renvoit le nom de l'objet
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Permet de renseigner le nom de l'objet
	 * @param nom Nom de l'objet
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

}
