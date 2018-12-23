import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonSampleData {
	
	
	public static void main(String[] args) {
		generateSampleFileFromClient();
	}
	private static void generateAllergyData() {
		JSONArray allergies = new JSONArray();
		
	}
	
	private static void generateSampleFileFromClient() {
		JSONArray allergies = new JSONArray();
		allergies.add("peanutButter");
		allergies.add("olvies");
		JSONArray preferences = new JSONArray();
		preferences.add("meat");
		preferences.add("dairy");
		
		JSONObject obj = new JSONObject();
		obj.put("foodPreference", "Vegan");
		obj.put("budget", 500);
		obj.put("calories", 0);
		obj.put("allergies", allergies);
		obj.put("preferences", preferences);
		
		String json = obj.toJSONString();
		System.out.println(json);
		
	}
}
