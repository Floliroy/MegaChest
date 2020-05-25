package ui.panneau;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objets.Objet;
import partie.Joueur;
import personnages.Personnage;
import ui.Fenetre;
import ui.souris.SourisObjet;
import ui.souris.SourisSelection;
import ui.util.Actions;
import ui.util.CaseImage;
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
	/** Le bouton pour valider l'equipement d'un objet */
	private JButton buttonEquiper;
	
	/** La case pour donner un objet a un perso */
	private CaseImage caseSelectionne;
	/** Le personnage a qui donner un objet */
	private Personnage persoSelectionne;
	/** L'objet qu'on doit donner a un perso */
	private Objet objetLoot;
	
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
		//On reset les composants de la fenetre
		this.removeAll();

		//On crée nos 3 conteneurs (sur 3 lignes)
		JPanel conteneurTitre = new JPanel();
		JPanel conteneurPersonnages = new JPanel();
		JPanel conteneurBouton = new JPanel();
		this.setLayout(new GridLayout(3,1));
		
		/* Titre (ligne 1) */
		String htmlHeader = "<html>"
						  + "<style>"
						  + "span{"
						  + "	color: " + fenetre.getJeu().getJoueurActif().getCouleur() + "; "
						  + "}"
						  + "</style>";
		String htmlFooter = "</html>";

		//On applique de l'html / css sur le texte
		JLabel label = new JLabel(htmlHeader + "<span>" + fenetre.getJeu().getJoueurActif().getNom() + "</span> : Sélectionnez et Placez vos Personnages" + htmlFooter);
		label.setFont(new Font("Calibri", Font.BOLD, 32));
		conteneurTitre.setLayout(new GridBagLayout());
		conteneurTitre.add(label);
		this.add(conteneurTitre);	
		
		/* Liste des personnages dispo (ligne 2) */
		conteneurPersonnages.setLayout(new GridLayout(1, Util.listePersonnages().size()));
		for(Personnage personnage : Util.listePersonnages()) {
			CaseImage panel = new CaseImage(personnage, 80, 80, null);
			//On ajoute un listener pour savoir quand on clique sur le personnage
			panel.addMouseListener(new SourisSelection(personnage, panel, fenetre));
			conteneurPersonnages.add(panel);
		}
		this.add(conteneurPersonnages);
		
		/* Bouton pour valider son équipe (ligne 3) */
		buttonValider = new MyButton("Valider", Color.LIGHT_GRAY);
		buttonValider.addActionListener(new Actions(fenetre, Actions.ACTION_VALIDER));
		//De base on désactive le bouton (tant que équipe pas complète)
		buttonValider.setEnabled(false);
		
		conteneurBouton.add(buttonValider);
		conteneurBouton.setLayout(new GridBagLayout());
		this.add(conteneurBouton);
		
		//On reconstruit le panneau
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Permet d'afficher le panneau qui permettra d'equiper un item
	 */
	public void showChoixObjet(Joueur joueur) {
		//On reset les composants de la fenetre
		this.removeAll();

		//On récupère un objet aléatoire
		objetLoot = Util.getRandomObjet();
		
		//On crée nos 3 conteneurs (sur 3 lignes)
		JPanel conteneurTitre = new JPanel();
		JPanel conteneurPersonnages = new JPanel();
		JPanel conteneurBouton = new JPanel();
		this.setLayout(new GridLayout(3,1));
		
		/* Titre (ligne 1) */
		String htmlHeader = "<html>"
						  + "<style>"
						  + "span{"
						  + "	color: " + joueur.getCouleur() + "; "
						  + "}"
						  + "</style>";
		String htmlFooter = "</html>";
		//On applique de l'html / css sur le texte
		JLabel label = new JLabel(htmlHeader + "<span>" + joueur.getNom() + "</span> : Choisissez sur qui équiper \"" 
								 + objetLoot.getNom() + "\" " + objetLoot.dumpCaracs() + htmlFooter);
		label.setFont(new Font("Calibri", Font.BOLD, 32));
		conteneurTitre.setLayout(new GridBagLayout());
		conteneurTitre.add(label);
		this.add(conteneurTitre);	
		
		/* Liste des personnages dispo (ligne 2) */
		conteneurPersonnages.setLayout(new GridLayout(1, Util.listePersonnages().size()));
		//On fait un décalage de 4 cases
		for(int i=0 ; i<4 ; i++) {
			conteneurPersonnages.add(new JPanel());
		}
		//On montre les personnages encore dans l'équipe
		for(Personnage personnage : joueur.getEquipe().getListePersonnages()) {
			CaseImage panel = new CaseImage(personnage, 80, 80, null);
			if(personnage.getListObjets().size() >= Personnage.TAILLE_MAX_LISTE_OBJET) {
				//Si le personnage a trop d'objet on le grise
				panel.setTransparency(Util.getGrayTransparency());
			}
			//On ajoute un listener pour savoir quand on clique sur le personnage
			panel.addMouseListener(new SourisObjet(personnage, panel, fenetre));
			conteneurPersonnages.add(panel);
		}
		//On compléte les cases restantes
		for(int i=0 ; i<(Util.listePersonnages().size()-joueur.getEquipe().getListePersonnages().size()-4) ; i++) {
			conteneurPersonnages.add(new JPanel());
		}
		this.add(conteneurPersonnages);
		
		/* Bouton pour valider son équipe (ligne 3) */
		buttonEquiper = new MyButton("Équiper", Color.LIGHT_GRAY);
		buttonEquiper.addActionListener(new Actions(fenetre, Actions.ACTION_EQUIPER));
		buttonEquiper.setEnabled(false);
		//De base on désactive le bouton (tant qu'on a pas choisi un personnage)
		
		conteneurBouton.add(buttonEquiper);
		conteneurBouton.setLayout(new GridBagLayout());
		this.add(conteneurBouton);
		
		//On reconstruit le panneau
		this.revalidate();
		this.repaint();
	}
	

	/**
	 * Permet d'afficher le panneau correspondant à l'action souhaitée pour le personnage sélectionné
	 */
	public void refreshActions() {
		//On reset les composants de la fenetre
		this.removeAll();

		//On crée nos 3 conteneurs (sur 3 lignes)
		JPanel conteneurTitre = new JPanel();
		JPanel conteneurActions = new JPanel();
		JPanel conteneurVide = new JPanel();
		this.setLayout(new GridLayout(3,1));
		
		/* Titre (ligne 1) */
		String htmlHeader = "<html>"
				  + "<style>"
				  + "span{"
				  + "	color: " + fenetre.getJeu().getJoueurActif().getCouleur() + "; "
				  + "}"
				  + "</style>";
		String htmlFooter = "</html>";
		//On applique de l'html / css sur le texte
		JLabel label = new JLabel(htmlHeader + "<span>" + fenetre.getJeu().getJoueurActif().getNom() + "</span> : Choisissez votre Personnage et son Action" + htmlFooter);
		label.setFont(new Font("Calibri", Font.BOLD, 32));
		conteneurTitre.setLayout(new GridBagLayout());
		conteneurTitre.add(label);
		this.add(conteneurTitre);
		
		/* Nos trois boutons d'actions (ligne 2) */
		conteneurActions.setLayout(new GridLayout(1, 3));
		
		buttonAttaquer = new MyButton("Attaquer", Color.ORANGE);
		buttonAttaquer.addActionListener(new Actions(fenetre, Actions.ACTION_ATTAQUER));
		//De base on désactive le bouton (tant que jeton attaque pas dispo)
		buttonAttaquer.setEnabled(false);
		
		buttonDeplacer = new MyButton("Deplacer", Color.GREEN);
		buttonDeplacer.addActionListener(new Actions(fenetre, Actions.ACTION_DEPLACER));
		//De base on désactive le bouton (tant que jeton deplacement pas dispo)
		buttonDeplacer.setEnabled(false);
		
		buttonPasser = new MyButton("Passer Tour", Color.LIGHT_GRAY);
		buttonPasser.addActionListener(new Actions(fenetre, Actions.ACTION_PASSER_TOUR));
		//De base on désactive le bouton (tant que jeton passé pas dispo)
		buttonPasser.setEnabled(false);
		
		conteneurActions.add(new MyPanel(buttonAttaquer));
		conteneurActions.add(new MyPanel(buttonDeplacer));
		conteneurActions.add(new MyPanel(buttonPasser));
		this.add(conteneurActions);
		
		/* JPanel vide pour avoir la même taille que le panneau de sélection (ligne 3) */
		this.add(conteneurVide);
		
		//On reconstruit le panneau
		this.revalidate();
		this.repaint();
	}

	/**
	 * Getter de la case selectionnee lors de la partie equipement d'un objet
	 * @return la case selectionnee
	 */
	public CaseImage getCaseSelectionne() {
		return caseSelectionne;
	}

	/**
	 * Setter de la case selectionnee lors de la partie equipement d'un objet
	 * @param caseSelectionne la case selectionnee
	 */
	public void setCaseSelectionne(CaseImage caseSelectionne) {
		this.caseSelectionne = caseSelectionne;
	}
	
	/**
	 * Getter du personnage selectionnee lors de la partie equipement d'un objet
	 * @return la personnage selectionne
	 */
	public Personnage getPersoSelectionne() {
		return persoSelectionne;
	}

	/**
	 * Setter du personnage selectionnee lors de la partie equipement d'un objet
	 * @param persoSelectionne la personnage selectionne
	 */
	public void setPersoSelectionne(Personnage persoSelectionne) {
		this.persoSelectionne = persoSelectionne;
	}

	/**
	 * Getter de l'objet loot lors de la partie equipement d'un objet
	 * @return l'objet loot 
	 */
	public Objet getObjetLoot() {
		return objetLoot;
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

	/**
	 * Getter du bouton pour equiper un objet
	 * @return le bouton pour equiper un objet
	 */
	public JButton getButtonEquiper() {
		return buttonEquiper;
	}


}
