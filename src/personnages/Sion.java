package personnages;

public class Sion extends Personnage {

	private static final String nom = "Sion";
	private static final Integer vie = 200;
	private static final Integer deplacements = 2;
	private static final Integer degats = 15;
	private static final Integer portee = 1;
	private static final Integer vitesse = 30;
	private static final Element element = Element.TERRE;
	private static final Origine origine = Origine.NOXUS;
	
	public Sion() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
