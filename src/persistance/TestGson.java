package persistance;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;

import java.io.Writer;

import com.google.gson.*;


import partie.Jeu;
import persistance.customDeserialize.CaseDeserialize;
import plateau.Case;
import ui.Fenetre;
import ui.PopUpStart;





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
		partie = new GenerateJeuTest().GenerateTest();
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
			SauvegardeJeu relecture = testreader.fromJson(str, SauvegardeJeu.class);
			relecture.dumpEquipeJoueur();
			
			System.out.println(partie.getJoueur1().getEquipe().isComplete());
			RestoreJeu.Restore(partie, relecture);
			Fenetre fenetre = new Fenetre(partie);
			PopUpStart popup = new PopUpStart(fenetre, partie);
			fenetre.showWindow();
			popup.setVisible(true);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
