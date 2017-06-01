package it.polimi.ingsw.ps29.model.provvisorio.gsontest;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.ps29.model.cards.Card;

public class GsonConverter {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		final String JSON_PATH = "/home/jaycaves/Desktop/prova.json";

		
		
		try {
			Writer writer = new FileWriter(JSON_PATH);
			Gson gson = new GsonBuilder().create();
		    //gson.toJson(users, writer);
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(new FileReader(JSON_PATH));
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(br).getAsJsonArray();
		
		ArrayList<JsonObject> result = new ArrayList<>(array.size());

	    for (int i=0; i < array.size(); i++) {
	       
	    	result.add((JsonObject) array.get(i));
	    }
	    
	    ArrayList<Card> deck= new ArrayList<Card>();
	    
	    for(JsonObject obj: result ) {
	    	
	    	
	    	//ciclo di trasformazione json object to Card
	    }
	
	
	}
	
	
	

	/* Codice extra
	 * try {
		JsonWriter  writer = new JsonWriter(new FileWriter(JSON_PATH));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/

}
