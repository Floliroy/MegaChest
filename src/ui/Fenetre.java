package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import partie.Jeu;

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
		this.setTitle("MegaChest");
		this.setSize(1920, 1080);
		this.setLocationRelativeTo(null);
		
		panneauInfos = new PanneauInfos();
		panneauLogs = new JPanel();
		panneauLogs.setBackground(Color.CYAN);
		panneauActions = new JPanel();
		panneauActions.setBackground(Color.YELLOW);
		
		
		JInternalFrame test = new JInternalFrame();
		test.add(panneauInfos);
		BasicInternalFrameUI bi = (BasicInternalFrameUI) test.getUI();
		bi.setNorthPane(null);
	
		test.setVisible(true);
		
		
		panneauJeu = new PanneauJeu(jeu, panneauInfos);
		
		setLayout(new GridBagLayout());
		
		/* Positionnement des panneaux avec GridBagConstraints*/
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.70;
		cons.weighty = 0.66;
		add(panneauJeu,cons);
		
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.weightx = 0.3;
		add(test, cons);

		cons.gridwidth = 1;
		cons.weighty = 0.34;
		cons.gridx = 0;
		cons.gridy = 1;
		add(panneauActions, cons);
		
		cons.gridwidth = 1;
		cons.gridx = 1;
		cons.gridy = 1;
		add(panneauLogs, cons);
	}
	
	public void addPanel(JPanel panneau) {
		this.setContentPane(panneau);
	}
	
	public void showWindow() {
		this.setVisible(true);
	}
}
