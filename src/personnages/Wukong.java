package personnages;

public class Wukong extends Personnage {

	private static final String nom = "Wukong";
	private static final Integer vie = 120;
	private static final Integer deplacements = 3;
	private static final Integer degats = 25;
	private static final Integer portee = 3;
	private static final Integer vitesse = 60;
	private static final Element element = Element.FEU;
	private static final Origine origine = Origine.IONIA;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/wukong.png";
	
	/**
	 * Initialise le personnage Wukong avec comme caractéristiques :<br>
	 *  - Vie = 120<br>
	 *  - PM = 3<br>
	 *  - AD = 25<br>
	 *  - PO = 3<br>
	 *  - Vit = 60<br>
	 */
	public Wukong() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
