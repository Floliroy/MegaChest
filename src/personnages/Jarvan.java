package personnages;

public class Jarvan extends Personnage {

	private static final String nom = "Jarvan";
	private static final Integer vie = 150;
	private static final Integer deplacements = 5;
	private static final Integer degats = 20;
	private static final Integer portee = 2;
	private static final Integer vitesse = 50;
	private static final Element element = Element.TERRE;
	private static final Origine origine = Origine.DEMACIA;
	private static final String cheminImage = System.getProperty("user.dir") + "/images/personnages/jarvan.png";
	
	/**
	 * Initialise le personnage Jarvan IV avec comme caract�ristiques :<br>
	 *  - Vie = 150<br>
	 *  - PM = 5<br>
	 *  - AD = 20<br>
	 *  - PO = 2<br>
	 *  - Vit = 50<br>
	 */
	public Jarvan() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine, cheminImage);
	}
	
}
