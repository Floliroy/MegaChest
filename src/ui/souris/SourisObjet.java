package ui.souris;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import personnages.Personnage;
import ui.CaseImage;
import ui.Fenetre;
import ui.panneau.PanneauActions;
import util.Util;

public class SourisObjet extends MouseAdapter {

	private Personnage personnage;
	private CaseImage caseImage;
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
	
	@Override
    public void mouseClicked(MouseEvent e) {
		PanneauActions panneauActions = fenetre.getPanneauActions();
		
		if(personnage.getListObjets().size() >= Personnage.TAILLE_MAX_LISTE_OBJET) {
			System.out.println("Ce personnage a deja 3 objets ...");
		}else {
			System.out.println("Vous avez choisis " + personnage.getNom());
			CaseImage prevCase = panneauActions.getCaseSelectionne();
			if(prevCase != null) {
				prevCase.setTransparency(null);
				prevCase.repaint();
			}
			
			caseImage.setTransparency(Util.getYellowTransparency());
			caseImage.repaint();
			panneauActions.setCaseSelectionne(caseImage);
			panneauActions.setPersoSelectionne(personnage);
			panneauActions.getButtonEquiper().setEnabled(true);
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
