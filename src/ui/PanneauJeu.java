package ui;

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
	
	Jeu jeu;
	JPanel panneauInfos;
	
	public PanneauJeu(Jeu jeu, JPanel panneauInfos) {
		this.jeu = jeu;
		this.panneauInfos = panneauInfos;
		refresh();
	}
	
	public void refresh() {
		this.setLayout(new GridLayout(Plateau.NOMBRE_LIGNE,Plateau.NOMBRE_COLONNE));
		
		for(int x=0 ; x<Plateau.NOMBRE_LIGNE ; x++) {
			for(int y=0 ; y<Plateau.NOMBRE_COLONNE ; y++) {
				
				if(jeu.getPlateauJeu().getCase(y, x).getPersonnage() != null) {
					
					Personnage perso = jeu.getPlateauJeu().getCase(y, x).getPersonnage();
					CaseImage caseImage = new CaseImage(perso.getCheminImage(), 80, 80, jeu.getJoueur1().getEquipe().getListePersonnages().contains(perso)? "bleu.png" : "rouge.png");
					
					caseImage.addMouseListener(new Souris(jeu.getPlateauJeu().getCase(y, x), panneauInfos));
					this.add(caseImage);
					
				}else {
					JPanel panel = new JPanel();					
					panel.setBackground((x + y) % 2 == 0 ? Color.DARK_GRAY : Color.LIGHT_GRAY);

					panel.addMouseListener(new Souris(jeu.getPlateauJeu().getCase(y, x), panneauInfos));
					
					this.add(panel);	
				}
				
			}
		}
	}

}
