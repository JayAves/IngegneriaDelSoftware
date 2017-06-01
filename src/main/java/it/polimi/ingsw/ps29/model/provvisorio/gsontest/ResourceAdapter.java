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
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class ResourceAdapter implements JsonSerializer<Resource>, JsonDeserializer<Resource> {
	
	
	@Override
	public Resource deserialize(JsonElement el, Type resourceType, JsonDeserializationContext context) throws JsonParseException {
		// TODO Auto-generated method stub
		JsonObject jsonObject = el.getAsJsonObject();
	    String type = jsonObject.get("resourceType").getAsString();
	    JsonElement element = jsonObject.get("resourceData");

	    try {
	      String thepackage = "it.polimi.ingsw.ps29.model.game.resources.";
	      return context.deserialize(element, Class.forName(thepackage + type));
	    } catch (ClassNotFoundException cnfe) {
	      throw new JsonParseException("Unknown element type: " + type, cnfe);
	    }
		
		
	}

	@Override
	public JsonElement serialize(Resource resource, Type effectType, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		JsonObject result = new JsonObject();
	    result.add("resourceType", new JsonPrimitive(resource.getClass().getSimpleName()));
	    result.add("resourceData", context.serialize(resource, resource.getClass()));
	    return result;
	}

}