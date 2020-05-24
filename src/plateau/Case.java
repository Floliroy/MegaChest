package plateau;

import com.google.gson.annotations.Expose;
import personnages.Personnage;
import ui.util.CaseImage;

public class Case {
	
	/** coordonnee x (colonne) */
	@Expose
	private int positionX;
	/** coordonnee y (ligne) */
	@Expose
	private int positionY;
	/** personnage present sur la case */
	@Expose
	private Personnage personnage;
	/** JPanel correspondant aux coordonnées de la cas */
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
	 * Getter position y
	 * 
	 * @return positionY
	 */
	public int getPositionY() {
		return positionY;
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
	 * Permet de set le personnage sur la case ainsi que sur le JPanel correspondant
	 * @param personnage Le personnage a attribuer a la case
	 * @param fond La couleur du fond du JPanel correspondant
	 */
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
	 * Permet de récupérer le JPanel correspondant à la cas
	 * @return Le JPanel correspondant
	 */
	public CaseImage getPanel() {
		return panel;
	}

	/**
	 * Permet de set le JPanel correspondant à la case
	 * @param panel Le JPanel correspondant
	 */
	public void setPanel(CaseImage panel) {
		this.panel = panel;
	}
	
}
