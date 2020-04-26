package test;


import partie.Jeu;

public class TestMain {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		
		jeu.initialiserPartie();
		
		Boolean finJeu = false;
		do {
			
			System.out.println("Tour de " + (jeu.getJoueur1().isTour() ? jeu.getJoueur1().getNom() : jeu.getJoueur2().getNom()) + ":");
			jeu.getPlateauJeu().afficherPlateau();
			
			jeu.choixAction();
			
			if(jeu.getJoueur1().getEquipe().isEmpty() || jeu.getJoueur2().getEquipe().isEmpty()) {
				finJeu = true;
			}else {
				jeu.getJoueur1().setTour(!jeu.getJoueur1().isTour());
				jeu.getJoueur2().setTour(!jeu.getJoueur2().isTour());
			}
		}while(!finJeu);
		
		jeu.declarerVainqueur();
	}

}
