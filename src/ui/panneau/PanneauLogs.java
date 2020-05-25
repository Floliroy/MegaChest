package ui.panneau;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanneauLogs extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextArea textOutput;
	
	/**
	 * Constructeur de PanneauLogs
	 * 
	 * Permet de construire le panneau contenant une JTextArea permettant
	 * d'afficher les logs sur les actions qui ont lieu pendant la partie.
	 */
	public PanneauLogs() {
		
		this.setLayout(new BorderLayout());
		//On ajoute une bordure avec un titre
		this.setBorder(new TitledBorder(new EtchedBorder(), "Logs"));
		
		textOutput = new JTextArea(1,1);
		textOutput.setBackground(Color.WHITE);
		textOutput.setAutoscrolls(true);
		textOutput.setEditable(false);
		
		//On ajoute une barre scrollable
		JScrollPane scroll = new JScrollPane(textOutput, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(null);
		this.add(scroll);
		
	}

	/**
	 * Methode qui permet de rediriger un flux de texte vers notre panneau
	 * @return
	 */
	public JTextArea getTextOutput() {
		return textOutput;
	}

}
