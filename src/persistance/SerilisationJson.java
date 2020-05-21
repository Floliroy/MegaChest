package persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import partie.Jeu;
import plateau.*;
import personnages.*;

public class SerilisationJson {
	
	private Jeu partie;
	private ArrayList<Case> equipeJoueur1;
	private ArrayList<Case> equipeJoueur2;


	public SerilisationJson(Jeu partie) {
		this.partie = partie;
		equipeJoueur1 = new ArrayList<Case>();
		equipeJoueur2 = new ArrayList<Case> ();
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


	public void dumpString() {
		equipeJoueur2.forEach( (cp) -> {
			System.out.println(  cp.dumpCase() + " : " + cp.getPersonnage().getNom());
		});
		equipeJoueur2.forEach( (cp) -> {
			System.out.println(  cp.dumpCase() + " : " + cp.getPersonnage().getNom());
		});

		

	}
	

}
