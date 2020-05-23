package persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

import partie.Jeu;
import persistance.customDeserialize.CaseDeserialize;
import plateau.Case;





public class TestGson {
	
	public static void main (String [] args){
		
	
		/** WRITE **/
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.setPrettyPrinting().create();
		
		
		Jeu partie = new GenerateJeuTest().GenerateTest();
		
		SauvegardeJeu seri = new SauvegardeJeu(partie);
		
		seri.sauvegardePersonnage();
		
		try {
			Writer writer = new FileWriter("./backup/sauvegarde.json");
			gson.toJson(seri, writer);
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/** READ **/
		GsonBuilder readerJson = new GsonBuilder();
		JsonDeserializer<Case> deserializer = new CaseDeserialize();
	
		readerJson.registerTypeAdapter(Case.class, deserializer);
		Gson testreader = readerJson.create();
		
		File file = new File("./backup/sauvegarde.json");
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[]data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			
			String str = new String(data);
			System.out.println("Reading file");
			SauvegardeJeu sauvegarde = testreader.fromJson(str, SauvegardeJeu.class);
			sauvegarde.dumpEquipeJoueur();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
		
		//SauvegardeJeu news = testreader.fromJson(, SauvegardeJeu.class);
		
		//news.dumpEquipeJoueur();
	}

}
