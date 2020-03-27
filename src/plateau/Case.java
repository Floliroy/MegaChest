package plateau;

import personnages.Personnage;

public class Case {
	

	/** coordonnee x */
	private int pos_x;
	
	/** coordonnee y */
	private int pos_y;
	
	/** personnage present sur la case
	 *  par default = null
	 */
	private Personnage personnage;
	
	/**
	 * Constructeur permettant de creer une Case
	 * 
	 * @param pos_x position x sur le plateau
	 * @param pos_y position y sur le plateau
	 */
	public Case(int pos_x, int pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	/**
	 * Getter position x
	 * 
	 * @return pos_x
	 */
	public int getPos_x() {
		return pos_x;
	}

	/**
	 * Setter position x
	 * 
	 * @param pos_x position x voulu
	 */
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	/**
	 * Getter position y
	 * 
	 * @return pos_y
	 */
	public int getPos_y() {
		return pos_y;
	}

	/**
	 * Setter position y 
	 * 
	 * @param pos_y position y voulu
	 */
	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
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
	
	

}
