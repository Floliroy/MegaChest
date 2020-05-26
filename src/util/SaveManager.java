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

public class SaveManager {
	/**
	 * PATH relatif du fichier de sauvegarde
	 */
	public final static String SAVE = "./backup/sauvegarde.json";
	
	/**
	 * Permet d'écrire le contenu de la sauvegarde
	 * 
	 * @param partie jeu en cours à sauvegarder
	 * @throws IOException
	 */
	public void writeSauvegarde(Jeu partie) throws IOException {
		
		GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.setPrettyPrinting().create();
		
		SauvegardeJeu seri = new SauvegardeJeu(partie);
		//Sauvegarde de la partie
		seri.sauvegardePersonnage();
		
		// Ecriture de la sauvegarde dans le fichier
		Writer writer = new FileWriter(SAVE);
		gson.toJson(seri, writer);
		writer.flush();
		writer.close();

	}
	
	/**
	 * Permet lire le contenue de la sauvegarde
	 * 
	 * @param partie jeu sur lequel la sauvegarde est chargée
	 * @throws IOException
	 */
	public void readSauvegarde(Jeu partie) throws IOException {
		
		GsonBuilder builder = new GsonBuilder();
		JsonDeserializer<Case> deserializer = new CaseDeserialize();
		builder.registerTypeAdapter(Case.class, deserializer);
		Gson gson = builder.create();
		
		File file = new File(SAVE);
	
		// Lecture du fichier de sauvegarde
		FileInputStream fis = new FileInputStream(file);
		byte[]data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		
		String str = new String(data);
		// Affectation de la sauvegarde à un objet SauvegardeJeu
		SauvegardeJeu chargement = gson.fromJson(str, SauvegardeJeu.class);
		// Chargement de la sauvegarde
		RestoreJeu.Restore(partie, chargement);
	}
	
}
