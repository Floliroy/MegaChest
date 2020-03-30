package plateau;

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
		
		for(int lig = 0; lig < NOMBRE_LIGNE; lig ++) {
			for(int col = 0; col < NOMBRE_COLONNE; col ++)
				plateau[lig][col] = new Case(lig, col);
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
	
	
	
}
