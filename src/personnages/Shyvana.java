package personnages;

public class Shyvana extends Personnage {

	private static final String nom = "Shyvana";
	private static final Integer vie = 150;
	private static final Integer deplacements = 4;
	private static final Integer degats = 20;
	private static final Integer portee = 3;
	private static final Integer vitesse = 70;
	private static final Element element = Element.FEU;
	private static final Origine origine = Origine.DEMACIA;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/shyvana.png";
	
	/**
	 * Initialise le personnage Shyvana avec comme caractéristiques :<br>
	 *  - Vie = 150<br>
	 *  - PM = 4<br>
	 *  - AD = 20<br>
	 *  - PO = 3<br>
	 *  - Vit = 70<br>
	 */
	public Shyvana() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}

}
