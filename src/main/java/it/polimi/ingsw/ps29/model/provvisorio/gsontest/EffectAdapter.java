package it.polimi.ingsw.ps29.model.provvisorio.gsontest;

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

public class EffectAdapter implements JsonSerializer<Effect>, JsonDeserializer<Effect> {
	
	
	@Override
	public Effect deserialize(JsonElement el, Type effectType, JsonDeserializationContext context) throws JsonParseException {
		// TODO Auto-generated method stub
		JsonObject jsonObject = el.getAsJsonObject();
	    String type = jsonObject.get("effectType").getAsString();
	    JsonElement element = jsonObject.get("effectData");

	    try {
	      String thepackage = "it.polimi.ingsw.ps29.model.cards.effects.";
	      return context.deserialize(element, Class.forName(thepackage + type));
	    } catch (ClassNotFoundException cnfe) {
	      throw new JsonParseException("Unknown element type: " + type, cnfe);
	    }
		
		
	}

	@Override
	public JsonElement serialize(Effect effect, Type effectType, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
	    result.add("effectType", new JsonPrimitive(effect.getClass().getSimpleName()));
	    result.add("effectData", context.serialize(effect, effect.getClass()));
	    return result;
	}

}