package personnages;

public class Shyvana extends Personnage {

	private static final String nom = "Shyvana";
	private static final Integer vie = 150;
	private static final Integer deplacements = 4;
	private static final Integer degats = 20;
	private static final Integer portee = 3;
	private static final Integer vitesse = 70;
	private static final Element element = Element.FEU;
	private static final Origine origine = Origine.DEMACIA;
	
	public Shyvana() {
		super(nom, vie, deplacements, degats, portee, vitesse, element, origine);
	}

}
