package persistance;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;

import java.io.Writer;

import com.google.gson.*;

import objets.Bottes;
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
		partie.getJoueur1().getEquipe().getListePersonnages().get(0).addListObjets(new Bottes());
		partie.getJoueur1().getEquipe().getListePersonnages().get(0).addListObjets(new Bottes());
		partie.setEtatJeu(2);
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
		partie = new Jeu();
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
			
			
			RestoreJeu.Restore(partie, relecture);
			System.out.println(partie.getJoueur1().isTour() +  " " + partie.getJoueur1().getNom());
			System.out.println(partie.getJoueur2().isTour() +  " " + partie.getJoueur2().getNom());
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
