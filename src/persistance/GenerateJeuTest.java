package persistance;

import java.util.ArrayList;
import java.util.Random;

import partie.Equipe;
import partie.Jeu;
import partie.Joueur;
import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import util.Util;

public class GenerateJeuTest {
	
	private static final int NOMBRE_PERSO = 4;
	
	private void generateEquipe(Equipe equipe) {
		ArrayList<Personnage> listPerso = Util.listePersonnages();
		Personnage perso = null;
		Random rand = new Random();
		
		for(int i = 0; i < NOMBRE_PERSO; i++) {
			do {
				perso = listPerso.get(rand.nextInt(listPerso.size()));
			} while(equipe.isDansEquipe(perso));
			
			System.out.println("Ajout de " + perso.getNom());
			equipe.addEquipe(perso);
		}
	}
	
	
	private void positionnerEquipe(Joueur joueur, Plateau plateau, int colonne) {
		
		for(int i = 0; i < joueur.getEquipe().getListePersonnages().size(); i ++) {
			Personnage perso = joueur.getEquipe().getListePersonnages().get(i);
			Case current = plateau.getCase(colonne, i);
			current.setPersonnage(perso);
		}
		
	}
	

	public  Jeu GenerateTest() {
		Jeu partie = new Jeu();
		partie.getJoueur1().setNom("Joueur1");
		partie.getJoueur2().setNom("Joueur2");
		
		System.out.println("Generation Equipe Joueur 1");
		generateEquipe(partie.getJoueur1().getEquipe());
		System.out.println("Generation Equipe Joueur 2");
		generateEquipe(partie.getJoueur2().getEquipe());
		
		System.out.println("Positonnement perso Equipe Joueur 1");
		positionnerEquipe(partie.getJoueur1(), partie.getPlateauJeu(), 1);
		System.out.println("Positonnement perso Equipe Joueur 2");
		positionnerEquipe(partie.getJoueur2(), partie.getPlateauJeu(), 12);
		
		
		
		
		return partie;
	}
}
