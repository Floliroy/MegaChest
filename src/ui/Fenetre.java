package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
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
	
	private PanneauJeu panneauJeu;
	private PanneauInfos panneauInfos;
	private PanneauLogs panneauLogs;
	private PanneauActions panneauActions;

	public Fenetre(Jeu jeu) {
		
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
		panneauJeu = new PanneauJeu(jeu, panneauInfos);
		
		//Panneau Action (Bas Gauche)
		panneauActions = new PanneauActions(panneauJeu, panneauInfos);
		//panneauActions.showSelection();
		
		
		//Positionnement des panneaux avec GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		

		//Panneau Jeu (Haut Gauche)
		cons.gridx = 0;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.67;
		cons.weighty = 0.75;
		add(panneauJeu,cons);

		//Panneau Infos (Haut Droite)
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.weightx = 0.33;
		add(iFrameInfos, cons);

		//Panneau Action (Bas Gauche)
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.weighty = 0.25;
		add(panneauActions, cons);

		//Panneau Logs (Bas Droite)
		cons.gridwidth = 1;
		cons.gridx = 1;
		cons.gridy = 1;
		add(iFrameLogs, cons);
		
		
	}
	
	public PanneauJeu getPanneauJeu() {
		return panneauJeu;
	}

	public void setPanneauJeu(PanneauJeu panneauJeu) {
		this.panneauJeu = panneauJeu;
	}

	public PanneauInfos getPanneauInfos() {
		return panneauInfos;
	}

	public void setPanneauInfos(PanneauInfos panneauInfos) {
		this.panneauInfos = panneauInfos;
	}

	public PanneauLogs getPanneauLogs() {
		return panneauLogs;
	}

	public void setPanneauLogs(PanneauLogs panneauLogs) {
		this.panneauLogs = panneauLogs;
	}

	public PanneauActions getPanneauActions() {
		return panneauActions;
	}

	public void setPanneauActions(PanneauActions panneauActions) {
		this.panneauActions = panneauActions;
	}

	public void addPanel(JPanel panneau) {
		this.setContentPane(panneau);
	}
	
	public void showWindow() {
		this.setVisible(true);
	}
}
