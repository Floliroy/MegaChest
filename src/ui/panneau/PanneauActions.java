package ui.panneau;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import personnages.Personnage;
import ui.Actions;
import ui.CaseImage;
import ui.Fenetre;
import ui.souris.SourisSelection;
import ui.util.MyButton;
import ui.util.MyPanel;
import util.Util;

public class PanneauActions extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Fenetre fenetre;
	private JButton buttonValider;
	private JButton buttonAttaquer;
	private JButton buttonDeplacer;
	private JButton buttonPasser;
	
	public PanneauActions(Fenetre fenetre) {
		this.fenetre = fenetre;
	}
	
	public void showSelection() {
		this.removeAll();

		JPanel conteneurTitre = new JPanel();
		JPanel conteneurPersonnages = new JPanel();
		JPanel conteneurBouton = new JPanel();
		
		this.setLayout(new GridLayout(3,1));
		
		String htmlHeader = "<html>"
						  + "<style>"
						  + "span{"
						  + "	color: " + fenetre.getJeu().getJoueurActif().getCouleur() + "; "
						  + "}"
						  + "</style>";
		String htmlFooter = "</html>";
		
		JLabel label = new JLabel(htmlHeader + "<span>" + fenetre.getJeu().getJoueurActif().getNom() + "</span> : Sélectionnez et Placez vos Personnages" + htmlFooter);
		label.setFont(new Font("Calibri", Font.BOLD, 32));
		conteneurTitre.setLayout(new GridBagLayout());
		conteneurTitre.add(label);
		this.add(conteneurTitre);	
		
		conteneurPersonnages.setLayout(new GridLayout(1, Util.listePersonnages().size()));
		for(Personnage personnage : Util.listePersonnages()) {
			CaseImage panel = new CaseImage(personnage, 80, 80, null);
			
			panel.addMouseListener(new SourisSelection(personnage, panel, fenetre));
			conteneurPersonnages.add(panel);
		}
		this.add(conteneurPersonnages);
		
		buttonValider = new MyButton("Valider", Color.LIGHT_GRAY);
		buttonValider.addActionListener(new Actions(fenetre, Actions.ACTION_VALIDER));
		buttonValider.setEnabled(false);
		
		conteneurBouton.add(buttonValider);
		conteneurBouton.setLayout(new GridBagLayout());
		this.add(conteneurBouton);
		
		this.revalidate();
		this.repaint();
	}
	
	public void refreshActions() {
		this.removeAll();
		
		JPanel conteneurTitre = new JPanel();
		JPanel conteneurActions = new JPanel();
		JPanel conteneurVide = new JPanel();
		
		this.setLayout(new GridLayout(3,1));
		
		String htmlHeader = "<html>"
				  + "<style>"
				  + "span{"
				  + "	color: " + fenetre.getJeu().getJoueurActif().getCouleur() + "; "
				  + "}"
				  + "</style>";
		String htmlFooter = "</html>";

		JLabel label = new JLabel(htmlHeader + "<span>" + fenetre.getJeu().getJoueurActif().getNom() + "</span> : Choisissez votre Personnage et son Action" + htmlFooter);
		label.setFont(new Font("Calibri", Font.BOLD, 32));
		conteneurTitre.setLayout(new GridBagLayout());
		conteneurTitre.add(label);
		this.add(conteneurTitre);
		
		conteneurActions.setLayout(new GridLayout(1, 3));
		buttonAttaquer = new MyButton("Attaquer", Color.ORANGE);
		buttonAttaquer.addActionListener(new Actions(fenetre, Actions.ACTION_ATTAQUER));
		buttonDeplacer = new MyButton("Deplacer", Color.GREEN);
		buttonDeplacer.addActionListener(new Actions(fenetre, Actions.ACTION_DEPLACER));
		buttonPasser = new MyButton("Passer Tour", Color.LIGHT_GRAY);
		buttonPasser.addActionListener(new Actions(fenetre, Actions.ACTION_PASSER_TOUR));
		buttonPasser.setEnabled(false);
		conteneurActions.add(new MyPanel(buttonAttaquer));
		conteneurActions.add(new MyPanel(buttonDeplacer));
		conteneurActions.add(new MyPanel(buttonPasser));
		this.add(conteneurActions);
		
		this.add(conteneurVide);
		this.revalidate();
		this.repaint();
	}

	public JButton getButtonAttaquer() {
		return buttonAttaquer;
	}

	public void setButtonAttaquer(JButton buttonAttaquer) {
		this.buttonAttaquer = buttonAttaquer;
	}

	public JButton getButtonDeplacer() {
		return buttonDeplacer;
	}

	public void setButtonDeplacer(JButton buttonDeplacer) {
		this.buttonDeplacer = buttonDeplacer;
	}

	public JButton getButtonRetour() {
		return buttonPasser;
	}

	public void setButtonRetour(JButton buttonRetour) {
		this.buttonPasser = buttonRetour;
	}

	public JButton getButtonValider() {
		return buttonValider;
	}

	public void setButtonValider(JButton buttonValider) {
		this.buttonValider = buttonValider;
	}

}
