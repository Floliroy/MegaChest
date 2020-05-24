package personnages;

/**
 * Les diff�rentes originies possibles pour un personnage : Demacia, Noxus, Ionia, Bilgewater
 * @author Florian
 *
 */
public enum Origine {
	DEMACIA ("Démacia"),
	NOXUS ("Noxus"),
	IONIA ("Ionia"),
	BILGEWATER ("BilgeWater");
	
	private String name = "";
	
	Origine(String name){
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
