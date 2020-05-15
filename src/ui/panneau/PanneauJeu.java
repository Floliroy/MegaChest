package ui.panneau;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import partie.Jeu;
import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import ui.CaseImage;
import ui.Fenetre;
import ui.souris.SourisJeu;
import util.Util;

public class PanneauJeu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Fenetre fenetre;
	private Personnage personnageSelectionne;
	private CaseImage casePersoSelectionne;
	


	public PanneauJeu(Fenetre fenetre) {
		this.fenetre = fenetre;
		this.setBackground(Color.GRAY);
	}
	
	public void refresh() {
		this.removeAll();
		this.setLayout(new GridLayout(Plateau.NOMBRE_LIGNE,Plateau.NOMBRE_COLONNE));
		
		for(int x=0 ; x<Plateau.NOMBRE_LIGNE ; x++) {
			for(int y=0 ; y<Plateau.NOMBRE_COLONNE ; y++) {
				Jeu jeu = fenetre.getJeu();
				Case casePlateau = jeu.getPlateauJeu().getCase(y, x);
				
				CaseImage panel;
				if(jeu.getPlateauJeu().getCase(y, x).getPersonnage() != null) {
					
					Personnage perso = jeu.getPlateauJeu().getCase(y, x).getPersonnage();
					panel = new CaseImage(casePlateau.getPersonnage(), 80, 80, jeu.getJoueur1().getEquipe().getListePersonnages().contains(perso)? "bleu.png" : "rouge.png");

					if(perso == personnageSelectionne) {
						panel.setTransparency(Util.getYellowTransparency());
					}
					
				}else {
					panel = new CaseImage(null, 80, 80, null);					
				}	
				
				panel.setBackground((x + y) % 2 == 0 ? Color.DARK_GRAY : Color.LIGHT_GRAY);
				panel.addMouseListener(new SourisJeu(casePlateau, panel, fenetre));
				this.add(panel);		
				

				jeu.getPlateauJeu().getCase(y, x).setPanel(panel);
			}
		}
		this.revalidate();
		this.repaint();
	}

	public Personnage getPersonnageSelectionne() {
		return personnageSelectionne;
	}

	public void setPersonnageSelectionne(Personnage personnageSelectionne) {
		this.personnageSelectionne = personnageSelectionne;
	}

	public CaseImage getCasePersoSelectionne() {
		return casePersoSelectionne;
	}

	public void setCasePersoSelectionne(CaseImage casePersoSelectionne) {
		this.casePersoSelectionne = casePersoSelectionne;
	}
	
	public void setSelectionne(Personnage personnageSelectionne, CaseImage casePersoSelectionne) {
		this.personnageSelectionne = personnageSelectionne;
		this.casePersoSelectionne = casePersoSelectionne;
	}
}