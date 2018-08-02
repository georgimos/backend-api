package utility;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Combiner{
	
	private Parser parser = new Parser();
			
	public String process(String collectionName, String entry){
		
		JsonObject response = parser.strToJSON(entry);
		
		JsonArray results = response.getAsJsonArray("results");
		
		entry = "" ;
		
		for (int i = 0; i<results.size();i++){
			JsonObject element = (JsonObject) results.get(i);
			
			entry += "{";
			entry += "\"SuggestionKind\": \"" + collectionName + "\", " ;
			
			if (collectionName.equals("Answer")){
				entry += "\"ReplySuggestion\": " + element.get("text") + ", " ;
			}
			if (collectionName.equals("Self Service")){
				entry += "\"ReplyTopic\": " + element.get("title") + ", " ;
				entry += "\"ReplySuggestion\": " + element.get("Beschreibungstext") + ", " ;
				entry += "\"ReplyLink\": " + element.get("text") + ", " ;
			}
			if (collectionName.equals("FAQ")){
				try{
					entry += "\"ReplyTopic\": " + element.getAsJsonArray("title").get(0) + ", " ;
				}catch(Exception ex){}
				entry += "\"ReplyLink\": " + element.get("web") + ", " ;
				entry += "\"ReplySnippet\": " + element.get("snippet") + ", " ;
			}			
			
			entry += "\"ReplyPriority\": " + element.get("score") ;
			entry += "},";
		}
		
		if (!entry.isEmpty()) entry = entry.substring(0, entry.length()-1);
		
		return entry;
	
	}	 
	
	public String combine(List<String> entries){
		
		
		String response="{}";
		
		response="{\"ReplySuggestions\":[ ";
		
		for (int i = 0; i < entries.size(); i++){
			if (((String) entries.get(i)).isEmpty()){
				response += "null";
			}else{
				response += entries.get(i);
			}
			response += ",";
		}	
		response = response.substring(0, response.length()-1);
		
		response += "]}";
				
		
		return response;
	}
	
}