package ui.souris;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import partie.Equipe;
import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import ui.CaseImage;
import ui.Fenetre;
import ui.panneau.PanneauInfos;
import ui.panneau.PanneauJeu;
import util.Util;

public class SourisSelection extends MouseAdapter{

	private Personnage personnage;
	private CaseImage caseImage;
	private Fenetre fenetre;
	
	public SourisSelection(Personnage personnage, CaseImage caseImage, Fenetre fenetre) {
		this.personnage = personnage;
		this.caseImage = caseImage;
		this.fenetre = fenetre;
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		Equipe equipe = fenetre.getJeu().getJoueurActif().getEquipe();
		Plateau plateau = fenetre.getJeu().getPlateauJeu();

		if(equipe.getListePersonnages().size() < Equipe.TAILLE_EQUIPE) {
			if(equipe.getListePersonnages().contains(personnage)) {
				System.out.println("Suppression de " + personnage.getNom());
				caseImage.setTransparency(null);
				caseImage.repaint();
				equipe.removeEquipe(personnage);
				panneauJeu.setPersonnageSelectionne(null);
				Case previousCase = plateau.getCase(personnage);
				previousCase.setPersonnage(null, null);
				previousCase.getPanel().setTransparency(null);
				previousCase.getPanel().repaint();
				
			}else {
				System.out.println("Ajout de " + personnage.getNom());
				caseImage.setTransparency(Util.getGrayTransparency());
				caseImage.repaint();
				equipe.addEquipe(personnage);
				Case newCase;
				String fond;
				if(fenetre.getJeu().getJoueurActif().equals(fenetre.getJeu().getJoueur1())) {
					newCase = plateau.getFirstCaseLeft();
					fond = "bleu.png";
				}else {
					newCase = plateau.getFirstCaseRight();
					fond = "rouge.png";
				}
				newCase.setPersonnage(personnage, fond);
				newCase.getPanel().setTransparency(null);
				newCase.getPanel().repaint();
			}
		}
		if(equipe.getListePersonnages().size() >= Equipe.TAILLE_EQUIPE) {
			System.out.println("Votre equipe est complete, n'oubliez pas de repositionnez vos personnages.");
			fenetre.getPanneauActions().getButtonValider().setEnabled(true);
		}else {
			fenetre.getPanneauActions().getButtonValider().setEnabled(false);
		}
	}
	
	@Override
    public void mouseEntered(MouseEvent e) {
		if(personnage != null) 
			fenetre.getPanneauInfos().refresh(personnage);
    }
	
	@Override
    public void mouseExited(MouseEvent e) {
		fenetre.getPanneauInfos().refresh();
    }
}
