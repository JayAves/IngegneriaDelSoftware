package it.polimi.ingsw.ps29.model.cards.customadapters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.TerritoryCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class CardAdapter implements JsonSerializer<Card>, JsonDeserializer<Card> {

	@Override
	public Card deserialize(JsonElement el, Type cardType, JsonDeserializationContext context) throws JsonParseException {
		// TODO Auto-generated method stub
		JsonObject jsonObject = el.getAsJsonObject();
	    String type = jsonObject.get("cardType").getAsString();
	    JsonElement element = jsonObject.get("cardData");

	    try {
	      String thepackage = "it.polimi.ingsw.ps29.model.cards.";
	      return context.deserialize(element, Class.forName(thepackage + type));
	    } catch (ClassNotFoundException cnfe) {
	      throw new JsonParseException("Unknown element type: " + type, cnfe);
	    }
		
		
	}

	@Override
	public JsonElement serialize(Card card, Type cardType, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
	    result.add("cardType", new JsonPrimitive(card.getClass().getSimpleName()));
	    result.add("cardData", context.serialize(card, card.getClass()));
	    return result;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		BufferedReader cards = new BufferedReader(new FileReader("src/main/java/cards.json"));
	    GsonBuilder gcards = new GsonBuilder();
	    gcards.registerTypeAdapter(Card.class, new CardAdapter());
	    gcards.registerTypeAdapter(Effect.class, new EffectAdapter());
	    gcards.registerTypeAdapter(Resource.class, new ResourceAdapter());
	    Card[] cardz = gcards.create().fromJson(cards, Card[].class);
	    if (cardz[1].getType().equalsIgnoreCase("territory")){
	    	
	    	System.out.println(((TerritoryCard)cardz[1]).getHarvestForce());
	   }
	    
	}
}
