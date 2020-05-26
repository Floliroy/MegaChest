package persistance.customDeserialize;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


import objets.Objet;
import objets.TypeStat;
import personnages.Personnage;
import plateau.Case;
import util.Util;



public class CaseDeserialize implements JsonDeserializer<Case>{
	
	/**
	 * Permet d'attribuer à un personnage les objets qu'ils possèdents
	 * 
	 * @param currentP personnage possédant les objets
	 * @param jsonEquipements liste des objets du personnage sous la forme de liste Json
	 */
	private void setPersoObject(Personnage currentP, JsonArray jsonEquipements) {
		ArrayList<Objet> listeO = Util.listeObjet();
		
		// On parcourt la liste json pour chaque objets équipés
		for(int i = 0; i < jsonEquipements.size(); i ++) {
			// On transforme l'élément de la liste en objet Json
			JsonObject objet = jsonEquipements.get(0).getAsJsonObject();
			// On récupère le nom de l'objet
			String nomObjet = objet.get("nom").getAsString();
			
			int cmpt = 0;
			// On parcourt la liste d'objets jusqu'à trouver le bon
			while(!listeO.get(cmpt).getNom().equals(nomObjet) && cmpt < listeO.size())
				cmpt ++;
			// On l'ajoute à la liste d'objet du personnage
			currentP.addListObjets(listeO.get(cmpt));
		}	
	}
	
	/**
	 * Permet de mettre à jour les caractéristiques d'un personnage depuis la sauvegarde
	 * 
	 * @param currentP personnage à actualiser
	 * @param jsonPerso personnage sous la forme d'un objet Json
	 */
	private void setPersoAttrib(Personnage currentP, JsonObject jsonPerso) {
		// On actualise la vie du personnage avec la valeur du champ vie de l'objet Json
		currentP.setVie(jsonPerso.get("vie").getAsInt());
		// On récupère les attributs sous la forme d'un objet Json
		JsonObject jsonBonusP = jsonPerso.get("bonusEquipe").getAsJsonObject();
		
		HashMap<TypeStat, Integer> bonus = new HashMap<TypeStat, Integer>();
		// On récupère les bonus du personnages grâce aux champs correspondant dans la sauvegarde
		for(TypeStat value : TypeStat.values())
			bonus.put(value, jsonBonusP.get(value.toString()).getAsInt());
		// On met à jour les bonus du personnage
		currentP.setBonusEquipe(bonus);
	}

	/**
	 * Deserialiser gson personnalisé pour la gestion des personnages sur chaques cases et leurs objets.
	 * Il est utilisé lors de la lecture du fichier sauvegarde.json
	 */
	@Override
	public Case deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		// On récupère l'élément courrant sous la forme d'un objet Json
		JsonObject jsonObject = arg0.getAsJsonObject();		
		
		// On récupère seulement l'objet correspondant au personnage
		JsonObject jsonPerso = jsonObject.get("personnage").getAsJsonObject();
		
		// On récupère le nom du personnage
		String nomPerso = jsonPerso.get("nom").getAsString();
		ArrayList<Personnage> listeP = Util.listePersonnages();
		
		int cmpt = 0;
		while(!listeP.get(cmpt).getNom().equals(nomPerso) && cmpt < listeP.size())
			cmpt ++;
		// On crée le personnage correspondant
		Personnage currentP = listeP.get(cmpt);
		setPersoAttrib(currentP, jsonPerso);
		
		
		JsonArray jsonEquipements = jsonPerso.getAsJsonArray("listObjets");
		setPersoObject(currentP, jsonEquipements);
		
		// On crée la case sur laquelle se trouve le personnage
		Case current = new Case(jsonObject.get("positionX").getAsInt(),
				jsonObject.get("positionY").getAsInt());
		// On affecte le personnage à la case
		current.setPersonnage(currentP);
		
		return current;
	}

}
