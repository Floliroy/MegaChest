package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import personnages.Personnage;

public class PanneauInfos extends JPanel{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanneauInfos() {
		this.setBackground(Color.GRAY);
	}
	
	public void refresh(Personnage personnage) {
		this.removeAll();
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0.50;
		cons.weighty = 0.30;
		
		CaseImage image = new CaseImage(personnage.getCheminImage(), 100, 100, null);
		image.setBackground(Color.GRAY);
		this.add(image,cons);
		
		String htmlHeader = "<style>"
						  + "p {"
						  + "	font-size: large; "
						  + "	font-weight: normal; "
						  + "	text-align: center; "
						  + "	margin-left: 25px; "
						  + "}"
						  + ".caracs {"
						  + "	font-weight: bold; "
						  + "	margin-left: 50px; "
						  + "}"
						  + "h1 {"
						  + "	font-size: xx-large; "
						  + "	text-align: right; "
						  + "	margin-right: 50px; "
						  + "}"
						  + "</style>";
		
		cons.gridx = 1;
		cons.gridy = 0;
		this.add(new JLabel("<html>" + htmlHeader + "<h1>" + personnage.getNom() + "</h1></html>"));

		cons.weighty = 0.70;
		cons.gridx = 0;
		cons.gridy = 1;		
		
		String listeCaracs = "<html>" + htmlHeader + "<p class=\"caracs\"> Point de vie : <br/>" 
				   + " Point de déplacements : <br/>"
				   + " Point de dégats : <br/>"
				   + " Portée d'attaque : <br/>"
				   + " Vitesse d'attaque : <br/></p></html>";
		
		this.add(new JLabel(listeCaracs),cons);

		cons.gridx = 1;
		cons.gridy = 1;
		
		String listeValeurs = "<html>" + htmlHeader + "<p>" + personnage.getVieAvecBoost() + " / " + personnage.getVieBaseAvecBoost() + "<br/>"
				+ personnage.getDeplacementsAvecBoost() + "<br/>"
				+ personnage.getDegatsAvecBoost() + "<br/>"
				+ personnage.getPorteeAvecBoost() + "<br/>"
				+ personnage.getVitesseAvecBoost() + "<br/></p></html>";
		
		this.add(new JLabel(listeValeurs),cons);
	
		this.revalidate();
		this.repaint();
	}
	
	public void refresh() {
		this.removeAll();
		this.revalidate();
		this.repaint();
	}

}
