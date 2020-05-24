package partie;

import personnages.Personnage;
import plateau.Plateau;


public class Jeu {
	
	/**
	 * Les valeurs du flag etatJeu pour savoir dans quelle phase le jeu se trouve
	 */
	public static final int PHASE_SELECTION = 1;
	public static final int PHASE_ACTION = 2;
	public static final int PHASE_TERMINE = 3;
	
	/** Le nombre de colonne utilisable pour le placement des personnages */
	public static final int NOMBRE_COLONNE_PLACEMENT = 4;
	
	/** Le plateau de jeu */
	private Plateau plateauJeu;
	/** Le premier joueur */
	private Joueur joueur1;
	/** Le seconde joueur */
	private Joueur joueur2;
	/** L'etat actuel du jeu */
	private Integer etatJeu;
	
	/** booleen pour savoir si une action a été effectué ce tour */
	private Boolean actionEffectue;
	/** booleen pour savoir si une attaque a été effectué ce tour */
	private Boolean attaqueEffectue;
	/** un jeton pour que le prochain click soit utilisé pour connaitre la case ciblée pour une attaque */
	private Boolean jetonAttaque;
	/** un jeton pour que le prochain click soit utilisé pour connaitre la case ciblée de déplacement */
	private Boolean jetonDeplace;
	/** un jeton a incrementer pour que l'on puisse equiper un objet */
	private Integer jetonEquipement;
	/** Le personnage joué ce tour */
	private Personnage personnageJoue;
	
	/**
	 * Constructeur permettant d'initialiser le plateau, les joueurs, et les differents flags / jeton d'un tour
	 */
	public Jeu(){
		plateauJeu = new Plateau();
		joueur1 = new Joueur(true, "blue");
		joueur2 = new Joueur(false, "red");
		etatJeu = PHASE_SELECTION;
		
		actionEffectue = false;
		attaqueEffectue = false;
		jetonAttaque = false;
		jetonDeplace = false;
		setJetonEquipement(0);
	}
	
	/**
	 * Permet de reset tous les flags et jetons nécessaire au déroulement d'un tour de jeu
	 */
	public void resetTour() {
		actionEffectue = false;
		attaqueEffectue = false;
		jetonAttaque = false;
		jetonDeplace = false;
		setJetonEquipement(0);
		
		personnageJoue.setDeplacements(personnageJoue.getDeplacementsBase());
		personnageJoue = null;
	}
	
	/**
	 * Permet de connaître le joueur dont c'est le tour
	 * @return Le joueur qui a la main
	 */
	public Joueur getJoueurActif() {
		if(joueur1.isTour() && !joueur2.isTour()) {
			return joueur1;
		}else if(!joueur1.isTour() && joueur2.isTour()) {
			return joueur2;
		}
		return null;
	}
	
	/**
	 * Permet de connaître le joueur dont ce n'est pas le tour
	 * @return Le joueur qui n'a pas la main
	 */
	public Joueur getJoueurInactif() {
		if(joueur1.isTour() && !joueur2.isTour()) {
			return joueur2;
		}else if(!joueur1.isTour() && joueur2.isTour()) {
			return joueur1;
		}
		return null;
	}
	
	/**
	 * Permet de donner de changer la main entre les deux joueurs
	 */
	public void inverseJoueurs() {
		joueur1.setTour(!joueur1.isTour());
		joueur2.setTour(!joueur2.isTour());
	}
	
	/**
	 * Permet de savoir si la partie est finie
	 * @return true si la partie est finie sinon false
	 */
	public Boolean isFini() {
		return joueur1.getEquipe().isEmpty() || joueur2.getEquipe().isEmpty();
	}
	
	/**
	 * Permet de connaître le joueur qui a gagné
	 * @return Le joueur gagnant
	 */
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
	 * @return true si l'attaque a bien été effectuée sinon false
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
	

	///////////////////////
	// GETTERS & SETTERS //
	///////////////////////
	
	/**
	 * Getter du plateau de jeu
	 * @return Le plateau de jeu
	 */
	public Plateau getPlateauJeu() {
		return plateauJeu;
	}
	/**
	 * Getter du joueur 1
	 * @return Le joueur 1
	 */
	public Joueur getJoueur1() {
		return joueur1;
	}
	/**
	 * Getter du joueur 2
	 * @return Le joueur 2
	 */
	public Joueur getJoueur2() {
		return joueur2;
	}
	/**
	 * Getter de l'etat du jeu
	 * @return L'etat du jeu
	 */
	public Integer getEtatJeu() {
		return etatJeu;
	}
	/**
	 * Setter de l'etat du jeu
	 * @param etatJeu L'etat du jeu souhaité
	 */
	public void setEtatJeu(Integer etatJeu) {
		this.etatJeu = etatJeu;
	}
	/**
	 * Getter pour savoir si on a effectue une action ce tour
	 * @return true si on en a effectue sinon false
	 */
	public Boolean getActionEffectue() {
		return actionEffectue;
	}
	/**
	 * Setter pour indiquer si on a effectue une action ce tour
	 * @param actionEffectue La valeur souhaitée
	 */
	public void setActionEffectue(Boolean actionEffectue) {
		this.actionEffectue = actionEffectue;
	}
	/**
	 * Getter pour savoir si on a effectue une attaque ce tour
	 * @return true si on en a effectue sinon false
	 */
	public Boolean getAttaqueEffectue() {
		return attaqueEffectue;
	}
	/**
	 * Setter pour indiquer si on a effectue une attaque ce tour
	 * @param attaqueEffectue La valeur souhaitée
	 */
	public void setAttaqueEffectue(Boolean attaqueEffectue) {
		this.attaqueEffectue = attaqueEffectue;
	}
	/**
	 * Permet de savoir si on a un jeton d'attaque disponible pour le prochain click
	 * @return Si le jeton est disponible
	 */
	public Boolean getJetonAttaque() {
		return jetonAttaque;
	}
	/**
	 * Permet de donner ou d'enlever un jeton d'attaque pour le prochain click
	 * @param jetonAttaque La valeur souhaitée
	 */
	public void setJetonAttaque(Boolean jetonAttaque) {
		this.jetonAttaque = jetonAttaque;
	}
	/**
	 * Permet de savoir si on a un jeton de déplacement disponible pour le prochain click
	 * @return Si le jeton est disponible
	 */
	public Boolean getJetonDeplace() {
		return jetonDeplace;
	}
	/**
	 * Permet de donner ou d'enlever un jeton de déplacement pour le prochain click
	 * @param jetonDeplace La valeur souhaitée
	 */
	public void setJetonDeplace(Boolean jetonDeplace) {
		this.jetonDeplace = jetonDeplace;
	}
	/**
	 * Permet de savoir si on a un jeton d'équipement disponible
	 * @return Le nombre de jeton disponible
	 */
	public Integer getJetonEquipement() {
		return jetonEquipement;
	}
	/**
	 * Permet de donner ou d'enlever des jetons d'équipement
	 * @param jetonEquipement La valeur souhaitée
	 */
	public void setJetonEquipement(Integer jetonEquipement) {
		this.jetonEquipement = jetonEquipement;
	}
	/**
	 * Permet de connaitre le personnage joué ce tour
	 * @return Le personnage joué
	 */
	public Personnage getPersonnageJoue() {
		return personnageJoue;
	}
	/**
	 * Setter du personnage joué ce tour
	 * @param personnageJoue Le personnage souhaité
	 */
	public void setPersonnageJoue(Personnage personnageJoue) {
		this.personnageJoue = personnageJoue;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur2 = joueur1;	
	}
	
	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;	
	}


}
