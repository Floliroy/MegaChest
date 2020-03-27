package personnages;

public class Kled extends Personnage {

	private static final String nom = "Kled";
	private static final Integer vie = 150;
	private static final Integer deplacements = 6;
	private static final Integer degats = 25;
	private static final Integer portee = 1;
	private static final Integer vitesse = 70;
	private static final Element element = Element.FEU;
	private static final Origine origine = Origine.NOXUS;
	
	public Kled() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
