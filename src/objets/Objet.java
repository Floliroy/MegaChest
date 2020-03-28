package objets;

public abstract class Objet {
	
	private Integer stat;
	
	private TypeStat typeStat;
	
	private String nom;
	
	public Objet(Integer stat, TypeStat typeStat, String nom) {
		this.stat = stat;
		this.typeStat = typeStat;
		this.nom=nom;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	public TypeStat getTypeStat() {
		return typeStat;
	}

	public void setTypeStat(TypeStat typeStat) {
		this.typeStat = typeStat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
