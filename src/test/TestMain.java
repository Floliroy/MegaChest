package test;

import partie.Clavier;
import partie.Jeu;

public class TestMain {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		
		System.out.println("Joueur 1 entre ton nom :");
		jeu.getJoueur1().setNom(Clavier.entrerClavierString());
		
		System.out.println("Joueur 2 entre ton nom :");
		jeu.getJoueur2().setNom(Clavier.entrerClavierString());
		
		System.out.println( jeu.getJoueur1().getNom() + " complète ton équipe !");
		jeu.getJoueur1().completerEquipe();
		jeu.positionnerEquipe(jeu.getJoueur1());
		
		System.out.println( jeu.getJoueur2().getNom() + " complète ton équipe !");
		jeu.getJoueur2().completerEquipe();
		jeu.positionnerEquipe(jeu.getJoueur2());
		
		Boolean finJeu = false;
		do {
			jeu.getPlateauJeu().afficherPlateau();
			
			System.out.println("Tour de " + (jeu.getJoueur1().isTour() ? jeu.getJoueur1().getNom() : jeu.getJoueur2().getNom()) + ":");
			jeu.choixAction();
			
			if(jeu.getJoueur1().getEquipe().isEmpty() || jeu.getJoueur2().getEquipe().isEmpty()) {
				finJeu = true;
			}else {
				jeu.getJoueur1().setTour(!jeu.getJoueur1().isTour());
				jeu.getJoueur2().setTour(!jeu.getJoueur2().isTour());
			}
		}while(!finJeu);
		
		if(!jeu.getJoueur1().getEquipe().isEmpty() && jeu.getJoueur2().getEquipe().isEmpty()) {
			System.out.println(jeu.getJoueur1().getNom() + " tu as gagné !");
		}else if(jeu.getJoueur1().getEquipe().isEmpty() && !jeu.getJoueur2().getEquipe().isEmpty()) {
			System.out.println(jeu.getJoueur2().getNom() + " tu as gagné !");
		}else {
			if(jeu.getJoueurActif().equals(jeu.getJoueur1())) {
				System.out.println("Joueur 1 tu as gagné !");
			}else {
				System.out.println("Joueur 2 tu as gagné !");
			}
		}
	}

}
