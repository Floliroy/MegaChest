package persistance;

import java.util.ArrayList;


import partie.Equipe;
import partie.Jeu;
import partie.Joueur;
import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import util.Util;

public class GenerateJeuTest {
	
	private void generateEquipe(Equipe equipe) {
		ArrayList<Personnage> listPerso = Util.listePersonnages();
		
		for(int i = 0; i < 3; i++) {
			Personnage perso = listPerso.get(i);
			System.out.println("Ajout de " + perso.getNom());
			equipe.addEquipe(perso);
		}
	}
	
	
	private void positionnerEquipe(Joueur joueur, Plateau plateau, int colonne) {
		
		for(int i = 0; i < 3; i ++) {
			Personnage perso = joueur.getEquipe().getListePersonnages().get(i);
			Case current = plateau.getCase(i, colonne);
			System.out.println(perso.getNom() + " est sur la case " + current.dumpCase());
			current.setPersonnage(perso);
		}
		
	}
	

	public  Jeu GenerateTest() {
		Jeu partie = new Jeu();
		System.out.println("Generation Equipe Joueur 1");
		generateEquipe(partie.getJoueur1().getEquipe());
		System.out.println("Generation Equipe Joueur 2");
		generateEquipe(partie.getJoueur2().getEquipe());
		
		System.out.println("Positonnement perso Equipe Joueur 1");
		positionnerEquipe(partie.getJoueur1(), partie.getPlateauJeu(), 1);
		System.out.println("Positonnement perso Equipe Joueur 2");
		positionnerEquipe(partie.getJoueur2(), partie.getPlateauJeu(), 6);
		
		
		
		
		return partie;
	}
}
