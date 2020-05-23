package persistance.customDeserialize;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import persistance.SerilisationJson;
import personnages.Personnage;
import plateau.Case;

public class CaseDeserialize implements JsonDeserializer<Case>{
	

	

	@Override
	public Case deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jsonObject = arg0.getAsJsonObject();
		
		JsonObject jsonPerso = jsonObject.get("personnage").getAsJsonObject();
		
		String nomPerso = jsonPerso.get("nom").getAsString();
		
		
		
		Case current = new Case(jsonObject.get("positionX").getAsInt(),
				jsonObject.get("positionY").getAsInt());
		return current;
	}

}
