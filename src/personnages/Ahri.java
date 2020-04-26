package personnages;

public class Ahri extends Personnage {

	private static final String nom = "Ahri";
	private static final Integer vie = 80;
	private static final Integer deplacements = 4;
	private static final Integer degats = 30;
	private static final Integer portee = 6;
	private static final Integer vitesse = 50;
	private static final Element element = Element.EAU;
	private static final Origine origine = Origine.IONIA;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/ahri.png";

	/**
	 * Initialise le personnage Ahri avec comme caractéristiques :<br>
	 *  - Vie = 80<br>
	 *  - PM = 4<br>
	 *  - AD = 30<br>
	 *  - PO = 6<br>
	 *  - Vit = 50<br>
	 */
	public Ahri() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
