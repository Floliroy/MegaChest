package persistance;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;


import partie.Jeu;
import partie.Joueur;
import plateau.*;
import personnages.*;


public class SauvegardeJeu {
	
	private Jeu partie;
	@Expose
	private ArrayList<Case> equipeJoueur1;
	@Expose
	private ArrayList<Case> equipeJoueur2;
	@Expose
	private Joueur joueur1;
	@Expose
	private Joueur joueur2;
	@Expose
	private int etatJeu;

	
	public SauvegardeJeu(Jeu partie) {
		this.partie = partie;
		equipeJoueur1 = new ArrayList<Case>();
		equipeJoueur2 = new ArrayList<Case> ();
		joueur1 = partie.getJoueur1();
		joueur2 = partie.getJoueur2();
		
		etatJeu = partie.getEtatJeu();
	}
	
	
	private void addPersonnageToList(Case current) {
		Personnage perso = current.getPersonnage();
		if(partie.getJoueur1().getEquipe().isDansEquipe(perso))
			equipeJoueur1.add(current);
		else
			equipeJoueur2.add(current);
	}
	
	
	public void sauvegardePersonnage(){
		Plateau plateau = partie.getPlateauJeu();
		
		for(int colonne = 0; colonne < Plateau.NOMBRE_COLONNE; colonne ++) {
			for(int ligne = 0; ligne < Plateau.NOMBRE_LIGNE; ligne ++) {
				Case current = plateau.getCase(colonne, ligne);
				if (!current.isEmpty()) 
					addPersonnageToList(current);
			}
		}
		
	}

	
	public ArrayList<Case> getEquipeJoueur1() {
		return equipeJoueur1;
	}


	public ArrayList<Case> getEquipeJoueur2() {
		return equipeJoueur2;
	}


	public Joueur getJoueur1() {
		return joueur1;
	}


	public Joueur getJoueur2() {
		return joueur2;
	}
	
	public int getEtatJeu() {
		return etatJeu;
	}

	public void dumpEquipeJoueur() {
		System.out.println(joueur1.getNom());
		equipeJoueur1.forEach( (cp) -> {
			System.out.println(  cp.dumpCase() + " : " + cp.getPersonnage().getNom());
		});
		
		System.out.println(joueur2.getNom());
		equipeJoueur2.forEach( (cp) -> {
			System.out.println(  cp.dumpCase() + " : " + cp.getPersonnage().getNom());
		});
	}
	

}
