package objets;

public class CeintureGeant extends Objet {

	private static final String cheminImage = System.getProperty("user.dir") + "/images/objets/ceinture_de_geant.png";
	/**
	 * Initialise l'objet Ceinture de Géant qui donnera 50 de vie
	 */
	public CeintureGeant() {
		super(50, TypeStat.VIE, "Ceinture de Géant", cheminImage);
	}
}
