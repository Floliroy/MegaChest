package personnages;

public class Swain extends Personnage {

	private static final String nom = "Swain";
	private static final Integer vie = 180;
	private static final Integer deplacements = 2;
	private static final Integer degats = 20;
	private static final Integer portee = 5;
	private static final Integer vitesse = 40;
	private static final Element element = Element.FEU;
	private static final Origine origine = Origine.NOXUS;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/swain.png";
	
	/**
	 * Initialise le personnage Swain avec comme caractéristiques :<br>
	 *  - Vie = 180<br>
	 *  - PM = 2<br>
	 *  - AD = 20<br>
	 *  - PO = 5<br>
	 *  - Vit = 40<br>
	 */
	public Swain() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
