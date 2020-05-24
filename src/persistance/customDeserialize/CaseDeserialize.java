package persistance.customDeserialize;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import objets.TypeStat;
import personnages.Personnage;
import plateau.Case;
import util.Util;

public class CaseDeserialize implements JsonDeserializer<Case>{
	
	
	private void setPersoObject() {
		
	}
	
	private void setPersoAttrib(Personnage currentP, JsonObject jsonPerso) {
		currentP.setVie(jsonPerso.get("vie").getAsInt());
		JsonObject jsonBonusP = jsonPerso.get("bonusEquipe").getAsJsonObject();
		
		HashMap<TypeStat, Integer> bonus = new HashMap<TypeStat, Integer>();
		
		for(TypeStat value : TypeStat.values())
			bonus.put(value, jsonBonusP.get(value.toString()).getAsInt());
		currentP.setBonusEquipe(bonus);
	}

	
	@Override
	public Case deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jsonObject = arg0.getAsJsonObject();		
		
		JsonObject jsonPerso = jsonObject.get("personnage").getAsJsonObject();
		
		String nomPerso = jsonPerso.get("nom").getAsString();
		ArrayList<Personnage> listeP = Util.listePersonnages();
		
		int cmpt = 0;
		while(!listeP.get(cmpt).getNom().equals(nomPerso) && cmpt < listeP.size())
			cmpt ++;		
		Personnage currentP = listeP.get(cmpt);
		setPersoAttrib(currentP, jsonPerso);
		
		
		Case current = new Case(jsonObject.get("positionX").getAsInt(),
				jsonObject.get("positionY").getAsInt());
		
		current.setPersonnage(currentP);
		
		return current;
	}

}
