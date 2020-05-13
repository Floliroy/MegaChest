package ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import plateau.Case;

public class Souris extends MouseAdapter{
	
	private Case casePlateau;
	private JPanel panneauInfos;
	private PanneauJeu panneauJeu;
	
	public Souris(Case casePlateau, PanneauJeu panneauJeu, JPanel panneauInfos) {
		this.casePlateau = casePlateau;
		this.panneauInfos = panneauInfos;
		this.panneauJeu = panneauJeu;
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Case clique : " + (casePlateau.getPositionX()+1) + " , " + (casePlateau.getPositionY()+1) 
        		+ (casePlateau.getPersonnage() != null ? " -> " + casePlateau.getPersonnage().getNom() : ""));
        		
        if(!casePlateau.isEmpty()) {
        	if(casePlateau.getPersonnage().equals(panneauJeu.getPersonnageSelectionne())) {
        		System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deselectionne");
        		panneauJeu.setPersonnageSelectionne(null);
        		
        	} else {
        		panneauJeu.setPersonnageSelectionne(casePlateau.getPersonnage());
        		System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " selectionne");
        	}	
        } else if (panneauJeu.getPersonnageSelectionne() != null) {
        	System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deplace");
        	panneauJeu.getJeu().getPlateauJeu().getCase(panneauJeu.getPersonnageSelectionne()).setPersonnage(null);
        	casePlateau.setPersonnage(panneauJeu.getPersonnageSelectionne());
        	panneauJeu.setPersonnageSelectionne(null);
        	panneauJeu.refresh();
        }
        
    }
	
	@Override
    public void mouseEntered(MouseEvent e) {
		if(casePlateau.getPersonnage() != null) {
			panneauInfos.setBackground(Color.GREEN);
		}else {
			panneauInfos.setBackground(Color.PINK);
		}
    }
	
	@Override
    public void mouseExited(MouseEvent e) {
		panneauInfos.setBackground(Color.RED);
    }

	public Case getCasePlateau() {
		return casePlateau;
	}

	public void setCasePlateau(Case casePlateau) {
		this.casePlateau = casePlateau;
	}
}
