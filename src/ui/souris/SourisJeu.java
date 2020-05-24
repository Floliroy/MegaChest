package ui.souris;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import partie.Equipe;
import partie.Jeu;
import partie.Joueur;
import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import ui.CaseImage;
import ui.Fenetre;
import ui.panneau.PanneauJeu;
import util.Util;

/**
 * SourisJeu permet d'effectuer toutes les actions correspondantes 
 * pour chaques action réaliser par l'utilisateur avec la souris 
 * sur le panneauJeu.
 */
public class SourisJeu extends MouseAdapter {

	private Case casePlateau;
	private JPanel panel;
	private Fenetre fenetre;

	/**
	 * Constructeur de SourisJeu
	 * 
	 * @param casePlateau
	 * @param panel
	 * @param fenetre
	 */
	public SourisJeu(Case casePlateau, JPanel panel, Fenetre fenetre) {
		this.casePlateau = casePlateau;
		this.panel = panel;
		this.fenetre = fenetre;
	}

	/**
	 * Actualise les boutons d'actions du panneau panneauAction en fonction des
	 * actions que l'utilisateur peut effectuer.
	 * 
	 * @param jeu 
	 * @param panneauJeu
	 */
	public static void refreshBoutonsActions(Fenetre fenetre, Jeu jeu, PanneauJeu panneauJeu) {
		if (panneauJeu.getPersonnageSelectionne() == null) {
			fenetre.getPanneauActions().getButtonAttaquer().setEnabled(false);
			fenetre.getPanneauActions().getButtonDeplacer().setEnabled(false);
		} else {
			Boolean peutAttaquer = false;
			if (panneauJeu.getPersonnageSelectionne().isVivant()) {
				for (Case caseAttaquable : jeu.getPlateauJeu().getCasesAPorte(panneauJeu.getPersonnageSelectionne())) {
					if (!caseAttaquable.isEmpty()
							&& !jeu.getJoueurActif().getEquipe().isDansEquipe(caseAttaquable.getPersonnage())) {
						peutAttaquer = true;
					}
				}
			}
			Boolean peutDeplacer = panneauJeu.getPersonnageSelectionne().getDeplacementsAvecBoost() > 0;

			if (!jeu.getAttaqueEffectue() && peutAttaquer) {
				fenetre.getPanneauActions().getButtonAttaquer().setEnabled(true);
			} else {
				fenetre.getPanneauActions().getButtonAttaquer().setEnabled(false);
			}
			if (peutDeplacer && panneauJeu.getPersonnageSelectionne().isVivant()) {
				fenetre.getPanneauActions().getButtonDeplacer().setEnabled(true);
			} else {
				fenetre.getPanneauActions().getButtonDeplacer().setEnabled(false);
			}
		}

		if (jeu.getActionEffectue() || (panneauJeu.getPersonnageSelectionne() != null && !panneauJeu.getPersonnageSelectionne().isVivant())) {
			fenetre.getPanneauActions().getButtonPasser().setEnabled(true);
		} else {
			fenetre.getPanneauActions().getButtonPasser().setEnabled(false);
		}
	}

	/**
	 * Désélectionne un personnage lorsqu'il a déjà été sélectionné, la transparence
	 * jaune est supprimé.
	 * 
	 * @param image CaseImage contenant le personnage à désélectionner
	 */
	private void deselectPersonnage(CaseImage image) {
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		panneauJeu.setSelectionne(null, null);
		image.setTransparency(null);
	}

	/**
	 * Sélectionne un personnage en fonction de l'équipe du joueur et en supprimant
	 * l'ancienne sélection si un personnage est déjà sélectionné. Une transparence
	 * jaune est ajoutée pour indiquer la sélection.
	 * 
	 * @param image CaseImage contenant le personnage à sélectionner
	 */
	private void selectPersonnage(CaseImage image) {
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();

		/*
		 * Verifie que le personnage à sélectionner appartient bien à l'équipe du joueur
		 * actif
		 */
		if (fenetre.getJeu().getJoueurActif().getEquipe().isDansEquipe(casePlateau.getPersonnage())) {

			/* Si un personnage est déjà sélectionné, on supprime la sélection */
			if (panneauJeu.getCasePersoSelectionne() != null) {
				panneauJeu.getCasePersoSelectionne().setTransparency(null);
				panneauJeu.getCasePersoSelectionne().repaint();
			}
			/* On met à jours la sélection avec le bon personnage en surbrillance */
			panneauJeu.setSelectionne(casePlateau.getPersonnage(), (CaseImage) panel);
			image.setTransparency(Util.getYellowTransparency());

		}

	}

