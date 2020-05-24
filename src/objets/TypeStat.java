package objets;

/**
 * Les différents type de statistiques possibles pour un objet : Vie, Déplacements, Dégats, Vitesse.
 * @author Dorian
 *
 */
public enum TypeStat {
	
	VIE ("PV"),
	DEPLACEMENTS("PM"),
	DEGATS("Dmg"),
	PORTEE("PO"),
	VITESSE("Vit");
	
	private String name = "";
	
	TypeStat(String name){
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

}
