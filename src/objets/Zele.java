package objets;

public class Zele extends Objet{

	private static final String cheminImage = System.getProperty("user.dir") + "/images/objets/zele.png";
	
	/**
	 * Initialise l'objet Z�le qui donnera 2 de port�e
	 */
	public Zele() {
		super(2, TypeStat.PORTEE, "Z�le", cheminImage);
	}
}
