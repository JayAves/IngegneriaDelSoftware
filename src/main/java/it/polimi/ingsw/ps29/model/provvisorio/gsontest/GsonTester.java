package it.polimi.ingsw.ps29.model.provvisorio.gsontest;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class GsonTester {
	
	Gson gson= new Gson();
	
	JsonParser parser= new JsonParser();
	
	ArrayList<Resource> res = new ArrayList<Resource>();
	
	GainResourcesEffect immediate= new GainResourcesEffect(res);
	
	public void addResource(String type, int value) {
		res.add(new Resource(type,value));
	}
	
	
	public static void main() {
		
	}
}
