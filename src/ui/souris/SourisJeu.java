package ui.souris;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import partie.Jeu;
import personnages.Personnage;
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
	
	private void refreshBoutonsActions(Jeu jeu, PanneauJeu panneauJeu) {
		if(panneauJeu.getPersonnageSelectionne() == null) {
			fenetre.getPanneauActions().getButtonAttaquer().setEnabled(false);
			fenetre.getPanneauActions().getButtonDeplacer().setEnabled(false);
		}else {
			Boolean peutAttaquer = false;
			for(Case caseAttaquable : jeu.getPlateauJeu().getCasesAPorte(panneauJeu.getPersonnageSelectionne())) {
				if(!caseAttaquable.isEmpty() && !jeu.getJoueurActif().getEquipe().isDansEquipe(caseAttaquable.getPersonnage())) {
					peutAttaquer = true;
				}
			}
			Boolean peutDeplacer = panneauJeu.getPersonnageSelectionne().getDeplacementsAvecBoost() > 0;
			
			if(!jeu.getAttaqueEffectue() && peutAttaquer) {
				fenetre.getPanneauActions().getButtonAttaquer().setEnabled(true);
			}else {
				fenetre.getPanneauActions().getButtonAttaquer().setEnabled(false);				
			}
			if(peutDeplacer) {
				fenetre.getPanneauActions().getButtonDeplacer().setEnabled(true);
			}else {
				fenetre.getPanneauActions().getButtonDeplacer().setEnabled(false);				
			}
		}
		
		if(jeu.getActionEffectue()) {
			fenetre.getPanneauActions().getButtonPasser().setEnabled(true);    				
		}else {
			fenetre.getPanneauActions().getButtonPasser().setEnabled(false);   
		}
	}
	
	/**
	 * 
	 * @param image
	 */
	private void deselectPersonnage(CaseImage image) {	
    	PanneauJeu panneauJeu = fenetre.getPanneauJeu();
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
			image.setTransparency(Util.getYellowTransparency());
			
		}
    		
	}
	

	/**
	 * 
	 * @param panneauJeu
	 */
	private void attaquerPersoUI(PanneauJeu panneauJeu) {
    	Jeu jeu = fenetre.getJeu();
		if(!casePlateau.isEmpty() && !jeu.getJoueurActif().getEquipe().isDansEquipe(casePlateau.getPersonnage())) {
			Personnage attaquant = jeu.getPersonnageJoue();
			Personnage defenseur = casePlateau.getPersonnage();
			if(jeu.actionAttaquer(attaquant, defenseur)) {
				jeu.setActionEffectue(true);
				jeu.setAttaqueEffectue(true);
				jeu.setJetonAttaque(false);
				
				//On supprime le personnage de son equipe s'il est mort
				if(!attaquant.isVivant()) {
					jeu.getJoueurActif().getEquipe().removeEquipe(attaquant);
					Case casePerso = jeu.getPlateauJeu().getCase(attaquant);
					casePerso.setPersonnage(null, null);
					casePerso.getPanel().setTransparency(null);
					casePerso.getPanel().repaint();
				}
				if(!defenseur.isVivant()) {
					jeu.getJoueurInactif().getEquipe().removeEquipe(defenseur);
					Case casePerso = jeu.getPlateauJeu().getCase(defenseur);
					casePerso.setPersonnage(null, null);
					casePerso.getPanel().setTransparency(null);
					casePerso.getPanel().repaint();
				}
			}
		}
		if(jeu.isFini()) {
			jeu.setEtatJeu(Jeu.PHASE_TERMINE);
			fenetre.getPanneauActions().getButtonAttaquer().setEnabled(false);
			fenetre.getPanneauActions().getButtonDeplacer().setEnabled(false);
			fenetre.getPanneauActions().getButtonPasser().setEnabled(false);
			System.out.println();
			System.out.println(jeu.getGagnant().getNom() + ", tu es le gagant !");
		}
	}
	
	
	/**
	 * 	 
	 * @param panneauJeu
	 */
	private void deplacerPersoUI(PanneauJeu panneauJeu) {
		
    	Case previousCase = fenetre.getJeu().getPlateauJeu().getCase(panneauJeu.getPersonnageSelectionne());
    	Jeu jeu = fenetre.getJeu();
    	
		if(casePlateau.isEmpty() && fenetre.getJeu().getPersonnageJoue() != null && jeu.getJetonDeplace()
		   && jeu.getPersonnageJoue().equals(previousCase.getPersonnage())) {

	    	if(fenetre.getJeu().getPlateauJeu().deplacerPersonnage(fenetre, previousCase, casePlateau)) {
	        	
	        	casePlateau.getPanel().setTransparency(Util.getYellowTransparency());
	        	casePlateau.getPanel().repaint();
		    	
		    	previousCase.getPanel().setTransparency(null);
		    	previousCase.getPanel().repaint(); 
	    	}
	    	
	    	jeu.setJetonDeplace(false);
	    	jeu.setActionEffectue(true);
		}
	}
	
	/**
	 * 
	 * @param panneauJeu
	 */
	private void positionnerPersoUI(PanneauJeu panneauJeu) {
		Case previousCase = fenetre.getJeu().getPlateauJeu().getCase(panneauJeu.getPersonnageSelectionne());
		
		int limite = fenetre.getJeu().getJoueur1().isTour() ? Jeu.NOMBRE_COLONNE_PLACEMENT : Plateau.NOMBRE_COLONNE - Jeu.NOMBRE_COLONNE_PLACEMENT;
		
		if(casePlateau.isEmpty() && fenetre.getJeu().getJoueurActif().getEquipe().isDansEquipe(panneauJeu.getPersonnageSelectionne())
								 && ((fenetre.getJeu().getJoueur1().isTour() && casePlateau.getPositionX() < limite) 
								 || (fenetre.getJeu().getJoueur2().isTour() && casePlateau.getPositionX() >= limite))) {
			
			
			casePlateau.setPersonnage(previousCase.getPersonnage(),
					fenetre.getJeu().getJoueur1().getEquipe().isDansEquipe(panneauJeu.getPersonnageSelectionne()) ? "blue.png" : "red.png");
			casePlateau.getPanel().setTransparency(null);
			casePlateau.getPanel().repaint();
			previousCase.setPersonnage(null,null);
		}
		if(fenetre.getJeu().getJoueur1().isTour() && casePlateau.getPositionX() >= limite) {
			System.out.println("Vous ne pouvez placer votre personnage que dans les 4 premieres colonnes.");
		}
		if(fenetre.getJeu().getJoueur2().isTour() && casePlateau.getPositionX() < limite) {
			System.out.println("Vous ne pouvez placer votre personnage que dans les 4 dernieres colonnes.");
		}
		
		previousCase.getPanel().setTransparency(null);
    	previousCase.getPanel().repaint();
    	panneauJeu.setSelectionne(null, null);   
		
	}
	
	
	@Override
    public void mouseClicked(MouseEvent e) {
		PanneauJeu panneauJeu = fenetre.getPanneauJeu();
		Jeu jeu = fenetre.getJeu();
		
		/*if(panneauJeu.getPersonnageSelectionne() != null) {
			System.out.println("En cours : " + panneauJeu.getPersonnageSelectionne().getNom());
		}*/
        
		if(!casePlateau.isEmpty() && !jeu.getActionEffectue() &&!jeu.getJetonAttaque() && !jeu.getJetonDeplace()) {
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
	        		if(jeu.getJetonAttaque()) {
	        			attaquerPersoUI(panneauJeu);
	        		}else if(jeu.getJetonDeplace()) {
		        		deplacerPersoUI(panneauJeu);
	        		}
	        		break;
	        		
	        	case Jeu.PHASE_TERMINE :
	        		break;
	        }
        }  
		
		if(jeu.getEtatJeu() == Jeu.PHASE_ACTION) {
			refreshBoutonsActions(jeu, panneauJeu);
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
