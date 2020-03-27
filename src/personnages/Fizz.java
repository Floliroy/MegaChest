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
	
	public Fizz() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
