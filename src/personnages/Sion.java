package personnages;

public class Sion extends Personnage {

	private static final String nom = "Sion";
	private static final Integer vie = 200;
	private static final Integer deplacements = 2;
	private static final Integer degats = 15;
	private static final Integer portee = 1;
	private static final Integer vitesse = 30;
	private static final Element element = Element.TERRE;
	private static final Origine origine = Origine.NOXUS;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/sion.png";
	
	/**
	 * Initialise le personnage Sion avec comme caractéristiques :<br>
	 *  - Vie = 200<br>
	 *  - PM = 2<br>
	 *  - AD = 15<br>
	 *  - PO = 1<br>
	 *  - Vit = 30<br>
	 */
	public Sion() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
