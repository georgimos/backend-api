package utility;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parser{

	private JsonParser parser = new JsonParser();
			
	public JsonParser getParser() {
		return parser;
	}
	public void setParser(JsonParser parser) {
		this.parser = parser;
	}
	
	public JsonObject strToJSON(String jsonString){
		JsonObject jsonObject = this.getParser().parse(jsonString).getAsJsonObject();

		return jsonObject;
	}
	
}