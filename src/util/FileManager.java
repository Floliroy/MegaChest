package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import partie.Jeu;


import persistance.RestoreJeu;
import persistance.SauvegardeJeu;
import persistance.customDeserialize.CaseDeserialize;
import plateau.Case;

public class FileManager {
	
	public final static String SAVE = "./backup/sauvegarde.json";

	public void writeSauvegarde(Jeu partie) throws IOException {
		
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.setPrettyPrinting().create();
		
		SauvegardeJeu seri = new SauvegardeJeu(partie);
		
		seri.sauvegardePersonnage();
		
		Writer writer = new FileWriter(SAVE);
		gson.toJson(seri, writer);
		writer.flush();
		writer.close();

	}
	
	
	public void readSauvegarde(Jeu partie) throws IOException {
		
		GsonBuilder builder = new GsonBuilder();
		JsonDeserializer<Case> deserializer = new CaseDeserialize();
		builder.registerTypeAdapter(Case.class, deserializer);
		Gson gson = builder.create();
		
		File file = new File(SAVE);
	
		FileInputStream fis = new FileInputStream(file);
		byte[]data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		
		String str = new String(data);
		SauvegardeJeu relecture = gson.fromJson(str, SauvegardeJeu.class);
		RestoreJeu.Restore(partie, relecture);
	}
	
}
