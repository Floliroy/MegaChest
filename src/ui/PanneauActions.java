package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import partie.Jeu;
import personnages.Personnage;
import util.Util;

public class PanneauActions extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Jeu jeu;
	private PanneauInfos panneauInfos;
	
	public PanneauActions(Jeu jeu, PanneauInfos panneauInfos) {
		this.jeu = jeu;
		this.panneauInfos = panneauInfos;
	}
	
	public void showSelection() {
		
		JPanel conteneurPersonnages = new JPanel();
		JPanel conteneurTitre = new JPanel();
		JPanel conteneurBouton = new JPanel();


		
		this.setLayout(new GridLayout(3,1));
		JLabel label = new JLabel("Sélectionnez vos personnages");
		label.setFont(new Font("Calibri", Font.BOLD, 32));
		conteneurTitre.setLayout(new GridBagLayout());
		conteneurTitre.add(label);
		this.add(conteneurTitre);	
		
		conteneurPersonnages.setLayout(new GridLayout(1, Util.listePersonnages().size()));
		for(Personnage personnage : Util.listePersonnages()) {
			JPanel panel = new CaseImage(personnage.getCheminImage(), 80, 80, null);
			
			panel.addMouseListener(new SourisSelection(personnage, panneauInfos));
			conteneurPersonnages.add(panel);
		}
		this.add(conteneurPersonnages);
		
		conteneurBouton.add(new MyButton("Valider", Color.LIGHT_GRAY));
		conteneurBouton.setLayout(new GridBagLayout());
		this.add(conteneurBouton);
	}

}
