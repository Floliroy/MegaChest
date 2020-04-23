package partie;

public class Joueur {
	
	
	private Equipe equipe;
	private Boolean tour;
	
	public Joueur() {
		setEquipe(new Equipe());
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
