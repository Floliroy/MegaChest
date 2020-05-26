package ui.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import partie.Jeu;
import ui.Fenetre;
import ui.util.MyPanel;
import util.SaveManager;

public class PopUpStart extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** La partie en cours */
	private Jeu jeu;
	/** La fenetre de jeu */
	private Fenetre fenetre;
	/** Le champs de texte pour le nom du joueur 1 */
	private JTextField nomJ1;
	/** Le champs de texte pour le nom du joueur 2 */
	private JTextField nomJ2;
	/** Le bouton permettant de commencer une nouvelle partie */
	private JButton start;
	/** Le bouton permettant de recharger la derniere partie */
	private JButton load;
	
	/**
	 * Constructeur de notre pop-up
	 * 
	 * @param master La fenetre de jeu
	 * @param jeu La partie en cours
	 */
	public PopUpStart(Fenetre master, Jeu jeu) {
		this.fenetre = master;
		this.jeu = jeu;
		
		//On initialise la popup
		this.setSize(450, 350);
		this.setLocationRelativeTo(master);
		this.setBackground(Color.GRAY);
		this.setResizable(false);
		this.setModal(true);

		//On crée nos 3 conteneurs (sur 3 lignes)
		JPanel conteneurTitre = new JPanel();
		JPanel conteneurInfos = new JPanel();
		JPanel conteneurField = new JPanel();
		GridBagConstraints cons = null;
		this.setLayout(new GridLayout(3,1));
		
		/* Titre (ligne 1) */
		JLabel label = new JLabel("Entrez les noms des joueurs");
		label.setFont(new Font("Calibri", Font.BOLD, 24));
		label.setForeground(Color.BLUE);
		conteneurTitre.setLayout(new GridBagLayout());
		conteneurTitre.add(label);
		this.add(conteneurTitre);	
		
		/* Infos (ligne 2) */
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
		
		
		/* Noms (ligne 3) */
		nomJ1 = new JTextField();
		nomJ1.setPreferredSize(new Dimension(150, 25));
		nomJ2 = new JTextField();
		nomJ2.setPreferredSize(new Dimension(150, 25));
		
		conteneurField.setLayout(new GridBagLayout());
		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		
		//Champs texte pour le joueur 1
		cons.gridx = 0;
		cons.gridy = 0;
		cons.weightx = 0.4;
		cons.weighty = 0.6;
		conteneurField.add(new MyPanel(nomJ1), cons);	
		
		//Bouton commencer partie
		cons.gridx = 1;
		cons.weightx = 0.2;	
		start = new JButton("Commencer");
		start.addActionListener(this);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(start, BorderLayout.SOUTH);
		conteneurField.add(panel, cons);
	
		//Champs texte pour le joueur 2
		cons.gridx = 2;
		cons.weightx = 0.4;
		conteneurField.add(new MyPanel(nomJ2), cons);
		
		//Bouton charger sauvegarde
		File file = new File(SaveManager.SAVE);
		//On crée le bouton seulement si on a un fichier de sauvegarde
		if(file.exists()) {
			cons.gridx = 1;
			cons.gridy = 1;
			cons.weightx = 0.2;	
			cons.weighty = 0.4;
			load = new JButton("Recharger");
			load.addActionListener(this);
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.add(load, BorderLayout.SOUTH);
			conteneurField.add(panel, cons);
		}
		
		this.add(conteneurField);
		
		//On termine le programme si on ferme la popup
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	/**
	 * Listener pour savoir quand on clique sur le bouton pour commencer la partie
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Si on souhaite commencer une nouvelle partie
		if(e.getSource().equals(start)) {
			//Si les champs des joueurs sont renseignés
			if(!nomJ1.getText().isEmpty() && !nomJ2.getText().isEmpty()) {
				//On recupere les noms des joueurs
				jeu.getJoueur1().setNom(nomJ1.getText());
				jeu.getJoueur2().setNom(nomJ2.getText());
				System.out.println("Joueur 1 : " + jeu.getJoueur1().getNom());
				System.out.println("Joueur 2 : " + jeu.getJoueur2().getNom());
				System.out.println();
				//On ferme la pop-up
				dispose();		
				//On supprime l'ancien fichier de sauvgarde
				File file = new File(SaveManager.SAVE);
				if(file.exists()) {
					file.delete();
				}
				//On lance la partie
				fenetre.initJeu();
			}
		//Si on souhaite recharger la derniere partie
		}else if(e.getSource().equals(load) && (new File(SaveManager.SAVE).exists())){
			SaveManager fm = new SaveManager();
			try {
				//On récupère la partie sauvegardée
				fm.readSauvegarde(jeu);
				//On ferme la pop-up
				dispose();		
				//On lance la partie
				fenetre.initJeu();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
