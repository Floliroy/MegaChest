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
	
	
	public PopUpEnd(Fenetre fenetre, Joueur joueur) {
		
		this.setSize(450, 350);
		
		this.setLocationRelativeTo(fenetre);
		fenetre.getRootPane().getGlassPane().setVisible(true);
		fenetre.revalidate();
		fenetre.repaint();
		
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
		
		JLabel label = new JLabel(htmlHeader + "<p>Bravo <span>" + joueur.getNom() + "</span> tu es le gagnant !</p>" + htmlFooter);
		label.setFont(new Font("Calibri", Font.BOLD, 38));
		this.add(label);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

}
