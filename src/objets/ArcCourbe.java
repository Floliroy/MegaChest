package objets;

public class ArcCourbe extends Objet {

	private static final String cheminImage = System.getProperty("user.dir") + "/images/objets/arc_courbe.png";
	
	/**
	 * Initialise l'objet Arc Courbe qui donnera 20 de vitesse
	 */
	public ArcCourbe() {
		super(20, TypeStat.VITESSE, "Arc Courbe", cheminImage);
	}
		
}
