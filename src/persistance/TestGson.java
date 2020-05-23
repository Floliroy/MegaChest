package persistance;

import com.google.gson.*;

import partie.Jeu;
import persistance.customDeserialize.CaseDeserialize;
import personnages.Ahri;
import plateau.Case;




public class TestGson {
	
	public static void main (String [] args) {
		
	
		
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.setPrettyPrinting().create();
		
		
		Jeu partie = new GenerateJeuTest().GenerateTest();
		
		SerilisationJson seri = new SerilisationJson(partie);
		
		seri.sauvegardePersonnage();
		//seri.dumpString();
		
	
		
		Case ca = new Case(0,0);
		ca.setPersonnage(new Ahri());
		String json = gson.toJson(ca);
		
		GsonBuilder reader = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		JsonDeserializer<Case> deserializer = new CaseDeserialize();
		reader.registerTypeAdapter(Case.class, deserializer);
		Gson test = builder.setPrettyPrinting().create();
		
		System.out.println(json);
		Case output = test.fromJson(json, Case.class);
		output.dumpCase();
		
		
	}

}
