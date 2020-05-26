package ui.souris;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JPanel;

import partie.Equipe;
import partie.Jeu;
import partie.Joueur;
import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import ui.Fenetre;
import ui.panneau.PanneauJeu;
import ui.popup.PopUpEnd;
import ui.util.CaseImage;
import util.SaveManager;
import util.Util;

/**
 * SourisJeu permet d'effectuer toutes les actions correspondantes pour chaques action réaliser par l'utilisateur avec la souris sur le panneauJeu.
 */
public class SourisJeu extends MouseAdapter {

	/** La case concerné par le listener */
	private Case casePlateau;
	/** La panel concerné par le listener */
	private JPanel panel;
	/** La fenetre de jeu */
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
	 * Actualise les boutons d'actions du panneau panneauAction en fonction des actions que l'utilisateur peut effectuer.
	 * 
	 * @param jeu 
	 * @param panneauJeu
	 */
	public static void refreshBoutonsActions(Fenetre fenetre) {
		Jeu jeu = fenetre.getJeu();
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		
		if (panneauJeu.getPersonnageSelectionne() == null) {
			//S'il n'y a pas de personnage selectionne on désactive les boutons d'attaque et de deplacement
			fenetre.getPanneauActions().getButtonAttaquer().setEnabled(false);
			fenetre.getPanneauActions().getButtonDeplacer().setEnabled(false);
		} else {
			Boolean peutAttaquer = false;
			//Un personnage peut attaque s'il a des adversaires a portees
			if (panneauJeu.getPersonnageSelectionne().isVivant()) {
				for (Case caseAttaquable : jeu.getPlateauJeu().getCasesAPorte(panneauJeu.getPersonnageSelectionne())) {
					if (!caseAttaquable.isEmpty()
							&& !jeu.getJoueurActif().getEquipe().isDansEquipe(caseAttaquable.getPersonnage())) {
						peutAttaquer = true;
					}
				}
			}
			//Un personnage peut se deplacer s'il lui reste des PMs
			Boolean peutDeplacer = panneauJeu.getPersonnageSelectionne().getDeplacementsAvecBoost() > 0;

			//Une seule attaque disponible par tour
			if (!jeu.getAttaqueEffectue() && peutAttaquer) {
				fenetre.getPanneauActions().getButtonAttaquer().setEnabled(true);
			} else {
				fenetre.getPanneauActions().getButtonAttaquer().setEnabled(false);
			}
			//Si le personnage est mort durant son tour il ne peut plus se deplacer
			if (peutDeplacer && panneauJeu.getPersonnageSelectionne().isVivant()) {
				fenetre.getPanneauActions().getButtonDeplacer().setEnabled(true);
			} else {
				fenetre.getPanneauActions().getButtonDeplacer().setEnabled(false);
			}
		}

		//Si on a effectué au moins une action ou que le personnage joué est mort on peut passer son tour
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


		//On verifie que le personnage à sélectionner appartient bien à l'équipe du joueur actif
		if (fenetre.getJeu().getJoueurActif().getEquipe().isDansEquipe(casePlateau.getPersonnage())) {

			//Si un personnage est déjà sélectionné, on supprime la sélection
			if (panneauJeu.getCasePersoSelectionne() != null) {
				panneauJeu.getCasePersoSelectionne().setTransparency(null);
			}
			//On met à jours la sélection avec le bon personnage en surbrillance
			panneauJeu.setSelectionne(casePlateau.getPersonnage(), image);
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
		//On verifie qu'on a bien cliqué sur un personnage ennemi
		if (!casePlateau.isEmpty() && !jeu.getJoueurActif().getEquipe().isDansEquipe(casePlateau.getPersonnage())) {
			Personnage attaquant = jeu.getPersonnageJoue();
			Personnage defenseur = casePlateau.getPersonnage();
			
			//On effectue l'attaque entre les deux personnages
			if (jeu.actionAttaquer(attaquant, defenseur)) {
				//On met a jour les jetons et flags correspondant a l'attaque
				jeu.setActionEffectue(true);
				jeu.setAttaqueEffectue(true);
				jeu.setJetonAttaque(false);

				// On supprime les personnages de leurs equipes s'ils sont morts
				Joueur joueurEquipement = null;
				if (!attaquant.isVivant()) {
					jeu.getJoueurActif().getEquipe().removeEquipe(attaquant);
					Case casePerso = jeu.getPlateauJeu().getCase(attaquant);
					casePerso.setPersonnage(null, null);
					/*casePerso.getPanel().setTransparency(null);
					casePerso.getPanel().repaint();*/
					
					//Si l'equipe a perdu deux personnages le joueur adverse gagne un objet
					if(!jeu.isFini() && jeu.getJoueurActif().getEquipe().getListePersonnages().size() % 2 == Equipe.TAILLE_EQUIPE % 2) {
						joueurEquipement = jeu.getJoueurInactif();
						jeu.setJetonEquipement(jeu.getJetonEquipement()+1);
					}
				}
				if (!defenseur.isVivant()) {
					jeu.getJoueurInactif().getEquipe().removeEquipe(defenseur);
					Case casePerso = jeu.getPlateauJeu().getCase(defenseur);
					casePerso.setPersonnage(null, null);
					/*casePerso.getPanel().setTransparency(null);
					casePerso.getPanel().repaint();*/
					
					//Si l'equipe adverse a perdu deux personnages le joueur gagne un objet
					if(!jeu.isFini() && jeu.getJoueurInactif().getEquipe().getListePersonnages().size() % 2 == Equipe.TAILLE_EQUIPE % 2) {
						joueurEquipement = jeu.getJoueurActif();
						jeu.setJetonEquipement(jeu.getJetonEquipement()+1);
					}
				}
				//Si un joueur a gagné un equipement on lance la methode correspondante
				if(joueurEquipement != null) {
					fenetre.getPanneauActions().showChoixObjet(joueurEquipement);
				}
			}
			//On met a jour le panneau de jeu pour enlever la zone attaquable
			panneauJeu.refresh();
		}
		//On regarde si le jeu est fini
		if (jeu.isFini()) {
			jeu.setEtatJeu(Jeu.PHASE_TERMINE);
			
			File file = new File(SaveManager.SAVE);
			//On supprime le fichier de sauvegarde
			if(file.exists()) {
				file.delete();
			}
			
			//On enleve la possibilité d'effectuer des actions
			fenetre.getPanneauActions().getButtonAttaquer().setEnabled(false);
			fenetre.getPanneauActions().getButtonDeplacer().setEnabled(false);
			fenetre.getPanneauActions().getButtonPasser().setEnabled(false);
			
			//On affiche le gagnant
			System.out.println();
			System.out.println(jeu.getGagnant().getNom() + ", tu es le gagnant !");
			PopUpEnd popUpEnd = new PopUpEnd(fenetre, jeu.getGagnant());
			popUpEnd.setVisible(true);
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

		//On verifie que le deplacement est bien autorisé
		if (casePlateau.isEmpty() && fenetre.getJeu().getPersonnageJoue() != null && jeu.getJetonDeplace()
				&& jeu.getPersonnageJoue().equals(previousCase.getPersonnage())) {

			//On verifie que le deplacement est bien effectué
			if (fenetre.getJeu().getPlateauJeu().deplacerPersonnage(fenetre, previousCase, casePlateau)) {

				/*casePlateau.getPanel().setTransparency(Util.getYellowTransparency());
				casePlateau.getPanel().repaint();

				previousCase.getPanel().setTransparency(null);
				previousCase.getPanel().repaint();*/
				

				//On met a jour les jetons et flags correspondant au deplacement
				jeu.setJetonDeplace(false);
				jeu.setActionEffectue(true);
				//On met a jour le panneau de jeu pour enlever la zone de deplacement
				panneauJeu.refresh();
			}

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

		//On determine la limite dans laquelle le joueur peut postionner ses personnages
		int limite = fenetre.getJeu().getJoueur1().isTour() ? Jeu.NOMBRE_COLONNE_PLACEMENT
				: Plateau.NOMBRE_COLONNE - Jeu.NOMBRE_COLONNE_PLACEMENT;

		//On verifie que la case cliquée est libre et dans la limite
		if (casePlateau.isEmpty()
				&& fenetre.getJeu().getJoueurActif().getEquipe().isDansEquipe(panneauJeu.getPersonnageSelectionne())
				&& ((fenetre.getJeu().getJoueur1().isTour() && casePlateau.getPositionX() < limite)
						|| (fenetre.getJeu().getJoueur2().isTour() && casePlateau.getPositionX() >= limite))) {

			//On place le personnage sur la case avec la couleur de l'equipe en fond
			casePlateau.setPersonnage(previousCase.getPersonnage(),
					fenetre.getJeu().getJoueur1().getEquipe().isDansEquipe(panneauJeu.getPersonnageSelectionne())
							? "blue.png"
							: "red.png");
			casePlateau.getPanel().setTransparency(null);
			casePlateau.getPanel().repaint();
			previousCase.setPersonnage(null, null);
		}
		//Si en dehors de la limite on l'indique au joueur
		if (fenetre.getJeu().getJoueur1().isTour() && casePlateau.getPositionX() >= limite) {
			System.out.println("Vous ne pouvez placer votre personnage que dans les 4 premieres colonnes.");
		}
		if (fenetre.getJeu().getJoueur2().isTour() && casePlateau.getPositionX() < limite) {
			System.out.println("Vous ne pouvez placer votre personnage que dans les 4 dernieres colonnes.");
		}

		//On reset la derniere case cliquée
		previousCase.getPanel().setTransparency(null);
		previousCase.getPanel().repaint();
		panneauJeu.setSelectionne(null, null);

	}

	/**
	 * Gère les différentes actions possibles que peut effectuer le joueur actif avec la souris en fonction de la phase dans laquelle se trouve la partie.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		Jeu jeu = fenetre.getJeu();

		//Si on clique sur un personnage et qu'il n'y a pas de jeton d'action en cours et qu'on a pas deja effectué une action ce tour
		if (!casePlateau.isEmpty() && !jeu.getActionEffectue() && !jeu.getJetonAttaque() && !jeu.getJetonDeplace()) {
			CaseImage image = (CaseImage) panel;

			//Si c'est le personnage sélectionné on le déselectionne
			if (casePlateau.getPersonnage().equals(panneauJeu.getPersonnageSelectionne()))
				deselectPersonnage(image);
			else
				selectPersonnage(image);

			panneauJeu.refresh();

		} else if (panneauJeu.getPersonnageSelectionne() != null) {
			//Si on a un jeton et un personnage deja sélectionné
			
			switch (fenetre.getJeu().getEtatJeu()) {
			case Jeu.PHASE_SELECTION:
				//En phase de selection on positionne son equipe
				positionnerPersoUI(panneauJeu);
				break;

			case Jeu.PHASE_ACTION:
				//En phase d'action on renvoit sur l'action souhaitée en fonction du jeton disponible
				if (jeu.getJetonAttaque()) {
					attaquerPersoUI(panneauJeu);
				} else if (jeu.getJetonDeplace()) {
					deplacerPersoUI(panneauJeu);
				}
				break;

			case Jeu.PHASE_TERMINE:
				//En phase de jeu termine on ne fait rien
				break;
			}
		}

		//Si on est en phase d'action on met a jour les boutons d'actions en fonction de ce qu'il vient de se passer
		if (jeu.getEtatJeu() == Jeu.PHASE_ACTION) {
			refreshBoutonsActions(fenetre);
		}

	}

	/**
	 * Listener pour affiche les caractéristiques dans le panneauInfos lorsque l'utilisateur survole un personnage sur le plateau
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (!casePlateau.isEmpty())
			fenetre.getPanneauInfos().refresh(casePlateau.getPersonnage());
	}

	/**
	 * Listener réinitialise le panneauInfos lorsque l'utilisateur ne survole plus un personnage
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		fenetre.getPanneauInfos().refresh();
	}

}
