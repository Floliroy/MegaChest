package objets;

public class BFGlaive extends Objet{

	private static final String cheminImage = System.getProperty("user.dir") + "/images/objets/bf_glaive.png";
	
	/**
	 * Initialise l'objet BF Glaive qui donnera 15 de dégats
	 */
	public BFGlaive() {
		super(15, TypeStat.DEGATS, "BF Glaive", cheminImage);
	}
}
