package objets;

public class Bottes extends Objet {
	
	private static final String cheminImage = System.getProperty("user.dir") + "/images/objets/bottes.png";
	
	/**
	 * Initialise l'objet Bottes qui donnera 2 de déplacements
	 */
	public Bottes() {
		super(2, TypeStat.DEPLACEMENTS, "Bottes", cheminImage);
	}

}
