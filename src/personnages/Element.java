package personnages;

/**
 * Les différents éléments possible pour un personnage : Feu, Eau, Terre, Vent.
 * @author Florian
 *
 */
public enum Element {
	FEU ("Feu"),
	EAU ("Eau"),
	TERRE ("Terre"),
	VENT ("Vent");
	
	private String name = "";
	
	Element(String name){
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
