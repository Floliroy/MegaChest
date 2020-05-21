package persistance;



import java.util.ArrayList;

import com.google.gson.*;

import partie.Jeu;
import plateau.Case;



public class TestGson {
	
	public static void main (String [] args) {
		
	
		
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.setPrettyPrinting().create();
		
		
		Jeu partie = new GenerateJeuTest().GenerateTest();
		
		SerilisationJson seri = new SerilisationJson(partie);
		
		seri.sauvegardePersonnage();
		seri.dumpString();
		
	
		String json = gson.toJson(seri.getEquipeJoueur1());
		
		

		System.out.println(json);
		
	}

}
