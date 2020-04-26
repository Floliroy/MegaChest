package objets;

public class Zele extends Objet{

	private static final String cheminImage = System.getProperty("user.dir") + "/images/objets/zele.png";
	
	/**
	 * Initialise l'objet Zèle qui donnera 2 de portée
	 */
	public Zele() {
		super(2, TypeStat.PORTEE, "Zèle", cheminImage);
	}
}
