package plateau;

import java.util.ArrayList;

import personnages.Personnage;

public class Plateau {
	
	/** Nombre de ligne du plateau */
	private final static int NOMBRE_LIGNE = 16;
	/* Nombre de colonne par ligne du plateau */
	private final static int NOMBRE_COLONNE = 8;
	
	/* -------------------------------------------------- */
	
	/** plateau de jeu */
	private Case [][] plateau;
	
	/* -------------------------------------------------- */
	
	/**
	 * Constructeur permettant d'initialiser toutes
	 * les cases du plateau
	 * 
	 */
	public Plateau() {
		plateau = new Case[NOMBRE_LIGNE][NOMBRE_COLONNE];
		
		for(int ligne = 0; ligne < NOMBRE_LIGNE; ligne ++) {
			for(int colonne = 0; colonne < NOMBRE_COLONNE; colonne ++)
				plateau[ligne][colonne] = new Case(ligne, colonne);
		}	
	}

	/**
	 * Getter plateau de jeu
	 * 
	 * @return plateau
	 */
	public Case[][] getPlateau() {
		return plateau;
	}

	/**
	 * Setter plateau de jeu
	 * 
	 * @param plateau plateau de jeu voulu
	 */
	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}
	
	/**
	 * Verifie que les coordonnees de la case sont 
	 * bien dans le plateau
	 * 
	 * @param positionX ligne du plateau
	 * @param positionY colonne du plateau
	 * 
	 * @return true si case est dedans <br/>
	 * 		   false si la case est en-dehors
	 */
	public boolean isDansPlateau(int positionX, int positionY) {
		return positionX < NOMBRE_LIGNE && positionX >= 0 && positionY < NOMBRE_COLONNE && positionY >= 0;
	}
	
	/**
	 * Permet de recuperer une case a partir de ses
	 * coordonnees
	 * 
	 * @param positionX ligne de la case
	 * @param positionY colonne de la case
	 * 
	 * @return Case si la case a ete trouvee
	 * 		   null si la case est introuvable
	 */
	public Case getCase(int positionX, int positionY) {
		if(isDansPlateau(positionX, positionY))
			return plateau[positionX][positionY];
		else
			return null;
	}
	
	/**
	 * Permet de recuperer une case a partir du personnage
	 * present dessus
	 * 
	 * @param personnage personnage present sur la case
	 * 
	 * @return Case si la case a ete trouvee
	 * 		   null si la case est introuvable
	 */
	public Case getCase(Personnage personnage){
		for(Case []ligne : plateau) {
			for(Case colonne : ligne) {
				if(!colonne.isEmpty() && colonne.getPersonnage().equals(personnage))
					return colonne;
			}
		}
		return null;
	}
	
	/**
	 * Permet d'obtenir toutes les cases atteignables par un 
	 * personnage depuis sa position
	 * 
	 * @param personnage personnage dont on veut connaitre les deplacements possibles
	 * 
	 * @return ArrayList<Case> contenant les cases atteignables
	 */
	public ArrayList<Case> getCasesAtteignables(Personnage personnage) {
		ArrayList<Case> casesAtteignables = new ArrayList<Case>();
		Case libre = null;
		
		int pmPerso = personnage.getPorteeAvecBoost();
		Case position = getCase(personnage);
		int positionX = position.getPositionX();
		int positionY = position.getPositionY();
		int decalage = 0;

		for(int ligne = positionX - pmPerso; ligne <= positionX + pmPerso; ligne ++) {
			for(int colonne = positionY - decalage; colonne <= positionY + decalage; colonne ++)
				if((libre = getCase(ligne, colonne)) != null  && libre.getPersonnage() == null) 
					casesAtteignables.add(libre);
			decalage = ligne < positionX ? decalage + pmPerso : decalage - pmPerso;
		}
		return casesAtteignables;
	}
	
	/**
	 * TODO
	 */
	public void afficherPlateau() {
		for(int lig = 0; lig < NOMBRE_LIGNE; lig ++) {
			for(int col = 0; col < NOMBRE_COLONNE; col ++) 
				if(plateau[lig][col].getPersonnage() == null)
					System.out.print(" *");
				else 
					System.out.print(" x");
			System.out.println("");
		}
	}
	
	
}
