package persistance;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;


import partie.Jeu;

import plateau.*;
import personnages.*;


public class SauvegardeJeu {
	
	private Jeu partie;
	@Expose
	private ArrayList<Case> equipeJoueur1;
	@Expose
	private ArrayList<Case> equipeJoueur2;
	@Expose
	private String nomJoueur1;
	@Expose
	private String nomJoueur2;

	
	public SauvegardeJeu(Jeu partie) {
		this.partie = partie;
		equipeJoueur1 = new ArrayList<Case>();
		equipeJoueur2 = new ArrayList<Case> ();
		nomJoueur1 = partie.getJoueur1().getNom();
		nomJoueur2 = partie.getJoueur2().getNom();
	}
	
	
	private void addPersonnageToMap(Case current) {
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
					addPersonnageToMap(current);
			}
		}
		
	}

	public ArrayList<Case> getEquipeJoueur1() {
		return equipeJoueur1;
	}


	public ArrayList<Case> getEquipeJoueur2() {
		return equipeJoueur2;
	}


	public String getNomJoueur1() {
		return nomJoueur1;
	}


	public String getNomJoueur2() {
		return nomJoueur2;
	}


	public void dumpEquipeJoueur() {
		System.out.println(nomJoueur1);
		equipeJoueur1.forEach( (cp) -> {
			System.out.println(  cp.dumpCase() + " : " + cp.getPersonnage().getNom());
		});
		
		System.out.println(nomJoueur2);
		equipeJoueur2.forEach( (cp) -> {
			System.out.println(  cp.dumpCase() + " : " + cp.getPersonnage().getNom());
		});
	}
	

}
