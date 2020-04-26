package personnages;

public class Illaoi extends Personnage {

	private static final String nom = "Illaoi";
	private static final Integer vie = 150;
	private static final Integer deplacements = 2;
	private static final Integer degats = 30;
	private static final Integer portee = 2;
	private static final Integer vitesse = 30;
	private static final Element element = Element.TERRE;
	private static final Origine origine = Origine.BILGEWATER;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/illaoi.png";
	
	/**
	 * Initialise le personnage Illaoi avec comme caractéristiques :<br>
	 *  - Vie = 150<br>
	 *  - PM = 2<br>
	 *  - AD = 30<br>
	 *  - PO = 2<br>
	 *  - Vit = 30<br>
	 */
	public Illaoi() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
