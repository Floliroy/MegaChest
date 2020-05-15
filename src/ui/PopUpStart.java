package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import partie.Jeu;
import ui.util.MyPanel;

public class PopUpStart extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Jeu jeu;
	private Fenetre fenetre;
	private JTextField nomJ1;
	private JTextField nomJ2;
	
	public PopUpStart(Fenetre master, Jeu jeu) {

		this.fenetre = master;
		this.jeu = jeu;
		
		this.setSize(450, 250);
		this.setLocationRelativeTo(master);
		this.setBackground(Color.GRAY);
		this.setResizable(false);
		this.setModal(true);
		
		this.setLayout(new GridLayout(3,1));

		JPanel conteneurTitre = new JPanel();
		JPanel conteneurInfos = new JPanel();
		JPanel conteneurField = new JPanel();
		GridBagConstraints cons = null;
		
		//Titre (ligne 1)
		JLabel label = new JLabel("Entrez les noms des joueurs");
		label.setFont(new Font("Calibri", Font.BOLD, 24));
		label.setForeground(Color.BLUE);
		conteneurTitre.setLayout(new GridBagLayout());
		conteneurTitre.add(label);
		this.add(conteneurTitre);	
		
		//Infos (ligne 2)
		conteneurInfos.setLayout(new GridBagLayout());
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.4;
		cons.weighty = 1;
		conteneurInfos.add(new MyPanel(new JLabel("Nom Joueur 1 :")), cons);
		cons.gridx = 1;
		cons.weightx = 0.2;
		label = new JLabel("VS");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.BOTTOM);
		conteneurInfos.add(label, cons);
		cons.gridx = 2;
		cons.weightx = 0.4;
		conteneurInfos.add(new MyPanel(new JLabel("Nom Joueur 2 :")), cons);		
		this.add(conteneurInfos);
		
		
		//Noms (ligne 3)
		nomJ1 = new JTextField();
		nomJ1.setPreferredSize(new Dimension(150, 25));
		nomJ2 = new JTextField();
		nomJ2.setPreferredSize(new Dimension(150, 25));
		
		conteneurField.setLayout(new GridBagLayout());
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.4;
		cons.weighty = 1;
		conteneurField.add(new MyPanel(nomJ1), cons);	
		
		//Bouton commencer partie
		cons.gridx = 1;
		cons.weightx = 0.2;	
		JButton button = new JButton("Commencer");
		button.addActionListener(this);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(button, BorderLayout.SOUTH);
		conteneurField.add(panel, cons);
		
		cons.gridx = 2;
		cons.weightx = 0.4;
		conteneurField.add(new MyPanel(nomJ2), cons);
		this.add(conteneurField);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Fonction appelée par l'action listener du bouton
		jeu.getJoueur1().setNom(nomJ1.getText());
		jeu.getJoueur2().setNom(nomJ2.getText());
		System.out.println("Joueur 1 : " + jeu.getJoueur1().getNom());
		System.out.println("Joueur 2 : " + jeu.getJoueur2().getNom());
		dispose();		
		fenetre.getPanneauActions().showSelection();
	}

}
