package ui.souris;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import partie.Equipe;
import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import ui.Fenetre;
import ui.panneau.PanneauJeu;
import ui.util.CaseImage;
import util.Util;

public class SourisSelection extends MouseAdapter{

	private Personnage personnage;
	private CaseImage caseImage;
	private Fenetre fenetre;
	/**
	 * Constructeur sourisSelection
	 * 
	 * @param personnage
	 * @param caseImage
	 * @param fenetre
	 */
	public SourisSelection(Personnage personnage, CaseImage caseImage, Fenetre fenetre) {
		this.personnage = personnage;
		this.caseImage = caseImage;
		this.fenetre = fenetre;
	}
	
	/**
	 * Listener permettant d'ajouter un personnage sur le plateau de jeu lorsque l'utilisateur
	 * effetue un click de souris dessus. L'image est alors grisé pour signaler que
	 * le personnage est déjà dans l'équipe. Si l'utilisateur reclique dessus, le
	 * personnage est supprimé du plateau et n'est plus grisé.
	 */
	@Override
    public void mouseClicked(MouseEvent e) {
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		Equipe equipe = fenetre.getJeu().getJoueurActif().getEquipe();
		Plateau plateau = fenetre.getJeu().getPlateauJeu();

		if(equipe.isDansEquipe(personnage)) {
			System.out.println("Suppression de " + personnage.getNom());
			caseImage.setTransparency(null);
			caseImage.repaint();
			equipe.removeEquipe(personnage);
			panneauJeu.setPersonnageSelectionne(null);
			Case previousCase = plateau.getCase(personnage);
			previousCase.setPersonnage(null, null);
			previousCase.getPanel().setTransparency(null);
			previousCase.getPanel().repaint();
			
		}else if(!equipe.isComplete()){
			System.out.println("Ajout de " + personnage.getNom());
			caseImage.setTransparency(Util.getGrayTransparency());
			caseImage.repaint();
			equipe.addEquipe(personnage);
			Case newCase;
			if(fenetre.getJeu().getJoueurActif().equals(fenetre.getJeu().getJoueur1())) {
				newCase = plateau.getFirstCaseLeft();
			}else {
				newCase = plateau.getFirstCaseRight();
			}
			newCase.setPersonnage(personnage, fenetre.getJeu().getJoueurActif().getCouleur() + ".png");
			newCase.getPanel().setTransparency(null);
			newCase.getPanel().repaint();
		}
		if(equipe.isComplete()) {
			System.out.println("Votre equipe est complete, n'oubliez pas de repositionnez vos personnages.");
			fenetre.getPanneauActions().getButtonValider().setEnabled(true);
		}else {
			fenetre.getPanneauActions().getButtonValider().setEnabled(false);
		}
	}
	
	/**
	 * Listener pour afficher les caractéristiques dans le panneauInfos lorsque l'utilisateur 
	 * survole un personnage sur le panneauAction.
	 */
	@Override
    public void mouseEntered(MouseEvent e) {
		if(personnage != null) 
			fenetre.getPanneauInfos().refresh(personnage);
    }

	/**
	 * Listener pour réinitialiser le panneauInfos lorsque l'utilisateur ne survole plus un
	 * personnage
	 */
	@Override
    public void mouseExited(MouseEvent e) {
		fenetre.getPanneauInfos().refresh();
    }
}
