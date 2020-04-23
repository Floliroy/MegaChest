package partie;

<<<<<<< Upstream, based on branch 'dev' of https://github.com/Floliroy/MegaChest.git
=======
import java.util.ArrayList;

import personnages.Personnage;
import util.*;

>>>>>>> 3bf0304 completerEquipe + retour du nombre de pm restant dans actionDeplacer
public class Joueur {
	
	
	private Equipe equipe;
	private Boolean tour;
	
	public Joueur() {
		setEquipe(new Equipe());
	}
	
	
	public void completerEquipe() {
		ArrayList<Personnage> listePersonnages = Util.listePersonnages();
		String nom;
		
		while(!equipe.isComplete()) {
			System.out.println("Quels personnage voulez vous ajouter ?");
			nom = Clavier.entrerClavierString().toLowerCase();
			
			for(Personnage personnage : listePersonnages) {
				if(nom.equals(personnage.getNom()) && !equipe.isDansEquipe(personnage)) {
					equipe.addEquipe(personnage);
					System.out.println(personnage.getNom() + " a été ajouté à l'équipe ");
					listePersonnages.remove(personnage);		
					break;
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
