package ui.souris;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import ui.CaseImage;
import ui.panneau.PanneauInfos;
import ui.panneau.PanneauJeu;
import util.Util;

public class SourisJeu extends MouseAdapter{
	
	private Plateau plateauJeu;
	private Case casePlateau;
	private PanneauInfos panneauInfos;
	private PanneauJeu panneauJeu;
	private JPanel panel;
	
	public SourisJeu(Plateau plateauJeu, Case casePlateau, JPanel panel, PanneauJeu panneauJeu, PanneauInfos panneauInfos) {
		this.plateauJeu = plateauJeu;
		this.casePlateau = casePlateau;
		this.panneauInfos = panneauInfos;
		this.panneauJeu = panneauJeu;
		this.panel = panel;
	}
	
	
	private void selectPersonnage(CaseImage image) {	
    	System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deselectionne");
    	panneauJeu.setSelectionne(null, null);
    	image.setTransparency(null);
	}
	
	private void deselectPersonnage(CaseImage image) {
		if(panneauJeu.getCasePersoSelectionne() != null) {
    		panneauJeu.getCasePersoSelectionne().setTransparency(null);
    		panneauJeu.getCasePersoSelectionne().repaint();
		}
		
		panneauJeu.setSelectionne(casePlateau.getPersonnage(), (CaseImage) panel);
		System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " selectionne");
		image.setTransparency(Util.getYellowTransparency());
	}

	
	
	
	@Override
    public void mouseClicked(MouseEvent e) {
		
        System.out.println("Case cliquee : " + (casePlateau.getPositionX()+1) + " , " + (casePlateau.getPositionY()+1) 
       		+ (casePlateau.getPersonnage() != null ? " -> " + casePlateau.getPersonnage().getNom() : "")
       		+ (casePlateau.getPersonnage() != null ? " -> " + casePlateau.getPersonnage().getDeplacements() : ""));
        
    	
		if(!casePlateau.isEmpty()) {
        	CaseImage image = (CaseImage) panel;
        	
        	if(casePlateau.getPersonnage().equals(panneauJeu.getPersonnageSelectionne()))
        		selectPersonnage(image);
        	else 
        		deselectPersonnage(image);
    	
        	image.repaint();
        	
        } else if (panneauJeu.getPersonnageSelectionne() != null) {
        	
        	
        	Case previousCase = panneauJeu.getJeu().getPlateauJeu().getCase(panneauJeu.getPersonnageSelectionne());
        	
        	if(plateauJeu.deplacerPersonnage(panneauJeu, previousCase, casePlateau)) {
            	
            	casePlateau.getPanel().setTransparency(null);
            	casePlateau.getPanel().repaint();
            	
            	System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deplace");
        	}
        	
        	previousCase.getPanel().setTransparency(null);
        	previousCase.getPanel().repaint();
        	System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deselectionne");
        	panneauJeu.setSelectionne(null, null);
        	
      
        	
        }
        
    }
	
	@Override
    public void mouseEntered(MouseEvent e) {
		if(casePlateau.getPersonnage() != null) 
			panneauInfos.refresh(casePlateau.getPersonnage());
    }
	
	@Override
    public void mouseExited(MouseEvent e) {
		panneauInfos.refresh();
    }

	public Case getCasePlateau() {
		return casePlateau;
	}

	public void setCasePlateau(Case casePlateau) {
		this.casePlateau = casePlateau;
	}
}
