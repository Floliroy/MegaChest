package persistance;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.*;;


public class TestJson {

	@SuppressWarnings("unchecked")
	public static void main(String [] args) throws Exception{
	
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("age", "22");
		jo.put("city", "chicago");
		
		try {
			FileWriter file = new FileWriter("./backup/sauvegarde.json");
			file.write(jo.toJSONString());
			file.flush();
			file.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
