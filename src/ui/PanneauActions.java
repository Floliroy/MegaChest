package ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import personnages.Personnage;
import util.Util;

public class PanneauActions extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanneauActions() {
		
	}
	
	public void showSelection() {
		
		JPanel conteneurPersonnages = new JPanel();
		JPanel conteneurTitre = new JPanel();
		JPanel conteneurBouton = new JPanel();


		
		this.setLayout(new GridLayout(3,1));
		conteneurTitre.add(new JLabel("Test"));
		this.add(conteneurTitre);	
		
		conteneurPersonnages.setLayout(new GridLayout(1, Util.listePersonnages().size()));
		for(Personnage personnage : Util.listePersonnages()) {
			JPanel panel = new CaseImage(personnage.getCheminImage(), 80, 80, null);
			conteneurPersonnages.add(panel);
		}
		this.add(conteneurPersonnages);
		
		conteneurBouton.add(new JButton("Test"));
		this.add(conteneurBouton);
	}

}
