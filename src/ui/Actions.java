package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import partie.Jeu;
import partie.Joueur;

public class Actions implements ActionListener {

	public static final int ACTION_VALIDER = 1;
	public static final int ACTION_ATTAQUER = 2;
	public static final int ACTION_DEPLACER = 3;
	public static final int ACTION_PASSER_TOUR = 4;
	
	private Fenetre fenetre;
	private Integer action;
	
	public Actions(Fenetre fenetre, Integer action) {
		this.fenetre = fenetre;
		this.action = action;
	}
	
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
		}
	}
	
	private void actionValider() {
		Jeu jeu = fenetre.getJeu();
		Joueur joueur1 = jeu.getJoueur1();
		Joueur joueur2 = jeu.getJoueur2();
		if(joueur1.getEquipe().isComplete() && joueur2.getEquipe().isComplete()) {
			joueur1.getEquipe().calculerBonusEquipe();
			joueur2.getEquipe().calculerBonusEquipe();
			
			System.out.println("La partie commence");
			
			jeu.setEtatJeu(Jeu.PHASE_ACTION);
			jeu.inverseJoueurs();
			fenetre.getPanneauActions().refreshActions();
			fenetre.getPanneauJeu().refresh();
		}else if(jeu.getJoueurActif().getEquipe().isComplete()) {
			jeu.inverseJoueurs();
			fenetre.getPanneauActions().showSelection();
			fenetre.getPanneauJeu().refresh();
		}
	}
	
	private void actionAttaquer() {
		Jeu jeu = fenetre.getJeu();
		if(!jeu.getAttaqueEffectue() && !jeu.getJetonAttaque()) {
			jeu.setPersonnageJoue(fenetre.getPanneauJeu().getPersonnageSelectionne());
			jeu.setJetonAttaque(true);
			System.out.println("Cliquez sur le personnage a attaquer.");
		}else {
			if(!jeu.getActionEffectue()) {
				jeu.setPersonnageJoue(null);				
			}
			jeu.setJetonAttaque(false);
			System.out.println("Action d'attaque annule.");
		}
	}
	
	private void actionDeplacer() {
		Jeu jeu = fenetre.getJeu();
		jeu.setJetonDeplace(!jeu.getJetonDeplace());
		if(jeu.getJetonDeplace()) {
			jeu.setPersonnageJoue(fenetre.getPanneauJeu().getPersonnageSelectionne());
			System.out.println("Cliquez sur la case ou vous voulez aller.");
		}else {
			if(!jeu.getActionEffectue()) {
				jeu.setPersonnageJoue(null);
			}
			System.out.println("Action de deplacement annule.");
		}
	}
	
	private void actionPasser() {
		Jeu jeu = fenetre.getJeu();
		jeu.inverseJoueurs();
		jeu.resetTour();
		/*fenetre.getPanneauJeu().getCasePersoSelectionne().setTransparency(null);
		fenetre.getPanneauJeu().getCasePersoSelectionne().getIgnoreRepaint();*/
		
		fenetre.getPanneauActions().refreshActions();
		fenetre.getPanneauJeu().getCasePersoSelectionne().setTransparency(null);
		fenetre.getPanneauJeu().setSelectionne(null, null);
		fenetre.getPanneauJeu().refresh();
		System.out.println("Changement de joueur...");
	}

}
