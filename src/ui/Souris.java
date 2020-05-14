package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import plateau.Case;

public class Souris extends MouseAdapter{
	
	private Case casePlateau;
	private PanneauInfos panneauInfos;
	private PanneauJeu panneauJeu;
	private JPanel panel;
	
	public Souris(Case casePlateau, JPanel panel, PanneauJeu panneauJeu, PanneauInfos panneauInfos) {
		this.casePlateau = casePlateau;
		this.panneauInfos = panneauInfos;
		this.panneauJeu = panneauJeu;
		this.panel = panel;
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Case cliquee : " + (casePlateau.getPositionX()+1) + " , " + (casePlateau.getPositionY()+1) 
        		+ (casePlateau.getPersonnage() != null ? " -> " + casePlateau.getPersonnage().getNom() : ""));
        		
        if(!casePlateau.isEmpty()) {
        	CaseImage image = (CaseImage) panel;
        	if(casePlateau.getPersonnage().equals(panneauJeu.getPersonnageSelectionne())) {
        		System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deselectionne");
        		panneauJeu.setSelectionne(null, null);
        		image.setTransparency(false);
        	} else {
        		if(panneauJeu.getCasePersoSelectionne() != null) {
            		panneauJeu.getCasePersoSelectionne().setTransparency(false);
            		panneauJeu.getCasePersoSelectionne().repaint();
        		}
        		
        		panneauJeu.setSelectionne(casePlateau.getPersonnage(), (CaseImage) panel);
        		System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " selectionne");
        		image.setTransparency(true);
        	}	
        	image.repaint();
        } else if (panneauJeu.getPersonnageSelectionne() != null) {
        	System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deplace");
        	panneauJeu.getJeu().getPlateauJeu().getCase(panneauJeu.getPersonnageSelectionne()).setPersonnage(null);
        	casePlateau.setPersonnage(panneauJeu.getPersonnageSelectionne());
        	panneauJeu.setSelectionne(null, null);
        	panneauJeu.refresh();
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
