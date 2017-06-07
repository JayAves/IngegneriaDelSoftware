package it.polimi.ingsw.ps29.model.provvisorio.gsontest;

import java.util.ArrayList;

import com.google.gson.Gson;

import it.polimi.ingsw.ps29.model.cards.TerritoryCard;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActivityEffect;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class GsonProva {
	

	public static void main(String[] args) {
		
		Resource cost = new Resource("coin", 5);
		ArrayList<Resource> alr = new ArrayList <Resource> ();
		alr.add(cost);
		GainResourcesEffect eff1 = new GainResourcesEffect (alr);
		BonusActivityEffect eff2 = new BonusActivityEffect (2, "Harvest");
		
		ArrayList<Effect> immediate = new ArrayList<Effect>();
		ArrayList<Effect> permanent = new ArrayList<Effect>();
		ArrayList<Resource> resCost = new ArrayList<Resource> ();
		immediate.add(eff1);
		permanent.add(eff2);
		resCost.add(cost);
		
		TerritoryCard test = new TerritoryCard ("Carta1", Period.FIRST, "Territory", immediate, permanent, resCost, 3);
	
		Gson gson = new Gson();
		String serializedObject = gson.toJson(test);
		//String name ="prova.json";
		System.out.println(serializedObject);
		/*
		
		
		System.out.println(gson.fromJson(serializedObject, TerritoryCard.class).getType());
		try {
			FileOutputStream fout = new FileOutputStream(name);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(test);
			//oos.writeObject(serializedObject);
			oos.close();

			//FileInputStream fin = new FileInputStream(name);
			//ObjectInputStream ois = new ObjectInputStream(fin);
			//TerritoryCard test2 = (TerritoryCard) ois.readObject();
			//System.out.println(test2.getImmediateEffects().get(0).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	*/}
}
	
