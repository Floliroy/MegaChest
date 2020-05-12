package test;

import partie.Jeu;
import ui.Fenetre;

public class TestUI {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Fenetre fenetre = new Fenetre(jeu);
		fenetre.showWindow();
	}
}
