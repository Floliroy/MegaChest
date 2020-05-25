package ui.util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import objets.Objet;
import partie.Jeu;
import partie.Joueur;


import util.FileManager;
import util.Util;
import personnages.Personnage;
import plateau.Case;
import ui.Fenetre;
import ui.panneau.PanneauActions;
import ui.panneau.PanneauJeu;
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
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		
		//On regarde si on a deja effectué une attaque et si on possède un jeton d'attaque
		if(!jeu.getAttaqueEffectue() && !jeu.getJetonAttaque()) {
			//On renseigne le personnage joué pour ce tour
			jeu.setPersonnageJoue(fenetre.getPanneauJeu().getPersonnageSelectionne());
			//On donne un jeton d'attaque
			jeu.setJetonAttaque(true);
			System.out.println();
			System.out.println("Cliquez sur le personnage a attaquer.");
			if(jeu.getJetonDeplace()) {
				//Si on avait un jeton de déplacement on le supprime
				jeu.setJetonDeplace(false);
				panneauJeu.refresh();
			}
			//On affiche la zone attaquable
			ArrayList<Case> cases = jeu.getPlateauJeu().getAllCasesAPorte(jeu.getPersonnageJoue(), jeu);
			for(Case casePlateau : cases) {
				CaseImage panel = casePlateau.getPanel();
				if(!casePlateau.isEmpty()) {
					panel.setTransparency(Util.getRedTransparency());
				}else {
					panel.setBackground(panel.getBackground()==Color.LIGHT_GRAY ? new Color(215,110,110) : new Color(175,35,35));
				}
				panel.repaint();
			}
		}else {
			//Si on a pas encore effectué d'action on peut changer de personnage joué pour ce tour
			if(!jeu.getActionEffectue()) {
				jeu.setPersonnageJoue(null);				
			}
			//On enleve le jeton d'attaque
			jeu.setJetonAttaque(false);
			System.out.println("Action d'attaque annulee.");
			panneauJeu.refresh();
		}
	}
	
	/**
	 * L'action permettant de set les différents booleen de jeu aux valeurs pour permettre un déplacement
	 */
	private void actionDeplacer() {
		Jeu jeu = fenetre.getJeu();
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		
		jeu.setJetonDeplace(!jeu.getJetonDeplace());
		//On regarde si on possède un jeton de déplacement
		if(jeu.getJetonDeplace()) {
			//On renseigne le personnage joué pour ce tour
			jeu.setPersonnageJoue(fenetre.getPanneauJeu().getPersonnageSelectionne());
			System.out.println();
			System.out.println("Cliquez sur la case ou vous voulez aller.");
			if(jeu.getJetonAttaque()) {
				//Si on avait un jeton d'attaque on le supprime
				jeu.setJetonAttaque(false);
				panneauJeu.refresh();
			}
			//On affiche la zone de deplacements possibles
			ArrayList<Case> cases = jeu.getPlateauJeu().getCasesAtteignables(jeu.getPersonnageJoue());
			for(Case casePlateau : cases) {
				CaseImage panel = casePlateau.getPanel();
				if(casePlateau.isEmpty()) {
					panel.setBackground(panel.getBackground()==Color.LIGHT_GRAY ? new Color(128,195,128) : new Color(0,155,0));
				}
				panel.repaint();
			}
		}else {
			//Si on a pas encore effectué d'action on peut changer de personnage joué pour ce tour
			if(!jeu.getActionEffectue()) {
				jeu.setPersonnageJoue(null);
			}
			System.out.println("Action de deplacement annulee.");
			panneauJeu.refresh();
		}
	}
	
	/**
	 * L'action permettant de terminer son tour et donc de donner la main à l'autre joueur
	 */
	private void actionPasser() {
		Jeu jeu = fenetre.getJeu();
		FileManager fm = new FileManager();

		//On réinitialise les jetons et flags pour le prochain tour
		jeu.inverseJoueurs();
		jeu.resetTour();
		
		//On réinitialise les panneaux de notre UI pour le prochain tour
		fenetre.getPanneauActions().refreshActions();
		fenetre.getPanneauJeu().getCasePersoSelectionne().setTransparency(null);
		fenetre.getPanneauJeu().setSelectionne(null, null);
		fenetre.getPanneauJeu().refresh();
		
		System.out.println();
		System.out.println("Changement de joueur...");
		
		//On sauvegarde la partie au début du nouveau tour
		try {
			fm.writeSauvegarde(jeu);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * L'action permettant de valider le choix d'equiper un objet sur un personnage
	 */
	private void actionEquiper() {
		PanneauActions panneauActions = fenetre.getPanneauActions();
		Jeu jeu = fenetre.getJeu();
		jeu.setJetonEquipement(jeu.getJetonEquipement()-1);
		
		//On ajoute l'objet aléatoire au personnage sélectionné
		Personnage perso = panneauActions.getPersoSelectionne();
		Objet objet = panneauActions.getObjetLoot();
		perso.addListObjets(objet);
		
		if(jeu.getJetonEquipement() == 0) {
			//Si c'était le seul jeton d'équipement
			panneauActions.refreshActions();
			SourisJeu.refreshBoutonsActions(fenetre);
		}else {
			//S'il y avait un deuxieme jeton d'équipement pour l'autre joueur
			fenetre.getPanneauActions().showChoixObjet(jeu.getJoueurInactif());
		}
		
		System.out.println(perso.getNom() + " a recu " + objet.getNom());
	}

}
