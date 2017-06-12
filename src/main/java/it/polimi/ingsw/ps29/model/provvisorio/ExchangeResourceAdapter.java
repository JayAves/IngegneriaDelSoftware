package it.polimi.ingsw.ps29.model.provvisorio;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;

public class ExchangeResourceAdapter implements JsonSerializer<ExchangeResourceHandler>, JsonDeserializer<ExchangeResourceHandler> {
	
	
	@Override
	public ExchangeResourceHandler deserialize(JsonElement el, Type effectType, JsonDeserializationContext context) throws JsonParseException {
		// TODO Auto-generated method stub
		JsonObject jsonObject = el.getAsJsonObject();
	    String type = jsonObject.get("className").getAsString();
	    JsonElement element = jsonObject.get("classData");

	    try {
	      String thepackage = "it.polimi.ingsw.ps29.model.cards.effects.";
	      return context.deserialize(element, Class.forName(thepackage + type));
	    } catch (ClassNotFoundException cnfe) {
	      throw new JsonParseException("Unknown element type: " + type, cnfe);
	    }
		
		
	}

	@Override
	public JsonElement serialize(ExchangeResourceHandler effect, Type effectType, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
	    result.add("className", new JsonPrimitive(effect.getClass().getSimpleName()));
	    result.add("classData", context.serialize(effect, effect.getClass()));
	    return result;
	}

}