package partie;

import personnages.Personnage;
import plateau.Plateau;


public class Jeu {
	
	public static final int PHASE_SELECTION = 1;
	public static final int PHASE_ACTION = 2;
	public static final int PHASE_TERMINE = 3;
	
	public static final int NOMBRE_COLONNE_PLACEMENT = 4;
	
	
	private Plateau plateauJeu;
	private Joueur joueur1;
	private Joueur joueur2;
	private Integer etatJeu;
	
	private Boolean actionEffectue;
	private Boolean attaqueEffectue;
	private Boolean jetonAttaque;
	private Boolean jetonDeplace;
	private Personnage personnageJoue;
	
	public Jeu(){
		plateauJeu = new Plateau();
		joueur1 = new Joueur(true, "blue");
		joueur2 = new Joueur(false, "red");
		etatJeu = PHASE_SELECTION;
		
		actionEffectue = false;
		attaqueEffectue = false;
		jetonAttaque = false;
		jetonDeplace = false;
	}
	
	public void resetTour() {
		actionEffectue = false;
		attaqueEffectue = false;
		jetonAttaque = false;
		jetonDeplace = false;
		personnageJoue.setDeplacements(personnageJoue.getDeplacementsBase());
		personnageJoue = null;
	}
	
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
	
	public void inverseJoueurs() {
		joueur1.setTour(!joueur1.isTour());
		joueur2.setTour(!joueur2.isTour());
	}
	

	public Boolean isFini() {
		return joueur1.getEquipe().isEmpty() || joueur2.getEquipe().isEmpty();
	}
	
	public Joueur getGagnant() {
		if(!joueur1.getEquipe().isEmpty() && joueur2.getEquipe().isEmpty()) {
			return joueur1;
		}else if(joueur1.getEquipe().isEmpty() && !joueur2.getEquipe().isEmpty()) {
			return joueur2;
		}else {
			return getJoueurActif();
		}
	}
	/**
	 * Permet d'effectuer l'action attaquer entre deux personnages
	 * 
	 * @param attaquant Le personnage initiant l'attaque
	 * @param defenseur Le personnage subissant l'attaque
	 * @return l'etat du jeton d'attaque
	 */
	public Boolean actionAttaquer(Personnage attaquant, Personnage defenseur) {
		if(!plateauJeu.getCasesAPorte(attaquant).contains(plateauJeu.getCase(defenseur))) {
			return false;
		}
		
		//On regarde si le defenseur pourra répondre a l'attaque ou non
		Boolean defenseurPeutAttaquer = plateauJeu.getCasesAPorte(defenseur).contains(plateauJeu.getCase(attaquant));
		
		//Si l'attaquant attaque en premier
		if(attaquant.getVitesseAvecBoost() > defenseur.getVitesseAvecBoost()) {
			attaquant.attaque(defenseur);
			defenseur.imprimeEtat();
			if(defenseur.isVivant() && defenseurPeutAttaquer) {
				defenseur.attaque(attaquant);
				attaquant.imprimeEtat();
			}
			
		//Si le défenseur attaque en premier
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
		
		return true;
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
	public Integer getEtatJeu() {
		return etatJeu;
	}
	public void setEtatJeu(Integer etatJeu) {
		this.etatJeu = etatJeu;
	}

	public Boolean getActionEffectue() {
		return actionEffectue;
	}

	public void setActionEffectue(Boolean actionEffectue) {
		this.actionEffectue = actionEffectue;
	}

	public Boolean getJetonAttaque() {
		return jetonAttaque;
	}

	public void setJetonAttaque(Boolean jetonAttaque) {
		this.jetonAttaque = jetonAttaque;
	}

	public Personnage getPersonnageJoue() {
		return personnageJoue;
	}

	public void setPersonnageJoue(Personnage personnageJoue) {
		this.personnageJoue = personnageJoue;
	}

	public Boolean getJetonDeplace() {
		return jetonDeplace;
	}

	public void setJetonDeplace(Boolean jetonDeplace) {
		this.jetonDeplace = jetonDeplace;
	}

	public Boolean getAttaqueEffectue() {
		return attaqueEffectue;
	}

	public void setAttaqueEffectue(Boolean attaqueEffectue) {
		this.attaqueEffectue = attaqueEffectue;
	}

}
