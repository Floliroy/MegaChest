package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
		this.setTitle("MegaChest");
		this.setSize(1920, 1080);
		this.setLocationRelativeTo(null);
		
		panneauInfos = new PanneauInfos();
		panneauLogs = new JPanel();
		
		panneauActions = new JPanel();
		panneauActions.setBackground(Color.YELLOW);
		
		
		JInternalFrame iFrameInfos = new JInternalFrame();
		iFrameInfos.add(panneauInfos);
		iFrameInfos.setBorder(null);
		BasicInternalFrameUI biFrameInfos = (BasicInternalFrameUI) iFrameInfos.getUI();
		biFrameInfos.setNorthPane(null);
		iFrameInfos.setVisible(true);
		
		
		
		
		
		
		JInternalFrame iFrameLogs= new JInternalFrame();
		JPanel test = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JTextArea textArea= new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(Color.GREEN);
		
		
		PrintStream printStream = new PrintStream(new RedirectionOutput(textArea));
		
		PrintStream standartOut = System.out;
		
		System.setOut(printStream);
		//System.setErr(printStream);
		
		test.setBackground(Color.GREEN);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setPreferredSize(new Dimension(100, 100));
		scroll.setBorder(null);
		test.add(scroll);
		iFrameLogs.add(test);
		iFrameLogs.setResizable(false);
		iFrameLogs.setVisible(true);
		
		
		
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
		add(iFrameInfos, cons);

		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.weighty = 0.34;
		add(panneauActions, cons);
		
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
