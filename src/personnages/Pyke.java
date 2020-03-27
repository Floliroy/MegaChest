package personnages;

public class Pyke extends Personnage {

	private static final String nom = "Pyke";
	private static final Integer vie = 120;
	private static final Integer deplacements = 3;
	private static final Integer degats = 35;
	private static final Integer portee = 4;
	private static final Integer vitesse = 40;
	private static final Element element = Element.EAU;
	private static final Origine origine = Origine.BILGEWATER;
	
	public Pyke() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
