package ui.panneau;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import personnages.Personnage;
import ui.util.CaseImage;
import ui.util.MyPanel;

public class PanneauInfos extends JPanel{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de panneauInfos
	 */
	public PanneauInfos() {
		this.setBackground(Color.GRAY);
	}
	
	/**
	 * Permet d'afficher les caractéristiques d'un personnage dans 
	 * panneauInfos
	 * 
	 * @param personnage personnage dont il faut afficher les caractéristiques
	 */
	public void refresh(Personnage personnage) {
		//On reset les composants de la fenetre
		this.removeAll();
	
		//On ajoute une ligne si le personnage possède des objets
		this.setLayout(new GridLayout((personnage.getListObjets().isEmpty()?2:3),2));
		
		//On affiche l'icone du personnage
		CaseImage image = new CaseImage(personnage, 100, 100, null);
		image.setBackground(Color.GRAY);
		this.add(image);
		
		//On crée le style css du panneau
		String htmlHeader = "<style>"
						  + "p {"
						  + "	font-size: large; "
						  + "	font-weight: normal; "
						  + "	text-align: center; "
						  + "}"
						  + ".caracs {"
						  + "	font-weight: bold; "
						  + "}"
						  + ".orange {"
						  + "	color: orange; "
						  + "	font-weight: bold; "
						  + "}"
						  + ".lime {"
						  + "	color: lime; "
						  + "	font-weight: bold; "
						  + "}"
						  + "h1 {"
						  + "	font-size: xx-large; "
						  + "	text-align: right; "
						  + "}"
						  + "</style>";
		
		//On ajoute le nom du perso
		this.add(new MyPanel(new JLabel("<html>" + htmlHeader + "<h1>" + personnage.getNom() + "</h1></html>"), Color.GRAY));
	
		//On ajoute la liste des caractéristique communes à tous les personnages
		String listeCaracs = "<html>" + htmlHeader + "<p class=\"caracs\"> Point de vie : <br/>" 
				   + " Point de déplacements : <br/>"
				   + " Point de dégats : <br/>"
				   + " Portée d'attaque : <br/>"
				   + " Vitesse d'attaque : <br/><br/>"
				   + " Élément : <br/>"
				   + " Origine : <br/></p></html>";
		this.add(new MyPanel(new JLabel(listeCaracs), Color.GRAY));
		
		//On ajoute la valeur de ces caractéristiques
		String listeValeurs = "<html>" + htmlHeader + "<p>" + personnage.getVieAvecBoost() + " / " + personnage.getVieBaseAvecBoost() + "<br/>"
				+ personnage.getDeplacementsAvecBoost() + "<br/>"
				+ personnage.getDegatsAvecBoost() + "<br/>"
				+ personnage.getPorteeAvecBoost() + "<br/>"
				+ personnage.getVitesseAvecBoost() + "<br/><br/>"
				+ "<span class=\"orange\">" + personnage.getElement() + "</span><br/>"
				+ "<span class=\"lime\">" + personnage.getOrigine() + "</span><br/></p></html>";
		this.add(new MyPanel(new JLabel(listeValeurs), Color.GRAY));
	
		//Si le personnage possède des objets
		if(!personnage.getListObjets().isEmpty()) {
			if(personnage.getListObjets().size() > 1) {
				this.add(new MyPanel(new JLabel("<html>" + htmlHeader + "<p class=\"caracs\">Objets équipés :</p></html>"), Color.GRAY));
			}else {
				this.add(new MyPanel(new JLabel("<html>" + htmlHeader + "<p class=\"caracs\">Objet équipé :</p></html>"), Color.GRAY));
			}
			
			//On crée le conteneur des icones + nom des objets équipés
			JPanel caracsObjets = new JPanel();
			caracsObjets.setLayout(new GridBagLayout());
			caracsObjets.setBackground(Color.GRAY);
			
			GridBagConstraints cons = new GridBagConstraints();
			cons.fill = GridBagConstraints.BOTH;
			cons.gridwidth = 1;
			cons.gridheight = 1;
			cons.weightx = 0.3;
			//Le nombre de ligne dépend du nombre d'objets
			cons.weighty = 1. / personnage.getListObjets().size();

			cons.gridx = 0;
			//On ajoute les icones des objets
			for(int i=0 ; i<personnage.getListObjets().size() ; i++) {
				CaseImage item = new CaseImage(personnage, 40, 40, "item"+i);
				item.setBackground(Color.GRAY);
				
				cons.gridy = i;
				caracsObjets.add(item, cons);
			}
			

			cons.weightx = 0.7;
			cons.gridx = 1;
			//On ajoute les noms des objets
			for(int i=0 ; i<personnage.getListObjets().size() ; i++) {
				cons.gridy = i;
				caracsObjets.add(new MyPanel(new JLabel("<html>" + htmlHeader + "<p>" + personnage.getListObjets().get(i).getNom() + "</p>"
														+ "<p>" + personnage.getListObjets().get(i).dumpCaracs() + "</p></html>"), Color.GRAY), cons);
			}
			
			//On construit et ajoute le conteneur des objets
			caracsObjets.revalidate();
			caracsObjets.repaint();
			this.add(caracsObjets);
		}

		//On reconstruit le panneau
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Permet de réinitialiser panneauInfo
	 */
	public void refresh() {
		this.removeAll();
		this.revalidate();
		this.repaint();
	}

}
