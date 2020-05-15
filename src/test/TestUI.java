package test;


import partie.Jeu;
import ui.Fenetre;
import ui.PopUpStart;

public class TestUI {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Fenetre fenetre = new Fenetre(jeu);
		PopUpStart popup = new PopUpStart(fenetre, jeu);
		fenetre.showWindow();
		popup.setVisible(true);
		
	}
}
