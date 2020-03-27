package personnages;

public class Talon extends Personnage {

	private static final String nom = "Talon";
	private static final Integer vie = 80;
	private static final Integer deplacements = 5;
	private static final Integer degats = 50;
	private static final Integer portee = 3;
	private static final Integer vitesse = 50;
	private static final Element element = Element.VENT;
	private static final Origine origine = Origine.NOXUS;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/talon.png";
	
	/**
	 * Initialise le personnage Talon avec comme caractéristiques :<br>
	 *  - Vie = 80<br>
	 *  - PM = 5<br>
	 *  - AD = 50<br>
	 *  - PO = 3<br>
	 *  - Vit = 50<br>
	 */
	public Talon() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
