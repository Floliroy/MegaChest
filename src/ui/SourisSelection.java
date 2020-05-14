package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import partie.Equipe;
import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import util.Util;

public class SourisSelection extends MouseAdapter{

	private Personnage personnage;
	private PanneauJeu panneauJeu;
	private PanneauInfos panneauInfos;
	private CaseImage caseImage;
	
	public SourisSelection(Personnage personnage, CaseImage caseImage, PanneauJeu panneauJeu, PanneauInfos panneauInfos) {
		this.personnage = personnage;
		this.caseImage = caseImage;
		this.panneauJeu = panneauJeu;
		this.panneauInfos = panneauInfos;
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
		Equipe equipe = panneauJeu.getJeu().getJoueurActif().getEquipe();
		Plateau plateau = panneauJeu.getJeu().getPlateauJeu();
		
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
			if(panneauJeu.getJeu().getJoueurActif().equals(panneauJeu.getJeu().getJoueur1())) {
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
	
	@Override
    public void mouseEntered(MouseEvent e) {
		if(personnage != null) 
			panneauInfos.refresh(personnage);
    }
	
	@Override
    public void mouseExited(MouseEvent e) {
		panneauInfos.refresh();
    }
}