	/**
	 * Permet au joueur actif d'effectuer l'action "attaquer" avec le personnage
	 * qu'il a sélectionné Si une des des équipes ne contient plus de personnages
	 * envie, le jeu passe en <b>PHASE_TERMINE<b/>
	 * 
	 * @param panneauJeu panneau contenant le plateau de jeu
	 */
	private void attaquerPersoUI(PanneauJeu panneauJeu) {
		Jeu jeu = fenetre.getJeu();
		if (!casePlateau.isEmpty() && !jeu.getJoueurActif().getEquipe().isDansEquipe(casePlateau.getPersonnage())) {
			Personnage attaquant = jeu.getPersonnageJoue();
			Personnage defenseur = casePlateau.getPersonnage();
			if (jeu.actionAttaquer(attaquant, defenseur)) {
				jeu.setActionEffectue(true);
				jeu.setAttaqueEffectue(true);
				jeu.setJetonAttaque(false);

				// On supprime le personnage de son equipe s'il est mort
				Joueur joueurEquipement = null;
				if (!attaquant.isVivant()) {
					jeu.getJoueurActif().getEquipe().removeEquipe(attaquant);
					Case casePerso = jeu.getPlateauJeu().getCase(attaquant);
					casePerso.setPersonnage(null, null);
					casePerso.getPanel().setTransparency(null);
					casePerso.getPanel().repaint();
					
					if(!jeu.isFini() && jeu.getJoueurActif().getEquipe().getListePersonnages().size() % 2 == Equipe.TAILLE_EQUIPE % 2) {
						joueurEquipement = jeu.getJoueurInactif();
						jeu.setJetonEquipement(jeu.getJetonEquipement()+1);
					}
				}
				if (!defenseur.isVivant()) {
					jeu.getJoueurInactif().getEquipe().removeEquipe(defenseur);
					Case casePerso = jeu.getPlateauJeu().getCase(defenseur);
					casePerso.setPersonnage(null, null);
					casePerso.getPanel().setTransparency(null);
					casePerso.getPanel().repaint();
					
					if(!jeu.isFini() && jeu.getJoueurInactif().getEquipe().getListePersonnages().size() % 2 == Equipe.TAILLE_EQUIPE % 2) {
						joueurEquipement = jeu.getJoueurActif();
						jeu.setJetonEquipement(jeu.getJetonEquipement()+1);
					}
				}
				if(joueurEquipement != null) {
					fenetre.getPanneauActions().showChoixObjet(joueurEquipement);
				}
			}
		}
		if (jeu.isFini()) {
			jeu.setEtatJeu(Jeu.PHASE_TERMINE);
			fenetre.getPanneauActions().getButtonAttaquer().setEnabled(false);
			fenetre.getPanneauActions().getButtonDeplacer().setEnabled(false);
			fenetre.getPanneauActions().getButtonPasser().setEnabled(false);
			System.out.println();
			System.out.println(jeu.getGagnant().getNom() + ", tu es le gagnant !");
		}
	}

	/**
	 * Permet au joueur actif d'effectuer l'action "déplacer" avec le personnage
	 * qu'il a sélectionné. Le joueur a la possibilité de déplacer son personnage
	 * tant que ce dernier poossède encore des PM.
	 * 
	 * @param panneauJeu panneau contenant le plateau de jeu
	 */
	private void deplacerPersoUI(PanneauJeu panneauJeu) {

		Case previousCase = fenetre.getJeu().getPlateauJeu().getCase(panneauJeu.getPersonnageSelectionne());
		Jeu jeu = fenetre.getJeu();

		if (casePlateau.isEmpty() && fenetre.getJeu().getPersonnageJoue() != null && jeu.getJetonDeplace()
				&& jeu.getPersonnageJoue().equals(previousCase.getPersonnage())) {

			if (fenetre.getJeu().getPlateauJeu().deplacerPersonnage(fenetre, previousCase, casePlateau)) {

				casePlateau.getPanel().setTransparency(Util.getYellowTransparency());
				casePlateau.getPanel().repaint();

				previousCase.getPanel().setTransparency(null);
				previousCase.getPanel().repaint();
			}

			jeu.setJetonDeplace(false);
			jeu.setActionEffectue(true);
		}
	}

