package ui.popup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

import partie.Joueur;
import ui.Fenetre;

public class PopUpEnd extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur de la popup de fin affichant le gagnant
	 * @param fenetre La fenetre de jeu
	 * @param joueur Le gagnant
	 */
	public PopUpEnd(Fenetre fenetre, Joueur joueur) {
		
		//On initialise la popup
		this.setSize(450, 350);
		this.setLocationRelativeTo(fenetre);
		this.setBackground(Color.GRAY);
		this.setResizable(false);
		this.setModal(true);
		
		String htmlHeader = "<html>"
				  + "<style>"
				  + "span{"
				  + "	color: " + joueur.getCouleur() + "; "
				  + "}"
				  + "p {"
				  + "	text-align: center; "
				  + "}"
				  + "</style>";
		String htmlFooter = "</html>";
		//On applique de l'html / css sur le texte
		JLabel label = new JLabel(htmlHeader + "<p>Bravo <span>" + joueur.getNom() + "</span> tu es le gagnant !</p>" + htmlFooter);
		label.setFont(new Font("Calibri", Font.BOLD, 38));
		this.add(label);
		
		//On termine le programme si on ferme la popup
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

}
