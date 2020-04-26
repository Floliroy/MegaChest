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
	
	
	public Joueur getJoueurActif() {
		if(joueur1.isTour() && !joueur2.isTour()) {
			return joueur1;
		}else if(!joueur1.isTour() && joueur2.isTour()) {
			return joueur2;
		}
		return null;
	}
	
	public Joueur getJoueurInactif() {
		if(joueur1.isTour() && !joueur2.isTour()) {
			return joueur2;
		}else if(!joueur1.isTour() && joueur2.isTour()) {
			return joueur1;
		}
		return null;
	}
	
	public Jeu() {
		plateauJeu = new Plateau();
		joueur1 = new Joueur(true);
		joueur2 = new Joueur(false);
	}
	
	/**
	 * Permet de choisir un personnage de son équipe
	 * @return Le personnage choisi
	 */
	private Personnage choixPersonnage() {
		Personnage personnage = null;
		do {
			System.out.println("Vos personnages sont : " + Util.imprimeListPersoCoor(getJoueurActif().getEquipe().getListePersonnages(), plateauJeu));
			System.out.print("Veuillez entrer le nom du personnage que vous souhaitez jouer : ");
			String nomPersonnage = Clavier.entrerClavierString();
			
			for(Personnage perso : getJoueurActif().getEquipe().getListePersonnages()) {
				if(perso.getNom().toLowerCase().equals(nomPersonnage.toLowerCase())) {
					personnage = perso;
					personnage.setDeplacements(personnage.getDeplacementsBase());
					break;
				}
			}
			if(personnage == null) {
				System.out.println("Personnage \"" + nomPersonnage + "\" introuvable.");
			}
		}while(personnage == null);
		
		return personnage;
	}
	
	public void choixAction() {
		Personnage personnage = choixPersonnage();

		Boolean actionEffectue = false;
		Boolean jetonAttaque = true;
		
		Boolean finTour = false;
		do {
			System.out.println();
			System.out.println("Veuillez choisir l'action souhaitée pour " + personnage.getNom() + " " + plateauJeu.getCase(personnage).dumpCase() + " : ");
			
			String actions = "";
			Integer cpt = 1;
			
			actions += personnage.getDeplacementsAvecBoost() > 0 ? "   " + cpt++ + " - Se déplacer\n" : "";
			actions += jetonAttaque ? "   " + cpt++ + " - Attaquer\n" : "";
			actions += "   " + cpt ++ + (actionEffectue ? " - Passer le tour\n" : " - Changer de perso\n");
			System.out.print(actions);
			Integer action = Clavier.entrerClavierInt();
			if(action == 1 && personnage.getDeplacementsAvecBoost() > 0) {
				actionDeplacer(personnage);
				
				if(personnage.getDeplacementsAvecBoost() > 0 || jetonAttaque) {
					System.out.println();
					plateauJeu.afficherPlateau();	
				}
				actionEffectue = true;
			}else if((action == 1 || (action == 2 && personnage.getDeplacementsAvecBoost() > 0)) && jetonAttaque) {
				jetonAttaque = actionAttaquer(personnage);
				actionEffectue = true;
			}else {
				if(actionEffectue) {
					finTour = true;
				}else {
					personnage = choixPersonnage();
				}
			}
			if((personnage.getDeplacementsAvecBoost() <= 0 && !jetonAttaque) || joueur1.getEquipe().isEmpty() || joueur2.getEquipe().isEmpty()) {
				finTour = true;
			}
		}while(!finTour);
	}
	
	/**
	 * TODO
	 */
	public void positionnerEquipe(Joueur joueur) {
		int positionX = -1;
		int positionY = -1;
		
		for(Personnage membre : joueur.getEquipe().getListePersonnages()) {
			plateauJeu.afficherPlateau();
			System.out.println("Positionnez " + membre.getNom());
		
			do {
				System.out.print("Numéro colonne : ");
				positionX = Clavier.entrerClavierInt() - 1;
				
				System.out.print("Numéro ligne : ");
				positionY = Clavier.entrerClavierInt() - 1;
				
			} while(!plateauJeu.isDansPlateau(positionX, positionY) || !plateauJeu.getCase(positionX, positionY).isEmpty());
			
			plateauJeu.placerPersonnage(positionX, positionY, membre);
		}
	}
	
	/**
	 * Permet d'effectuer l'action attaquer entre deux personnages
	 * @param attaquant Le personnage initiant l'attaque
	 * @param defenseur Le personnage subissant l'attaque
	 * @return l'etat du jeton d'attaque
	 */
	private Boolean actionAttaquer(Personnage attaquant) {
		ArrayList<Personnage> personnagesAttaquables = new ArrayList<>();
		//On vérifie que dans les cases a porter du personnage attaquant on a bien un ou des ennemis
		for(Personnage ennemi : getJoueurInactif().getEquipe().getListePersonnages()) {
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
				nomDefenseur = Clavier.entrerClavierString();
				
				//On vérifie si ce personnage existe
				for(Personnage ennemi : personnagesAttaquables) {
					if(ennemi.getNom().toLowerCase().equals(nomDefenseur.toLowerCase())) {
						defenseur = ennemi;
						break;
					}
				}
				if(defenseur == null) {
					System.out.println("Personnage \"" + nomDefenseur + "\" introuvable ou pas a portée d'attaque.");
				}
			}while(defenseur == null && !nomDefenseur.equals("retour"));
				
			if(defenseur != null) {
				//On regarde si le defenseur pourra r�pondre a l'attaque ou non
				Boolean defenseurPeutAttaquer = plateauJeu.getCasesAPorte(defenseur).contains(plateauJeu.getCase(attaquant));
				
				//Si l'attaquant attaque en premier
				if(attaquant.getVitesseAvecBoost() > defenseur.getVitesseAvecBoost()) {
					attaquant.attaque(defenseur);
					defenseur.imprimeEtat();
					if(defenseur.isVivant() && defenseurPeutAttaquer) {
						defenseur.attaque(attaquant);
						attaquant.imprimeEtat();
					}
					
				//Si le d�fenseur attaque en premier
				}else if(attaquant.getVitesseAvecBoost() < defenseur.getVitesseAvecBoost()) {
					if(defenseurPeutAttaquer) {
						defenseur.attaque(attaquant);
						attaquant.imprimeEtat();
					}
					if(attaquant.isVivant()) {
						attaquant.attaque(defenseur);
						defenseur.imprimeEtat();
					}
					
				//Si les deux attaques en même temps
				}else {
					attaquant.attaque(defenseur);
					defenseur.imprimeEtat();
					if(defenseurPeutAttaquer) {
						defenseur.attaque(attaquant);
						attaquant.imprimeEtat();
					}
				}
				
				//On supprime le personnage de son equipe s'il est mort
				if(!attaquant.isVivant()) {
					getJoueurActif().getEquipe().removeEquipe(attaquant);
				}
				if(!defenseur.isVivant()) {
					getJoueurInactif().getEquipe().removeEquipe(defenseur);
				}
				//Le jeton d'attaque a été utilisé
				return false;
			}			
		}else {
			System.out.println("Aucun personnage ennemi a portée d'attaque.");
		}
		//Le jeton d'attaque est toujours disponible
		return true;
	}
	
	/**
	 * TODO
	 */
	private void actionDeplacer(Personnage personnage) {
		Case depart = plateauJeu.getCase(personnage);
		System.out.println("Vous etes sur la case " + depart.dumpCase());
		System.out.println(personnage.getNom() + " possede " + personnage.getDeplacementsAvecBoost() + " PM");
		
		ArrayList<Case> casesAtteignables = plateauJeu.getCasesAtteignables(personnage);

		int positionX = -1;
		int positionY = -1;
		
		do {
			
			System.out.println("Veuillez choisir les coordonnees de la case d'arrive :");
	
			System.out.print("Numéro colonne : ");
			positionX = Clavier.entrerClavierInt() - 1;
			
			System.out.print("Numéro ligne : ");
			positionY = Clavier.entrerClavierInt() - 1;
		
		} while(!plateauJeu.isDansPlateau(positionX, positionY) || !casesAtteignables.contains(plateauJeu.getCase(positionX, positionY)));

		plateauJeu.deplacerPersonnage(depart, positionX, positionY);
		
		personnage.setDeplacements(personnage.getDeplacements() - Util.distanceCase(depart, positionX, positionY));
	}
	
	public void saisirNomJoueur() {
		System.out.println("Joueur 1 entre ton nom :");
		joueur1.setNom(Clavier.entrerClavierString());
		
		System.out.println("Joueur 2 entre ton nom :");
		joueur2.setNom(Clavier.entrerClavierString());
	}
	
	public void initialisationEquipe(Joueur joueur) {
		System.out.println(joueur.getNom() + " complète ton équipe !\n");
		joueur.completerEquipe();
		positionnerEquipe(joueur);
	}

	
	public void initialiserPartie() {
		
		String pres = "\t+-------------------+\n";
			  pres += "\t|     MegaChest     |\n";
			  pres += "\t+-------------------+";
		
		System.out.println(pres + "\n");
		saisirNomJoueur();
		
		System.out.println();
		initialisationEquipe(joueur1);
		System.out.println();
		initialisationEquipe(joueur2);
		
	}
	
	public void declarerVainqueur() {
	
		if(!joueur1.getEquipe().isEmpty() && joueur2.getEquipe().isEmpty()) {
			System.out.println(joueur1.getNom() + " tu as gagné !");
			
		}else if(joueur1.getEquipe().isEmpty() && !joueur2.getEquipe().isEmpty()) {
			System.out.println(joueur2.getNom() + " tu as gagné !");
			
		}else {
			
			if(getJoueurActif().equals(joueur1)){
				System.out.println(joueur1.getNom() + " tu as gagné !");
				
			}else {
				System.out.println(joueur2.getNom() + " tu as gagné !");
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
