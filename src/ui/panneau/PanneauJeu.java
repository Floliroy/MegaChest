package ui.panneau;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import partie.Jeu;
import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import ui.Fenetre;
import ui.souris.SourisJeu;
import ui.util.CaseImage;
import util.Util;

public class PanneauJeu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Fenetre fenetre;
	private Personnage personnageSelectionne;
	private CaseImage casePersoSelectionne;
	

	/**
	 * Constructeur de panneauJeu
	 * 
	 * @param fenetre JFrame principale
	 */
	public PanneauJeu(Fenetre fenetre) {
		this.fenetre = fenetre;
		this.setBackground(Color.GRAY);
	}
	
	/**
	 * Affiche l'ensemble du plateau avec les infos actualisés pour chaques
	 * ajout, déplacement, suppression de personnages.
	 */
	public void refresh() {
		this.removeAll();
		this.setLayout(new GridLayout(Plateau.NOMBRE_LIGNE,Plateau.NOMBRE_COLONNE));
		
		for(int x=0 ; x<Plateau.NOMBRE_LIGNE ; x++) {
			for(int y=0 ; y<Plateau.NOMBRE_COLONNE ; y++) {
				Jeu jeu = fenetre.getJeu();
				Case casePlateau = jeu.getPlateauJeu().getCase(y, x);
				
				CaseImage panel;
				if(!jeu.getPlateauJeu().getCase(y, x).isEmpty()) {
					
					Personnage perso = jeu.getPlateauJeu().getCase(y, x).getPersonnage();
					panel = new CaseImage(casePlateau.getPersonnage(), 80, 80, jeu.getJoueur1().getEquipe().isDansEquipe(perso)? "blue.png" : "red.png");

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

	/**
	 * Getter de personnageSelectionne
	 * 
	 * @return personnageSelectionne
	 */
	public Personnage getPersonnageSelectionne() {
		return personnageSelectionne;
	}

	/**
	 * Setter de personnageSelectionne
	 * 
	 * @param personnageSelectionne nouveau personnage sélectionné
	 */
	public void setPersonnageSelectionne(Personnage personnageSelectionne) {
		this.personnageSelectionne = personnageSelectionne;
	}

	/**
	 * Getter de casePersoSelectionne
	 * 
	 * @return casePersoSelectionne
	 */
	public CaseImage getCasePersoSelectionne() {
		return casePersoSelectionne;
	}

	/**
	 * Setter de casePersoSelectionne
	 * 
	 * @param casePersoSelectionne nouvelle CaseImage sélectionnée
	 */
	public void setCasePersoSelectionne(CaseImage casePersoSelectionne) {
		this.casePersoSelectionne = casePersoSelectionne;
	}
	
	/**
	 * Permet de set personnageSelectionne et casePersoSelectionne
	 * 
	 * @param personnageSelectionne nouveau personnage sélectionné
	 * @param casePersoSelectionne nouvelle CaseImage sélectionnée
	 */
	public void setSelectionne(Personnage personnageSelectionne, CaseImage casePersoSelectionne) {
		this.personnageSelectionne = personnageSelectionne;
		this.casePersoSelectionne = casePersoSelectionne;
	}
}
