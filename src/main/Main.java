package main;


import partie.Jeu;
import ui.Fenetre;
import ui.popup.PopUpStart;

public class Main {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Fenetre fenetre = new Fenetre(jeu);
		PopUpStart popup = new PopUpStart(fenetre, jeu);
		fenetre.showWindow();
		popup.setVisible(true);
		
	}
}
