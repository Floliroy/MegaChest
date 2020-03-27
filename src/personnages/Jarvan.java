package personnages;

public class Jarvan extends Personnage {

	private static final String nom = "Jarvan IV";
	private static final Integer vie = 150;
	private static final Integer deplacements = 5;
	private static final Integer degats = 20;
	private static final Integer portee = 2;
	private static final Integer vitesse = 50;
	private static final Element element = Element.TERRE;
	private static final Origine origine = Origine.DEMACIA;
	
	public Jarvan() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
