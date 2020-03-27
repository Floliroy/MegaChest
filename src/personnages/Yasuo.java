package personnages;

public class Yasuo extends Personnage {

	private static final String nom = "Yasuo";
	private static final Integer vie = 100;
	private static final Integer deplacements = 4;
	private static final Integer degats = 30;
	private static final Integer portee = 2;
	private static final Integer vitesse = 70;
	private static final Element element = Element.VENT;
	private static final Origine origine = Origine.IONIA;
	
	/**
	 * Initialise le personnage Yasuo avec comme caractéristiques :<br>
	 *  - Vie = 100<br>
	 *  - PM = 4<br>
	 *  - AD = 30<br>
	 *  - PO = 2<br>
	 *  - Vit = 70<br>
	 */
	public Yasuo() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