	/**
	 * Permet au joueur actif de positionner ses personnages pendant la phase
	 * <b>PHASE_SELECTION<b/>. Le joueur 1 et le joueur 2 ont respectivement la
	 * possibilité de poser sur les 4 premières et 4 dernières colonnes du plateau.
	 * 
	 * @param panneauJeu panneau contenant le plateau de jeu
	 */
	private void positionnerPersoUI(PanneauJeu panneauJeu) {
		Case previousCase = fenetre.getJeu().getPlateauJeu().getCase(panneauJeu.getPersonnageSelectionne());

		int limite = fenetre.getJeu().getJoueur1().isTour() ? Jeu.NOMBRE_COLONNE_PLACEMENT
				: Plateau.NOMBRE_COLONNE - Jeu.NOMBRE_COLONNE_PLACEMENT;

		if (casePlateau.isEmpty()
				&& fenetre.getJeu().getJoueurActif().getEquipe().isDansEquipe(panneauJeu.getPersonnageSelectionne())
				&& ((fenetre.getJeu().getJoueur1().isTour() && casePlateau.getPositionX() < limite)
						|| (fenetre.getJeu().getJoueur2().isTour() && casePlateau.getPositionX() >= limite))) {

			casePlateau.setPersonnage(previousCase.getPersonnage(),
					fenetre.getJeu().getJoueur1().getEquipe().isDansEquipe(panneauJeu.getPersonnageSelectionne())
							? "blue.png"
							: "red.png");
			casePlateau.getPanel().setTransparency(null);
			casePlateau.getPanel().repaint();
			previousCase.setPersonnage(null, null);
		}
		if (fenetre.getJeu().getJoueur1().isTour() && casePlateau.getPositionX() >= limite) {
			System.out.println("Vous ne pouvez placer votre personnage que dans les 4 premieres colonnes.");
		}
		if (fenetre.getJeu().getJoueur2().isTour() && casePlateau.getPositionX() < limite) {
			System.out.println("Vous ne pouvez placer votre personnage que dans les 4 dernieres colonnes.");
		}

		previousCase.getPanel().setTransparency(null);
		previousCase.getPanel().repaint();
		panneauJeu.setSelectionne(null, null);

	}

	/**
	 * Gère les différentes actions possibles que peut effectuer le joueur actif
	 * avec la souris en fonction de la phase dans laquelle se trouve la partie.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		Jeu jeu = fenetre.getJeu();

		if (!casePlateau.isEmpty() && !jeu.getActionEffectue() && !jeu.getJetonAttaque() && !jeu.getJetonDeplace()) {
			CaseImage image = (CaseImage) panel;

			if (casePlateau.getPersonnage().equals(panneauJeu.getPersonnageSelectionne()))
				deselectPersonnage(image);
			else
				selectPersonnage(image);

			image.repaint();

		} else if (panneauJeu.getPersonnageSelectionne() != null) {

			switch (fenetre.getJeu().getEtatJeu()) {
			case Jeu.PHASE_SELECTION:
				positionnerPersoUI(panneauJeu);
				break;

			case Jeu.PHASE_ACTION:
				if (jeu.getJetonAttaque()) {
					attaquerPersoUI(panneauJeu);
				} else if (jeu.getJetonDeplace()) {
					deplacerPersoUI(panneauJeu);
				}
				break;

			case Jeu.PHASE_TERMINE:
				break;
			}
		}

		if (jeu.getEtatJeu() == Jeu.PHASE_ACTION) {
			refreshBoutonsActions(fenetre, jeu, panneauJeu);
		}

	}

	/**
	 * Listener pour affiche les caractéristiques dans le panneauInfos lorsque l'utilisateur 
	 * survole un personnage sur le plateau.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (!casePlateau.isEmpty())
			fenetre.getPanneauInfos().refresh(casePlateau.getPersonnage());
	}

	/**
	 * Listener réinitialise le panneauInfos lorsque l'utilisateur ne survole plus un
	 * personnage
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		fenetre.getPanneauInfos().refresh();
	}

}
