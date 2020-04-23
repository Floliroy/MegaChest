package partie;

import java.util.ArrayList;

import personnages.Personnage;
import util.*;


public class Joueur {
	
	
	private Equipe equipe;
	private Boolean tour;
	
	public Joueur(Boolean tour) {
		this.equipe = new Equipe();
		this.tour = tour;
	}
	
	
	public void completerEquipe() {
		ArrayList<Personnage> listePersonnages = Util.listePersonnages();
		String nom;
		
		while(!equipe.isComplete()) {
			System.out.println("Voici la liste des personnages disponibles : ");
			System.out.println(Util.imprimeList(listePersonnages));
			System.out.println("Quels personnage voulez vous ajouter ?");
			
			nom = Clavier.entrerClavierString().toLowerCase();
			
			for(Personnage personnage : listePersonnages) {
				if(nom.equals(personnage.getNom()) && !equipe.isDansEquipe(personnage)) {
					System.out.println("Personnage : " + personnage.getNom() + "\n" + personnage.dumpCaracteristique());
					
					System.out.println("Confirmer personnage : y/n");
					String choix = Clavier.entrerClavierString().toLowerCase();
					
					if(choix.equals("y")) {
						equipe.addEquipe(personnage);
						System.out.println(personnage.getNom() + " a été ajouté à l'équipe ");
						listePersonnages.remove(personnage);		
						break;
					}
				}
			}
		}
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
	
	

}
