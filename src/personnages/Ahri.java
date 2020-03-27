package personnages;

public class Ahri extends Personnage {

	private static final String nom = "Ahri";
	private static final Integer vie = 80;
	private static final Integer deplacements = 4;
	private static final Integer degats = 30;
	private static final Integer portee = 6;
	private static final Integer vitesse = 50;
	private static final Element element = Element.EAU;
	private static final Origine origine = Origine.IONIA;
	
	public Ahri() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
