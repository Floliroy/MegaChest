package plateau;

import personnages.Personnage;

public class Case {
	

	/** coordonnee x */
	private int positionX;
	
	/** coordonnee y */
	private int positionY;
	
	/** personnage present sur la case
	 *  par default = null
	 */
	private Personnage personnage;
	
	/* -------------------------------------------------- */
	
	/**
	 * Constructeur permettant de creer une Case
	 * 
	 * @param positionX position x sur le plateau
	 * @param positionY position y sur le plateau
	 */
	public Case(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		personnage = null;
	}

	/**
	 * Getter position x
	 * 
	 * @return positionX
	 */
	public int getpositionX() {
		return positionX;
	}

	/**
	 * Setter position x
	 * 
	 * @param positionX position x voulu
	 */
	public void setpositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * Getter position y
	 * 
	 * @return positionY
	 */
	public int getpositionY() {
		return positionY;
	}

	/**
	 * Setter position y 
	 * 
	 * @param positionY position y voulu
	 */
	public void setpositionY(int positionY) {
		this.positionY = positionY;
	}

	/**
	 * Getter personnage
	 * 
	 * @return personnage present sur la case
	 */
	public Personnage getPersonnage() {
		return personnage;
	}

	/**
	 * Setter personnage
	 * 
	 * @param personnage a attribuer a la case
	 */
	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}
	
	/**
	 * Verifie si un personnage est present sur la case
	 * 
	 * @return true si un personnage est present <br>
	 * 		   false si la case est vide
	 */
	public boolean isEmpty() {
		return getPersonnage() == null;
	}
	

}
