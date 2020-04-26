package personnages;

public class Kled extends Personnage {

	private static final String nom = "Kled";
	private static final Integer vie = 150;
	private static final Integer deplacements = 6;
	private static final Integer degats = 25;
	private static final Integer portee = 1;
	private static final Integer vitesse = 70;
	private static final Element element = Element.FEU;
	private static final Origine origine = Origine.NOXUS;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/kled.png";
	
	/**
	 * Initialise le personnage Kled avec comme caractéristiques :<br>
	 *  - Vie = 150<br>
	 *  - PM = 6<br>
	 *  - AD = 25<br>
	 *  - PO = 1<br>
	 *  - Vit = 70<br>
	 */
	public Kled() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
