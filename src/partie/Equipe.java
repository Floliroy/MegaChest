package partie;

import java.util.ArrayList;

import personnages.Personnage;

public class Equipe {
	
	/** TODO */
	private static final int TAILLE_EQUIPE = 8;
	
	/* -------------------------------------------------- */
	
	/** Liste des personnages composant une equipe */
	private ArrayList<Personnage> equipe;
	
	/* -------------------------------------------------- */
	
	/**
	 * TODO
	 */
	public Equipe() {
		equipe = new ArrayList<Personnage>();
	}
	
	/**
	 * TODO
	 * @return
	 */
	public ArrayList<Personnage> getEquipe() {
		return equipe;
	}

	/**
	 * TODO
	 * @param equipe
	 */
	public void setEquipe(ArrayList<Personnage> equipe) {
		this.equipe = equipe;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public boolean isComplete() {
		return equipe.size() == TAILLE_EQUIPE;
	}
	
	/**
	 * TODO
	 * @param personnage
	 */
	public void ajouterPersonnages(Personnage personnage) {
		if(!isComplete())
			equipe.add(personnage);
	}
	
	/**
	 * TODO
	 * @param personnage
	 */
	public void supprimerPersonnage(Personnage personnage) {
		equipe.remove(personnage);
	}
	
	
}
