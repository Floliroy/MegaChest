package ui.souris;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;


import partie.Jeu;
import plateau.Case;
import plateau.Plateau;
import ui.CaseImage;
import ui.Fenetre;

import ui.panneau.PanneauJeu;
import util.Util;

public class SourisJeu extends MouseAdapter{
	
	private Case casePlateau;
	private JPanel panel;
	private Fenetre fenetre;
	

	public SourisJeu(Case casePlateau, JPanel panel, Fenetre fenetre) {
		this.casePlateau = casePlateau;
		this.panel = panel;
		this.fenetre = fenetre;
	}
	
	/**
	 * 
	 * @param image
	 */
	private void deselectPersonnage(CaseImage image) {	
    	PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deselectionne");
    	panneauJeu.setSelectionne(null, null);
    	image.setTransparency(null);
	}
	/**
	 * 
	 * @param image
	 */
	private void selectPersonnage(CaseImage image) {
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		
		if(fenetre.getJeu().getJoueurActif().getEquipe().isDansEquipe(casePlateau.getPersonnage())) {
			
			if(panneauJeu.getCasePersoSelectionne() != null) {
	    		panneauJeu.getCasePersoSelectionne().setTransparency(null);
	    		panneauJeu.getCasePersoSelectionne().repaint();
			}
			
			panneauJeu.setSelectionne(casePlateau.getPersonnage(), (CaseImage) panel);
			System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " selectionne");
			image.setTransparency(Util.getYellowTransparency());
		}
    		
	}
	
	
	/**
	 * 	 * @param panneauJeu
	 */
	private void deplacerPersoUI( PanneauJeu panneauJeu) {
	
    	Case previousCase = fenetre.getJeu().getPlateauJeu().getCase(panneauJeu.getPersonnageSelectionne());
    	
    	/* deplacer() */
    	if(fenetre.getJeu().getPlateauJeu().deplacerPersonnage(fenetre, previousCase, casePlateau)) {
        	
        	casePlateau.getPanel().setTransparency(null);
        	casePlateau.getPanel().repaint();
        	
        	System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deplace");
    	}
    	
    	previousCase.getPanel().setTransparency(null);
    	previousCase.getPanel().repaint();
    	System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " deselectionne");
    	panneauJeu.setSelectionne(null, null);   
	}
	
	
	
	private void positionnerPersoUI(PanneauJeu panneauJeu) {
		Case previousCase = fenetre.getJeu().getPlateauJeu().getCase(panneauJeu.getPersonnageSelectionne());
		
		int limite = fenetre.getJeu().getJoueur1().isTour() ? Jeu.NOMBRE_COLONNE_PLACEMENT : Plateau.NOMBRE_COLONNE - Jeu.NOMBRE_COLONNE_PLACEMENT;
		
		if(casePlateau.isEmpty() && fenetre.getJeu().getJoueurActif().getEquipe().isDansEquipe(panneauJeu.getPersonnageSelectionne())
								 && ((fenetre.getJeu().getJoueur1().isTour() && casePlateau.getPositionX() < limite) 
								 || (fenetre.getJeu().getJoueur2().isTour() && casePlateau.getPositionX() >= limite))) {
			
			System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " Positionne ");
			
			casePlateau.setPersonnage(previousCase.getPersonnage(),
					fenetre.getJeu().getJoueur1().getEquipe().isDansEquipe(panneauJeu.getPersonnageSelectionne()) ? "blue.png" : "red.png");
			casePlateau.getPanel().setTransparency(null);
			casePlateau.getPanel().repaint();
			previousCase.setPersonnage(null,null);
		}
		
		previousCase.getPanel().setTransparency(null);
    	previousCase.getPanel().repaint();
    	System.out.println(panneauJeu.getPersonnageSelectionne().getNom() + " Positionne + deselectionne");
    	panneauJeu.setSelectionne(null, null);   
		
	}
	
	

	
	
	
	
	@Override
    public void mouseClicked(MouseEvent e) {
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		
        System.out.println("Case cliquee : " + (casePlateau.getPositionX()+1) + " , " + (casePlateau.getPositionY()+1) 
       		+ (casePlateau.getPersonnage() != null ? " -> " + casePlateau.getPersonnage().getNom() : "")
       		+ (casePlateau.getPersonnage() != null ? " -> " + casePlateau.getPersonnage().getDeplacements() : ""));
        
		if(!casePlateau.isEmpty()) {
        	CaseImage image = (CaseImage) panel;
        	
        	if(casePlateau.getPersonnage().equals(panneauJeu.getPersonnageSelectionne()))
        		deselectPersonnage(image);
        	else 
        		selectPersonnage(image);
    	
        	image.repaint();
        	
        } else if (panneauJeu.getPersonnageSelectionne() != null) {
        
	        switch(fenetre.getJeu().getEtatJeu()) {
	        	case Jeu.PHASE_SELECTION :
	        		positionnerPersoUI(panneauJeu);
	        		break;
	        		
	        	case Jeu.PHASE_ACTION :
	        		deplacerPersoUI(panneauJeu);
	        		break;
	        		
	        	case Jeu.PHASE_TERMINE :
	        		break;
	        }
        }  
        
    }
	
	@Override
    public void mouseEntered(MouseEvent e) {
		if(!casePlateau.isEmpty()) 
			fenetre.getPanneauInfos().refresh(casePlateau.getPersonnage());
    }
	
	@Override
    public void mouseExited(MouseEvent e) {
		fenetre.getPanneauInfos().refresh();
    }

	public Case getCasePlateau() {
		return casePlateau;
	}

	public void setCasePlateau(Case casePlateau) {
		this.casePlateau = casePlateau;
	}
}
