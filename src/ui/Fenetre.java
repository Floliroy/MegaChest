package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import partie.Jeu;
import ui.panneau.PanneauActions;
import ui.panneau.PanneauInfos;
import ui.panneau.PanneauJeu;
import ui.panneau.PanneauLogs;
import util.RedirectionOutput;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Le panneau de jeu où le plateau de jeu est affiché */
	private PanneauJeu panneauJeu;
	/** Le panneau d'infos des personnages où s'afficheront les stats du perso au survol */
	private PanneauInfos panneauInfos;
	/** Le panneau de logs reprenant les messages de sysout() */ 
	private PanneauLogs panneauLogs;
	/** Le panneau d'action ou le joueur pourra choisir parmis les différentes actions disponible */
	private PanneauActions panneauActions;
	/** La partie en cours */
	private Jeu jeu;

	/**
	 * Constructeur de notre fenetre, initialisant les differents panneaux
	 * @param jeu La partie en cours
	 */
	public Fenetre(Jeu jeu) {
		this.jeu = jeu;
		
		//Fenetre
		this.setTitle("MegaChest");
		this.setSize(1920, 1080);
		this.setLocationRelativeTo(null);
		
		//Panneau Logs (Bas Droite)
		
		panneauLogs = new PanneauLogs();
		
		PrintStream printStream = new PrintStream(new RedirectionOutput(panneauLogs.getTextOutput()));
		System.setOut(printStream);
		
		JInternalFrame iFrameLogs= new JInternalFrame();
		iFrameLogs.add(panneauLogs);
		iFrameLogs.setResizable(false);
		iFrameLogs.setBorder(null);
		
		BasicInternalFrameUI biFrameLogs = (BasicInternalFrameUI) iFrameLogs.getUI();
		biFrameLogs.setNorthPane(null);
		
		iFrameLogs.setVisible(true);
		
		
		//Panneau Infos (Haut Droite)
		panneauInfos = new PanneauInfos();
		JInternalFrame iFrameInfos = new JInternalFrame();
		iFrameInfos.add(panneauInfos);
		iFrameInfos.setBorder(null);
		BasicInternalFrameUI biFrameInfos = (BasicInternalFrameUI) iFrameInfos.getUI();
		biFrameInfos.setNorthPane(null);
		iFrameInfos.setVisible(true);
		
		//Panneau Jeu (Haut Gauche)
		panneauJeu = new PanneauJeu(this);
		JInternalFrame iFrameJeu = new JInternalFrame();
		iFrameJeu.add(panneauJeu);
		iFrameJeu.setBorder(null);
		BasicInternalFrameUI biFrameJeu = (BasicInternalFrameUI) iFrameJeu.getUI();
		biFrameJeu.setNorthPane(null);
		iFrameJeu.setVisible(true);
		
		//Panneau Action (Bas Gauche)
		panneauActions = new PanneauActions(this);
		JInternalFrame iFrameActions = new JInternalFrame();
		iFrameActions.add(panneauActions);
		iFrameActions.setBorder(null);
		BasicInternalFrameUI biFrameActions = (BasicInternalFrameUI) iFrameActions.getUI();
		biFrameActions.setNorthPane(null);
		iFrameActions.setVisible(true);
		
		
		//Positionnement des panneaux avec GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		

		//Panneau Jeu (Haut Gauche)
		cons.gridx = 0;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.62;
		cons.weighty = 0.75;
		add(iFrameJeu,cons);

		//Panneau Infos (Haut Droite)
		cons.gridx = 1;
		cons.gridy = 0;
		cons.weightx = 0.38;
		add(iFrameInfos, cons);

		//Panneau Action (Bas Gauche)
		cons.gridx = 0;
		cons.gridy = 1;
		cons.weighty = 0.25;
		add(iFrameActions, cons);

		//Panneau Logs (Bas Droite)
		cons.gridx = 1;
		cons.gridy = 1;
		add(iFrameLogs, cons);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Permet d'afficher la fenetre en taille maximale
	 */
	public void showWindow() {
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	
	///////////////////////
	// GETTERS & SETTERS //
	///////////////////////
	
	/**
	 * Getter du panneau de jeu
	 * @return Le panneau de jeu
	 */
	public PanneauJeu getPanneauJeu() {
		return panneauJeu;
	}
	/**
	 * Getter du panneau d'infos
	 * @return Le panneau d'infos
	 */
	public PanneauInfos getPanneauInfos() {
		return panneauInfos;
	}
	/**
	 * Getter du panneau d'actions
	 * @return Le panneau d'actions
	 */
	public PanneauActions getPanneauActions() {
		return panneauActions;
	}
	
	/**
	 * Getter de la partie en cours
	 * @return La partie en cours
	 */
	public Jeu getJeu() {
		return jeu;
	}


}
