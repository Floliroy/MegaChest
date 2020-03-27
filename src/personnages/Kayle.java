package personnages;

public class Kayle extends Personnage {

	private static final String nom = "Kayle";
	private static final Integer vie = 100;
	private static final Integer deplacements = 2;
	private static final Integer degats = 25;
	private static final Integer portee = 6;
	private static final Integer vitesse = 60;
	private static final Element element = Element.VENT;
	private static final Origine origine = Origine.DEMACIA;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/kayle.png";
	
	/**
	 * Initialise le personnage Kayle avec comme caractéristiques :<br>
	 *  - Vie = 100<br>
	 *  - PM = 2<br>
	 *  - AD = 25<br>
	 *  - PO = 6<br>
	 *  - Vit = 60<br>
	 */
	public Kayle() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
