package partie;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

import personnages.Personnage;
import util.*;


public class Joueur {
	
	
	private Equipe equipe;
	@Expose
	private Boolean tour;
	private String couleur;
	@Expose
	private String nom;
	
	public Joueur(Boolean tour, String couleur) {
		this.equipe = new Equipe();
		this.tour = tour;
		this.couleur = couleur;
	}
	
	
	public void completerEquipe() {
		ArrayList<Personnage> listePersonnages = Util.listePersonnages();
		String nom;
		
		while(!equipe.isComplete()) {
			System.out.println("Voici la liste des personnages disponibles : ");
			System.out.println(Util.imprimeList(listePersonnages));
			System.out.print("Quels personnage voulez vous ajouter ? ");
			
			nom = Clavier.entrerClavierString();
			Boolean trouve = false;
			
			for(Personnage personnage : listePersonnages) {
				if(nom.toLowerCase().equals(personnage.getNom().toLowerCase()) && !equipe.isDansEquipe(personnage)) {
					System.out.println("Personnage : " + personnage.getNom() + "\n" + personnage.dumpCaracteristique());
					
					System.out.print("Confirmer personnage (y/n) : ");
					String choix = Clavier.entrerClavierString().toLowerCase();
					trouve = true;
					
					if(choix.equals("y")) {
						equipe.addEquipe(personnage);
						System.out.println(personnage.getNom() + " a été ajouté à l'équipe.\n");
						listePersonnages.remove(personnage);		
						break;
					}
				}
			}
			if(!trouve) {
				System.out.println("Personnage \"" + nom + "\" introuvable.");
			}
		}
		equipe.calculerBonusEquipe();
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
