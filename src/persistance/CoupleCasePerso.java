package persistance;

import personnages.Personnage;
import plateau.Case;

public class CoupleCasePerso {
	
	private Case current;
	private Personnage perso;
	
	public CoupleCasePerso(Case current, Personnage perso) {
		this.current = current;
		this.perso = perso;
	}

	public Case getCurrent() {
		return current;
	}

	public Personnage getPerso() {
		return perso;
	}
	
	

}
