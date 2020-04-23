package partie;

import personnages.Personnage;
import plateau.Plateau;


public class Jeu {
		
	private Plateau plateauJeu;
	private Joueur Joueur1;
	private Joueur Joueur2;
	
	/**
	 * Permet d'effectuer l'action attaquer entre deux personnages
	 * @param attaquant Le personnage initiant l'attaque
	 * @param defenseur Le personnage subissant l'attaque
	 */
	public void actionAttaquer(Personnage attaquant, Personnage defenseur) {
		//On regarde si l'attaquant a la portee pour attaquer le defenseur
		if(plateauJeu.getCasesAPorte(attaquant).contains(plateauJeu.getCase(defenseur))) {
			
			//On regarde si le defenseur pourra répondre a l'attaque ou non
			Boolean defenseurPeutAttaquer = plateauJeu.getCasesAPorte(defenseur).contains(plateauJeu.getCase(attaquant));
			
			//Si l'attaquant attaque en premier
			if(attaquant.getVitesseAvecBoost() > defenseur.getVitesseAvecBoost()) {
				attaquant.attaque(defenseur);
				if(defenseur.isVivant() && defenseurPeutAttaquer) {
					defenseur.attaque(attaquant);
				}
				
			//Si le défenseur attaque en premier
			}else if(attaquant.getVitesseAvecBoost() < defenseur.getVitesseAvecBoost()) {
				if(defenseurPeutAttaquer) {
					defenseur.attaque(attaquant);
				}
				if(attaquant.isVivant()) {
					attaquant.attaque(defenseur);
				}
				
			//Si les deux attaques en même temps
			}else {
				attaquant.attaque(defenseur);
				if(defenseurPeutAttaquer) {
					defenseur.attaque(attaquant);
				}
			}
		}
	}
	
	/** ---------------------------------------- */
	public Plateau getPlateauJeu() {
		return plateauJeu;
	}
	public void setPlateauJeu(Plateau plateauJeu) {
		this.plateauJeu = plateauJeu;
	}
	public Joueur getJoueur1() {
		return Joueur1;
	}
	public void setJoueur1(Joueur joueur1) {
		Joueur1 = joueur1;
	}
	public Joueur getJoueur2() {
		return Joueur2;
	}
	public void setJoueur2(Joueur joueur2) {
		Joueur2 = joueur2;
	}
}
