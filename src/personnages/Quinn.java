package personnages;

public class Quinn extends Personnage {

	private static final String nom = "Quinn";
	private static final Integer vie = 80;
	private static final Integer deplacements = 4;
	private static final Integer degats = 30;
	private static final Integer portee = 8;
	private static final Integer vitesse = 70;
	private static final Element element = Element.VENT;
	private static final Origine origine = Origine.DEMACIA;
	
	public Quinn() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
