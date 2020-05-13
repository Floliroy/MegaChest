package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import partie.Jeu;
import util.RedirectionOutput;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PanneauJeu panneauJeu;
	private PanneauInfos panneauInfos;
	private JPanel panneauLogs;
	private JPanel panneauActions;

	public Fenetre(Jeu jeu) {
		
		//Fenetre
		this.setTitle("MegaChest");
		this.setSize(1920, 1080);
		this.setLocationRelativeTo(null);
		
		//Panneau Logs (Bas Droite)
		JInternalFrame iFrameLogs= new JInternalFrame();
		panneauLogs = new JPanel();
		panneauLogs.setLayout(new BorderLayout());
		panneauLogs.setBorder(new TitledBorder(new EtchedBorder(), "Logs"));
		
		JTextArea textArea= new JTextArea(1,1);
		textArea.setBackground(Color.WHITE);
		textArea.setAutoscrolls(true);
		textArea.setEditable(false);
		
		PrintStream printStream = new PrintStream(new RedirectionOutput(textArea));
		System.setOut(printStream);
		
		JScrollPane scroll = new JScrollPane(textArea, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(null);
		panneauLogs.add(scroll);
		
		iFrameLogs.add(panneauLogs);
		iFrameLogs.setResizable(false);
		iFrameLogs.setBorder(null);
		BasicInternalFrameUI biFrameLogs = (BasicInternalFrameUI) iFrameLogs.getUI();
		biFrameLogs.setNorthPane(null);
		iFrameLogs.setVisible(true);
		
		//Panneau Action (Bas Gauche)
		panneauActions = new JPanel();
		panneauActions.setBackground(Color.YELLOW);
		
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
		
		//Positionnement des panneaux avec GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		

		//Panneau Jeu (Haut Gauche)
		cons.gridx = 0;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.71;
		cons.weighty = 0.68;
		add(panneauJeu,cons);

		//Panneau Infos (Haut Droite)
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.weightx = 0.29;
		add(iFrameInfos, cons);

		//Panneau Action (Bas Gauche)
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.weighty = 0.32;
		add(panneauActions, cons);

		//Panneau Logs (Bas Droite)
		cons.gridwidth = 1;
		cons.gridx = 1;
		cons.gridy = 1;
		add(iFrameLogs, cons);
	}
	
	public void addPanel(JPanel panneau) {
		this.setContentPane(panneau);
	}
	
	public void showWindow() {
		this.setVisible(true);
	}
}
