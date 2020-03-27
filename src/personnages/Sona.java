package personnages;

public class Sona extends Personnage {

	private static final String nom = "Sona";
	private static final Integer vie = 80;
	private static final Integer deplacements = 2;
	private static final Integer degats = 15;
	private static final Integer portee = 6;
	private static final Integer vitesse = 40;
	private static final Element element = Element.EAU;
	private static final Origine origine = Origine.DEMACIA;
	
	public Sona() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
