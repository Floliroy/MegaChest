package plateau;

import java.util.ArrayList;

import partie.Jeu;
import personnages.Personnage;
import ui.Fenetre;
import ui.panneau.PanneauJeu;
import util.Util;

public class Plateau {
	
	/** Nombre de ligne du plateau */
	public final static int NOMBRE_LIGNE = 8;
	/** Nombre de colonne par ligne du plateau */
	public final static int NOMBRE_COLONNE = 16;
	
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
				plateau[ligne][colonne] = new Case(colonne, ligne);
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
	 * @param positionX colonne du plateau
	 * @param positionY ligne du plateau
	 * 
	 * @return true si case est dedans <br/>
	 * 		   false si la case est en-dehors
	 */
	public boolean isDansPlateau(int positionX, int positionY) {
		return positionX < NOMBRE_COLONNE && positionX >= 0 && positionY < NOMBRE_LIGNE && positionY >= 0;
	}
	
	/**
	 * Permet de recuperer une case a partir de ses
	 * coordonnees
	 * 
	 * @param positionX colonne de la case
	 * @param positionY ligne de la case
	 * 
	 * @return Case si la case a ete trouvee
	 * 		   null si la case est introuvable
	 */
	public Case getCase(int positionX, int positionY) {
		if(isDansPlateau(positionX, positionY))
			return plateau[positionY][positionX];
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
	
	public Case getCaseInit(Personnage personnage){
		for(Case []ligne : plateau) {
			for(Case colonne : ligne) {
				if(!colonne.isEmpty() && colonne.getPersonnage().getNom().equals(personnage.getNom()))
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
		
		int pmPerso = personnage.getDeplacementsAvecBoost();
		Case position = getCase(personnage);
		int positionX = position.getPositionX();
		int positionY = position.getPositionY();
		int decalage = 0;

		for(int ligne = positionY - pmPerso; ligne <= positionY + pmPerso; ligne ++) {
			for(int colonne = positionX - decalage; colonne <= positionX + decalage; colonne ++)
				if((libre = getCase(colonne, ligne)) != null  && libre.getPersonnage() == null) 
					casesAtteignables.add(libre);
			decalage = ligne < positionY ? decalage + 1 : decalage - 1;
		}
		return casesAtteignables;
	}
	
	/**
	 * TODO
	 * @param personnage
	 * @return
	 */
	public ArrayList<Case> getCasesAPorte(Personnage personnage) {
		ArrayList<Case> casesAPorte = new ArrayList<Case>();
		Case positionCible = null;
		Personnage cible = null;
		
		int poPerso = personnage.getPorteeAvecBoost();
		Case positionPersonnage = getCase(personnage);
		int positionPersonnageX = positionPersonnage.getPositionX();
		int positionPersonnageY = positionPersonnage.getPositionY();

		for(int ligne = positionPersonnageY - poPerso; ligne <= positionPersonnageY + poPerso; ligne ++) {
			for(int colonne = positionPersonnageX - poPerso; colonne <= positionPersonnageX + poPerso; colonne ++)
				if((positionCible = getCase(colonne, ligne)) != null  && (cible = positionCible.getPersonnage()) != null && !cible.equals(personnage))
					casesAPorte.add(positionCible);
		}
		return casesAPorte;
	}
	
	/**
	 * TODO
	 */
	public void placerPersonnage(int positionX, int positionY, Personnage personnage) {
		plateau[positionY][positionX].setPersonnage(personnage);
	}
	
	/**
	 * TODO
	 */
	public void deplacerPersonnage(Case caseDepart, int positionX, int positionY) {
		/**/
		placerPersonnage(positionX, positionY, caseDepart.getPersonnage());
		/**/
		caseDepart.setPersonnage(null);
	}
	
	/**
	 * TODO
	 */
	public boolean deplacerPersonnage(Fenetre fenetre, Case caseDepart, Case caseArrivee) {
		PanneauJeu panel = fenetre.getPanneauJeu();
		int distance = Util.distanceCase (caseDepart, caseArrivee.getPositionX(), caseArrivee.getPositionY());
		int pmPerso = caseDepart.getPersonnage().getDeplacements();
	
		if(distance <= pmPerso) {	
			placerPersonnage(caseArrivee.getPositionX(), caseArrivee.getPositionY(), caseDepart.getPersonnage());
			caseDepart.getPersonnage().setDeplacements(pmPerso - distance);
			caseArrivee.setPersonnage(caseDepart.getPersonnage(),
					fenetre.getJeu().getJoueur1().getEquipe().isDansEquipe(panel.getPersonnageSelectionne()) ? "blue.png" : "red.png");
			caseDepart.setPersonnage(null,null);
			return true;
		}
		
		System.out.println("Deplacement impossible");
		return false;	
	}
	
	
	
	
	/**
	 * TODO
	 */
	private void afficherLigneSeparation() {
		System.out.print("  ");
		for(int col=0 ; col<NOMBRE_COLONNE*4+1 ; col++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	/**
	 * TODO
	 */
	public void afficherPlateau() {
		System.out.print("\n  ");
		for(int col=0 ; col<NOMBRE_COLONNE ; col++) {
			String numColonne = (col+1) + "";
			System.out.print("  " + numColonne + (numColonne.length() < 2 ? " " : ""));
		}
		System.out.println();
		
		for(int lig=0 ; lig<NOMBRE_LIGNE ; lig++) {
			afficherLigneSeparation();
			System.out.print((lig+1) + " ");
			
			for(int col=0 ; col<NOMBRE_COLONNE ; col++) {
				System.out.print("| " + (!getCase(col, lig).isEmpty() ? getCase(col, lig).getPersonnage().getNom().substring(0, 1) : " ") + " ");
			}
			System.out.println("|");			
		}
		
		afficherLigneSeparation();
		System.out.println();
	}
	
	
	public Case getFirstCaseLeft() {
		for(Case []ligne : plateau) {
			for(Case colonne : ligne) {
				if(colonne.getPositionX() < Jeu.NOMBRE_COLONNE_PLACEMENT && colonne.isEmpty()) {
					return colonne;
				}
			}
		}
		return null;
	}
	
	public Case getFirstCaseRight() {
		for(Case []ligne : plateau) {
			for(Case colonne : ligne) {
				if(colonne.getPositionX() >= Plateau.NOMBRE_COLONNE-Jeu.NOMBRE_COLONNE_PLACEMENT && colonne.isEmpty()) {
					return colonne;
				}
			}
		}
		return null;
	}
}
