package partie;

public class Joueur {
	
	
	private Equipe equipe;
	
	public Joueur() {
		setEquipe(new Equipe());
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	

}
