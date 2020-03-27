package personnages;

public class Poppy extends Personnage {

	private static final String nom = "Poppy";
	private static final Integer vie = 180;
	private static final Integer deplacements = 5;
	private static final Integer degats = 8;
	private static final Integer portee = 2;
	private static final Integer vitesse = 40;
	private static final Element element = Element.TERRE;
	private static final Origine origine = Origine.DEMACIA;
	
	public Poppy() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
