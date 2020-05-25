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

	/** La fenetre de jeu */
	private Fenetre fenetre;
	/** Le personnage actuellement sélectionné sur le plateau */
	private Personnage personnageSelectionne;
	/** La case actuellement sélectionné sur le plateau */
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
		//On reset les composants de la fenetre
		this.removeAll();
		
		this.setLayout(new GridLayout(Plateau.NOMBRE_LIGNE,Plateau.NOMBRE_COLONNE));
		//On parcours l'ensemble du plateau
		for(int x=0 ; x<Plateau.NOMBRE_LIGNE ; x++) {
			for(int y=0 ; y<Plateau.NOMBRE_COLONNE ; y++) {
				Jeu jeu = fenetre.getJeu();
				Case casePlateau = jeu.getPlateauJeu().getCase(y, x);
				
				CaseImage panel;
				//On regarde si la case contient un personnage
				if(!jeu.getPlateauJeu().getCase(y, x).isEmpty()) {
					
					//On ajoute l'icone de ce personnage ainsi que la couleur de l'équipe en fond
					Personnage perso = jeu.getPlateauJeu().getCase(y, x).getPersonnage();
					panel = new CaseImage(casePlateau.getPersonnage(), 80, 80, jeu.getJoueur1().getEquipe().isDansEquipe(perso)? "blue.png" : "red.png");

					if(perso == personnageSelectionne) {
						//Si c'est le personnage sélectionné on ajoute une transparence jaune
						panel.setTransparency(Util.getYellowTransparency());
					}
					
				}else {
					panel = new CaseImage(null, 80, 80, null);					
				}	
				
				panel.setBackground((x + y) % 2 == 0 ? Color.DARK_GRAY : Color.LIGHT_GRAY);
				//On ajoute un listener pour savoir quand on clique sur la case
				panel.addMouseListener(new SourisJeu(casePlateau, panel, fenetre));
				this.add(panel);		
				//On ajoute la case UI a la case pour faire les operations
				jeu.getPlateauJeu().getCase(y, x).setPanel(panel);
			}
		}
		
		//On reconstruit le panneau
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
