package plateau;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;

import persistance.customDeserialize.CaseDeserialize;
import personnages.Personnage;
import ui.CaseImage;

public class Case {
	

	/** coordonnee x */
	@Expose
	private int positionX;
	
	/** coordonnee y */
	@Expose
	private int positionY;
	
	/** personnage present sur la case
	 *  par default = null
	 */
	@Expose
	private Personnage personnage;
	
	private CaseImage panel;
	
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
	public int getPositionX() {
		return positionX;
	}

	/**
	 * Setter position x
	 * 
	 * @param positionX position x voulu
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * Getter position y
	 * 
	 * @return positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * Setter position y 
	 * 
	 * @param positionY position y voulu
	 */
	public void setPositionY(int positionY) {
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
	
	public void setPersonnage(Personnage personnage, String fond) {
		this.personnage = personnage;
		this.panel.setPersonnage(personnage);
		this.panel.setStringFond(fond);
	}
	
	/**
	 * Verifie si un personnage est present sur la case
	 * 
	 * @return true si un personnage est present <br>
	 * 		   false si la case est vide
	 */
	public boolean isEmpty() {
		return personnage == null;
	}
	
	/**
	 * 
	 */
	public String dumpCase() {
		return "(" + (positionX+1) + "," + (positionY+1) + ")";
	}

	public CaseImage getPanel() {
		return panel;
	}

	public void setPanel(CaseImage panel) {
		this.panel = panel;
	}
	
}
