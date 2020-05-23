package persistance;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;

import partie.Jeu;
import persistance.customDeserialize.CaseDeserialize;
import plateau.*;
import personnages.*;


public class SerilisationJson {
	
	private Jeu partie;
	@Expose
	private ArrayList<Case> equipeJoueur1;
	@Expose
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
			System.out.println(  cp.dumpCase());
		});
		equipeJoueur2.forEach( (cp) -> {
			System.out.println(  cp.dumpCase());
		});

		

	}
	

}
