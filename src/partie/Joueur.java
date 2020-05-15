package partie;

import java.util.ArrayList;

import personnages.Personnage;
import util.*;


public class Joueur {
	
	
	private Equipe equipe;
	private Boolean tour;
	private String couleur;
	private String nom;
	
	public Joueur(Boolean tour, String couleur) {
		this.equipe = new Equipe();
		this.tour = tour;
		this.couleur = couleur;
	}	

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Boolean isTour() {
		return tour;
	}

	public void setTour(Boolean tour) {
		this.tour = tour;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return this.nom;
	}


	public String getCouleur() {
		return couleur;
	}


	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	

}
