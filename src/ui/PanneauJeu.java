package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import partie.Jeu;
import personnages.Personnage;
import plateau.Plateau;

public class PanneauJeu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Jeu jeu;
	private PanneauInfos panneauInfos;
	private Personnage personnageSelectionne;
	


	public PanneauJeu(Jeu jeu, PanneauInfos panneauInfos) {
		this.jeu = jeu;
		this.panneauInfos = panneauInfos;
		refresh();
	}
	
	public void refresh() {
		this.removeAll();
		this.setLayout(new GridLayout(Plateau.NOMBRE_LIGNE,Plateau.NOMBRE_COLONNE));
		
		for(int x=0 ; x<Plateau.NOMBRE_LIGNE ; x++) {
			for(int y=0 ; y<Plateau.NOMBRE_COLONNE ; y++) {
				
				if(jeu.getPlateauJeu().getCase(y, x).getPersonnage() != null) {
					
					Personnage perso = jeu.getPlateauJeu().getCase(y, x).getPersonnage();
					CaseImage caseImage = new CaseImage(perso.getCheminImage(), 80, 80, jeu.getJoueur1().getEquipe().getListePersonnages().contains(perso)? "bleu.png" : "rouge.png");

					if(perso == personnageSelectionne) {
						caseImage.setTransparency(true);
					}
					
					caseImage.addMouseListener(new Souris(jeu.getPlateauJeu().getCase(y, x), caseImage, this, panneauInfos));
					this.add(caseImage);
					
				}else {
					JPanel panel = new JPanel();					
					panel.setBackground((x + y) % 2 == 0 ? Color.DARK_GRAY : Color.LIGHT_GRAY);

					panel.addMouseListener(new Souris(jeu.getPlateauJeu().getCase(y, x), panel, this, panneauInfos));
					
					this.add(panel);	
				}		
			}
		}
		this.revalidate();
		this.repaint();
	}

	
	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public Personnage getPersonnageSelectionne() {
		return personnageSelectionne;
	}

	public void setPersonnageSelectionne(Personnage personnageSelectionne) {
		this.personnageSelectionne = personnageSelectionne;
	}
}