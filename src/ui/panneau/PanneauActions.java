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

	/** La fenetre de jeu */
	private Fenetre fenetre;
	/** Le bouton pour valider son équipe */
	private JButton buttonValider;
	/** Le bouton pour indiquer que l'on souhaite attaquer */
	private JButton buttonAttaquer;
	/** Le bouton pour indiquer que l'on souhaite se déplacer */
	private JButton buttonDeplacer;
	/** Le bouton pour indiquer que l'on souhaite passer son tour */
	private JButton buttonPasser;
	
	/**
	 * Constructeur de panneau actions
	 * @param fenetre La fenetre de jeu
	 */
	public PanneauActions(Fenetre fenetre) {
		this.fenetre = fenetre;
	}
	
	/**
	 * Permet d'afficher le panneau correspondant à la sélection et au placement des personnage de son équipe
	 */
	public void showSelection() {
		this.removeAll();

		JPanel conteneurTitre = new JPanel();
		JPanel conteneurPersonnages = new JPanel();
		JPanel conteneurBouton = new JPanel();
		
		this.setLayout(new GridLayout(3,1));
		
		//Titre (ligne 1) 
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
		
		//Liste des personnages dispo (ligne 2)
		conteneurPersonnages.setLayout(new GridLayout(1, Util.listePersonnages().size()));
		for(Personnage personnage : Util.listePersonnages()) {
			CaseImage panel = new CaseImage(personnage, 80, 80, null);
			
			panel.addMouseListener(new SourisSelection(personnage, panel, fenetre));
			conteneurPersonnages.add(panel);
		}
		this.add(conteneurPersonnages);
		
		//Bouton pour valider son équipe (ligne 3)
		buttonValider = new MyButton("Valider", Color.LIGHT_GRAY);
		buttonValider.addActionListener(new Actions(fenetre, Actions.ACTION_VALIDER));
		buttonValider.setEnabled(false);
		
		conteneurBouton.add(buttonValider);
		conteneurBouton.setLayout(new GridBagLayout());
		this.add(conteneurBouton);
		
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Permet d'afficher le panneau correspondant à l'action souhaitée pour le personnage sélectionné
	 */
	public void refreshActions() {
		this.removeAll();
		
		JPanel conteneurTitre = new JPanel();
		JPanel conteneurActions = new JPanel();
		JPanel conteneurVide = new JPanel();
		
		this.setLayout(new GridLayout(3,1));
		
		//Titre (ligne 1) 
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
		
		//Nos trois boutons d'actions (ligne 2)
		conteneurActions.setLayout(new GridLayout(1, 3));
		
		buttonAttaquer = new MyButton("Attaquer", Color.ORANGE);
		buttonAttaquer.addActionListener(new Actions(fenetre, Actions.ACTION_ATTAQUER));
		buttonAttaquer.setEnabled(false);
		
		buttonDeplacer = new MyButton("Deplacer", Color.GREEN);
		buttonDeplacer.addActionListener(new Actions(fenetre, Actions.ACTION_DEPLACER));
		buttonDeplacer.setEnabled(false);
		
		buttonPasser = new MyButton("Passer Tour", Color.LIGHT_GRAY);
		buttonPasser.addActionListener(new Actions(fenetre, Actions.ACTION_PASSER_TOUR));
		buttonPasser.setEnabled(false);
		
		conteneurActions.add(new MyPanel(buttonAttaquer));
		conteneurActions.add(new MyPanel(buttonDeplacer));
		conteneurActions.add(new MyPanel(buttonPasser));
		this.add(conteneurActions);
		
		//JPanel vide pour avoir la même taille que le panneau de sélection (ligne 3)
		this.add(conteneurVide);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Getter du bouton d'attaque
	 * @return le bouton d'attaque
	 */
	public JButton getButtonAttaquer() {
		return buttonAttaquer;
	}

	/**
	 * Getter du bouton de déplacement
	 * @return le bouton de déplacement
	 */
	public JButton getButtonDeplacer() {
		return buttonDeplacer;
	}

	/**
	 * Getter du bouton pour valider son équipe
	 * @return le bouton pour valider son équipe
	 */
	public JButton getButtonValider() {
		return buttonValider;
	}
	
	/**
	 * Getter du bouton pour passer son tour
	 * @return le bouton pour passer son tour
	 */
	public JButton getButtonPasser() {
		return buttonPasser;
	}

}
