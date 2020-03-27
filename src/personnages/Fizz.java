package personnages;

public class Fizz extends Personnage {

	private static final String nom = "Fizz";
	private static final Integer vie = 100;
	private static final Integer deplacements = 4;
	private static final Integer degats = 40;
	private static final Integer portee = 2;
	private static final Integer vitesse = 40;
	private static final Element element = Element.EAU;
	private static final Origine origine = Origine.BILGEWATER;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/fizz.png";
	
	/**
	 * Initialise le personnage Fizz avec comme caractéristiques :<br>
	 *  - Vie = 100<br>
	 *  - PM = 4<br>
	 *  - AD = 40<br>
	 *  - PO = 2<br>
	 *  - Vit = 40<br>
	 */
	public Fizz() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
