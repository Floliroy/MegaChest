package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import objets.Objet;
import partie.Jeu;
import partie.Joueur;


import util.FileManager;

import personnages.Personnage;
import ui.panneau.PanneauActions;
import ui.souris.SourisJeu;


public class Actions implements ActionListener {

	/**
	 * Les valeurs du flag action permettant de savoir quelle action est désirée
	 */
	public static final int ACTION_VALIDER = 1;
	public static final int ACTION_ATTAQUER = 2;
	public static final int ACTION_DEPLACER = 3;
	public static final int ACTION_PASSER_TOUR = 4;
	public static final int ACTION_EQUIPER = 5;
	
	/** La fenetre de jeu */
	private Fenetre fenetre;
	/** L'action souhaitée */
	private Integer action;
	
	/**
	 * Constructeur de notre action listener
	 * @param fenetre La fenetre de jeu
	 * @param action L'action souhaitée
	 */
	public Actions(Fenetre fenetre, Integer action) {
		this.fenetre = fenetre;
		this.action = action;
	}
	
	/**
	 * L'action listener renvoyant sur la bonne fonction suivant l'attribut action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(this.action) {
		case ACTION_VALIDER :
			actionValider();
			break;
		case ACTION_ATTAQUER :
			actionAttaquer();
			break;
		case ACTION_DEPLACER :
			actionDeplacer();
			break;
		case ACTION_PASSER_TOUR :
			actionPasser();
			break;
		case ACTION_EQUIPER :
			actionEquiper();
			break;
		}
	}
	

	/**
	 * L'action permettant de valider la composition et le positionnement de son équipe<br>
	 * Permet aussi de lancer le début de la partie
	 */
	private void actionValider() {
		Jeu jeu = fenetre.getJeu();
		Joueur joueur1 = jeu.getJoueur1();
		Joueur joueur2 = jeu.getJoueur2();
		
		if(joueur1.getEquipe().isComplete() && joueur2.getEquipe().isComplete()) {
			//Quand les deux joueurs ont terminé leur équipe
			joueur1.getEquipe().calculerBonusEquipe();
			joueur2.getEquipe().calculerBonusEquipe();
			
			System.out.println();
			System.out.println("La partie commence !");
			jeu.setEtatJeu(Jeu.PHASE_ACTION);
			
			jeu.inverseJoueurs();
			fenetre.getPanneauActions().refreshActions();
			fenetre.getPanneauJeu().refresh();
		}else if(jeu.getJoueurActif().getEquipe().isComplete()) {
			//Quand le second joueur doit compléter et positionner son équipe
			jeu.inverseJoueurs();
			fenetre.getPanneauActions().showSelection();
			fenetre.getPanneauJeu().refresh();
		}
	}
	
	/**
	 * L'action permettant de set les différents booleen de jeu aux valeurs pour permettre une attaque
	 */
	private void actionAttaquer() {
		Jeu jeu = fenetre.getJeu();
		if(!jeu.getAttaqueEffectue() && !jeu.getJetonAttaque()) {
			jeu.setPersonnageJoue(fenetre.getPanneauJeu().getPersonnageSelectionne());
			jeu.setJetonAttaque(true);
			System.out.println();
			System.out.println("Cliquez sur le personnage a attaquer.");
		}else {
			if(!jeu.getActionEffectue()) {
				jeu.setPersonnageJoue(null);				
			}
			jeu.setJetonAttaque(false);
			System.out.println("Action d'attaque annule.");
		}
	}
	
	/**
	 * L'action permettant de set les différents booleen de jeu aux valeurs pour permettre un déplacement
	 */
	private void actionDeplacer() {
		Jeu jeu = fenetre.getJeu();
		jeu.setJetonDeplace(!jeu.getJetonDeplace());
		if(jeu.getJetonDeplace()) {
			jeu.setPersonnageJoue(fenetre.getPanneauJeu().getPersonnageSelectionne());
			System.out.println();
			System.out.println("Cliquez sur la case ou vous voulez aller.");
		}else {
			if(!jeu.getActionEffectue()) {
				jeu.setPersonnageJoue(null);
			}
			System.out.println("Action de deplacement annule.");
		}
	}
	
	/**
	 * L'action permettant de terminer son tour et donc de donner la main à l'autre joueur
	 */
	private void actionPasser() {
		Jeu jeu = fenetre.getJeu();
		FileManager fm = new FileManager();
		
		try {
			fm.writeSauvegarde(jeu);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		jeu.inverseJoueurs();
		jeu.resetTour();
		
		fenetre.getPanneauActions().refreshActions();
		fenetre.getPanneauJeu().getCasePersoSelectionne().setTransparency(null);
		fenetre.getPanneauJeu().setSelectionne(null, null);
		fenetre.getPanneauJeu().refresh();
		
		System.out.println();
		System.out.println("Changement de joueur...");
	}
	
	/**
	 * L'action permettant de valider le choix d'equiper un objet sur un personnage
	 */
	private void actionEquiper() {
		PanneauActions panneauActions = fenetre.getPanneauActions();
		Jeu jeu = fenetre.getJeu();
		jeu.setJetonEquipement(jeu.getJetonEquipement()-1);
		
		Personnage perso = panneauActions.getPersoSelectionne();
		Objet objet = panneauActions.getObjetLoot();
		perso.addListObjets(objet);
		
		if(jeu.getJetonEquipement() == 0) {
			panneauActions.refreshActions();
			SourisJeu.refreshBoutonsActions(fenetre, fenetre.getJeu(), fenetre.getPanneauJeu());
		}else {
			fenetre.getPanneauActions().showChoixObjet(jeu.getJoueurInactif());
		}
		
		System.out.println(perso.getNom() + " a recu " + objet.getNom());
	}

}
