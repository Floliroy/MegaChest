package personnages;

public class Poppy extends Personnage {

	private static final String nom = "Poppy";
	private static final Integer vie = 180;
	private static final Integer deplacements = 5;
	private static final Integer degats = 8;
	private static final Integer portee = 2;
	private static final Integer vitesse = 40;
	private static final Element element = Element.TERRE;
	private static final Origine origine = Origine.DEMACIA;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/poppy.png";
	
	/**
	 * Initialise le personnage Poppy avec comme caractéristiques :<br>
	 *  - Vie = 180<br>
	 *  - PM = 5<br>
	 *  - AD = 8<br>
	 *  - PO = 2<br>
	 *  - Vit = 40<br>
	 */
	public Poppy() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
