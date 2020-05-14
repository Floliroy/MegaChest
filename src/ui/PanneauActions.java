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

	private PanneauJeu panneauJeu;
	private PanneauInfos panneauInfos;
	
	public PanneauActions(PanneauJeu panneauJeu, PanneauInfos panneauInfos) {
		this.panneauJeu = panneauJeu;
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
			CaseImage panel = new CaseImage(personnage, 80, 80, null);
			
			panel.addMouseListener(new SourisSelection(personnage, panel, panneauJeu, panneauInfos));
			conteneurPersonnages.add(panel);
		}
		this.add(conteneurPersonnages);
		
		conteneurBouton.add(new MyButton("Valider", Color.LIGHT_GRAY));
		conteneurBouton.setLayout(new GridBagLayout());
		this.add(conteneurBouton);
	}

}
