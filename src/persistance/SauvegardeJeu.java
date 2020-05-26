package persistance;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;


import partie.Jeu;
import partie.Joueur;
import plateau.*;
import personnages.*;


public class SauvegardeJeu {
	/* Jeu à sauvegarde ou à charger */
	private Jeu partie;
	/* Cases contenant les personnages du Joueur 1 */
	@Expose
	private ArrayList<Case> equipeJoueur1;
	/* Cases contenant les personnages du Joueur 2 */
	@Expose
	private ArrayList<Case> equipeJoueur2;
	/* Joueur 1 à sauvegarder ou à charger */
	@Expose
	private Joueur joueur1;
	/* Joueur 2 à sauvegarder ou à charger */
	@Expose
	private Joueur joueur2;
	/* Etat du jeu à sauvegarder ou à charger */
	@Expose
	private int etatJeu;

	/**
	 * Constructeur permettant de créer une sauvegarde d'un jeu
	 * 
	 * @param partie jeu à sauvegarder
	 */
	public SauvegardeJeu(Jeu partie) {
		this.partie = partie;
		equipeJoueur1 = new ArrayList<Case>();
		equipeJoueur2 = new ArrayList<Case>();
		joueur1 = partie.getJoueur1();
		joueur2 = partie.getJoueur2();
		
		etatJeu = partie.getEtatJeu();
	}
	
	/**
	 * Permet d'ajouter une case à la liste contenant les cases occupées par les personnages d'un des joueur
	 * 
	 * @param current
	 */
	private void addPersonnageToList(Case current) {
		Personnage perso = current.getPersonnage();
		/*On vérifie à quelle équipe appartient le joueur*/
		if(partie.getJoueur1().getEquipe().isDansEquipe(perso))
			equipeJoueur1.add(current);
		else
			equipeJoueur2.add(current);
	}
	
	/**
	 * Permet de sauvegarder toutes les cases contenant un personnage
	 */
	public void sauvegardePersonnage(){
		Plateau plateau = partie.getPlateauJeu();
		
		// On parcourt l'ensemble du plateau de jeu 
		for(int colonne = 0; colonne < Plateau.NOMBRE_COLONNE; colonne ++) {
			for(int ligne = 0; ligne < Plateau.NOMBRE_LIGNE; ligne ++) {
				Case current = plateau.getCase(colonne, ligne);
				// Si la case n'est pas vide, on l'ajoute dans la liste correspondant au bon joueur
				if (!current.isEmpty()) 
					addPersonnageToList(current);
			}
		}
		
	}

	/**
	 * Getter equipeJoueur1
	 * 
	 * @return la liste des cases contenat les personnages du joueur 1
	 */
	public ArrayList<Case> getEquipeJoueur1() {
		return equipeJoueur1;
	}

	/**
	 * Getter equipeJoueur2
	 * 
	 * @return la liste des cases contenat les personnages du joueur 2
	 */
	public ArrayList<Case> getEquipeJoueur2() {
		return equipeJoueur2;
	}

	/**
	 * Getter joueur1
	 * 
	 * @return le joueur1
	 */
	public Joueur getJoueur1() {
		return joueur1;
	}

	/**
	 * Getter joueur2
	 * 
	 * @return le joueur2
	 */
	public Joueur getJoueur2() {
		return joueur2;
	}
	
	/**
	 * Getter etatJeu
	 * 
	 * @return l'etat du jeu au moment de la sauvegarde
	 */
	public int getEtatJeu() {
		return etatJeu;
	}


}
