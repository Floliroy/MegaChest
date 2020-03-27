package personnages;

public class Wukong extends Personnage {

	private static final String nom = "Wukong";
	private static final Integer vie = 120;
	private static final Integer deplacements = 3;
	private static final Integer degats = 25;
	private static final Integer portee = 3;
	private static final Integer vitesse = 60;
	private static final Element element = Element.FEU;
	private static final Origine origine = Origine.IONIA;
	
	public Wukong() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}
	
}
