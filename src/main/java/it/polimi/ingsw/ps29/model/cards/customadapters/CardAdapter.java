package it.polimi.ingsw.ps29.model.cards.customadapters;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.polimi.ingsw.ps29.model.cards.Card;

/**
 * Serializes and deseriaizes any Card and inheriting-from-Card object. 
 * Since all cards necessary for the game are stored in the same Json file and 
 * are of different types ( Building, Territory, Character and Venture), in writing in Json CardAdapter adds class name as a header.
 * That header's content is used during deserialization to recognize card's type,
 *  by looking into it.polimi.ingsw.ps29.model.cards package for the right class. 
 * @author Pietro Grotti
 *
 */
public class CardAdapter implements JsonSerializer<Card>, JsonDeserializer<Card> {

	@Override
	public Card deserialize(JsonElement el, Type cardType, JsonDeserializationContext context) throws JsonParseException {

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

		JsonObject result = new JsonObject();
	    result.add("cardType", new JsonPrimitive(card.getClass().getSimpleName()));
	    result.add("cardData", context.serialize(card, card.getClass()));
	    return result;
	}
	
	

}
