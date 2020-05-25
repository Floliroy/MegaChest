package ui.souris;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import personnages.Personnage;
import ui.Fenetre;
import ui.panneau.PanneauActions;
import ui.util.CaseImage;
import util.Util;

public class SourisObjet extends MouseAdapter {

	/** La personnage concerné par le listener */
	private Personnage personnage;
	/** La case concerné par le listener */
	private CaseImage caseImage;
	/** La fenetre de jeu */
	private Fenetre fenetre;
	
	/**
	 * Constructeur souris objet
	 * 
	 * @param personnage
	 * @param caseImage
	 * @param fenetre
	 */
	public SourisObjet(Personnage personnage, CaseImage caseImage, Fenetre fenetre) {
		this.personnage = personnage;
		this.caseImage = caseImage;
		this.fenetre = fenetre;
	}
	
	/**
	 * Listener permettant d'ajouter un objet a un personnage, un seul personnage est sélectionnabel.
	 * Si le personnage a deja 3 objets on ne peut pas le selectionner
	 * Si on souhaite choisir un autre personnage on deselectionnera l'ancien
	 */
	@Override
    public void mouseClicked(MouseEvent e) {
		PanneauActions panneauActions = fenetre.getPanneauActions();
		
		//On regarde si le personnage peut encore équiper des objets
		if(personnage.getListObjets().size() >= Personnage.TAILLE_MAX_LISTE_OBJET) {
			System.out.println("Ce personnage a deja 3 objets ...");
		}else {
			System.out.println("Vous avez choisis " + personnage.getNom());
			CaseImage prevCase = panneauActions.getCaseSelectionne();
			//On regarde s'il y avait un personnage sur l'ancienne case selectionne
			if(prevCase != null) {
				prevCase.setTransparency(null);
				prevCase.repaint();
			}
			
			//On actualise le personnage (et sa case) sélectionné
			caseImage.setTransparency(Util.getYellowTransparency());
			caseImage.repaint();
			panneauActions.setCaseSelectionne(caseImage);
			panneauActions.setPersoSelectionne(personnage);
			panneauActions.getButtonEquiper().setEnabled(true);
		}
	}
	
	/**
	 * Listener pour afficher les caractéristiques dans le panneauInfos lorsque l'utilisateur survole un personnage sur le panneauAction
	 */
	@Override
    public void mouseEntered(MouseEvent e) {
		if(personnage != null) 
			fenetre.getPanneauInfos().refresh(personnage);
    }

	/**
	 * Listener pour réinitialiser le panneauInfos lorsque l'utilisateur ne survole plus un personnage
	 */
	@Override
    public void mouseExited(MouseEvent e) {
		fenetre.getPanneauInfos().refresh();
    }
}
