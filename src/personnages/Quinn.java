package personnages;

public class Quinn extends Personnage {

	private static final String nom = "Quinn";
	private static final Integer vie = 80;
	private static final Integer deplacements = 4;
	private static final Integer degats = 30;
	private static final Integer portee = 8;
	private static final Integer vitesse = 70;
	private static final Element element = Element.VENT;
	private static final Origine origine = Origine.DEMACIA;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/quinn.png";
	
	/**
	 * Initialise le personnage Quinn avec comme caractéristiques :<br>
	 *  - Vie = 80<br>
	 *  - PM = 4<br>
	 *  - AD = 30<br>
	 *  - PO = 8<br>
	 *  - Vit = 70<br>
	 */
	public Quinn() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
