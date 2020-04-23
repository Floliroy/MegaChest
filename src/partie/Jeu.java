package partie;

import java.util.ArrayList;

import personnages.Personnage;
import plateau.Case;
import plateau.Plateau;
import util.Util;


public class Jeu {
		
	private Plateau plateauJeu;
	private Joueur joueur1;
	private Joueur joueur2;
	
	
	private Joueur getJoueurActif() {
		if(joueur1.isTour() && !joueur2.isTour()) {
			return joueur1;
		}else if(!joueur1.isTour() && joueur2.isTour()) {
			return joueur2;
		}
		return null;
	}
	
	private Joueur getJoueurInactif() {
		if(joueur1.isTour() && !joueur2.isTour()) {
			return joueur2;
		}else if(!joueur1.isTour() && joueur2.isTour()) {
			return joueur1;
		}
		return null;
	}
	
	/**
	 * Permet d'effectuer l'action attaquer entre deux personnages
	 * @param attaquant Le personnage initiant l'attaque
	 * @param defenseur Le personnage subissant l'attaque
	 */
	public void actionAttaquer(Personnage attaquant) {
		ArrayList<Personnage> personnagesAttaquables = new ArrayList<>();
		//On vérifie que dans les cases a porter du personnage attaquant on a bien un ou des ennemis
		for(Personnage ennemi : getJoueurInactif().getEquipe().getEquipe()) {
			for(Case caseAPorte : plateauJeu.getCasesAPorte(attaquant)) {
				if(caseAPorte.getPersonnage().equals(ennemi)) {
					personnagesAttaquables.add(ennemi);
				}
			}
		}
		//Si on peut attaquer
		if(!personnagesAttaquables.isEmpty()) {
			String nomDefenseur = null;
			Personnage defenseur = null;
			do {
				//On demande quel personnage il souhaite attaquer
				System.out.println("Les personnages attaquables sont : " + Util.imprimeList(personnagesAttaquables));
				System.out.print("Veuillez entrer le nom du Personnage a attaquer (ou retour) : ");
				nomDefenseur = Clavier.entrerClavierString().toLowerCase();
				
				//On vérifie si ce personnage existe et s'il est a portée d'attaque
				for(Personnage ennemi : personnagesAttaquables) {
					if(ennemi.getNom().toLowerCase().equals(nomDefenseur)) {
						defenseur = ennemi;
						break;
					}
				}
				if(defenseur == null) {
					System.out.println("Personnage \"" + nomDefenseur + "\" introuvable ou pas a portée d'attaque.");
				}
			}while(defenseur == null || !nomDefenseur.equals("retour"));
				
			if(defenseur != null) {
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
		}else {
			System.out.println("Aucun personnage ennemi a portée d'attaque.");
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
		return joueur1;
	}
	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}
	public Joueur getJoueur2() {
		return joueur2;
	}
	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}
}
