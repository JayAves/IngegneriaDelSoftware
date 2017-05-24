package it.polimi.ingsw.ps29.model.game.resources;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Container {
	
	private HashMap <String, Resource> resources;
	
	public Container () {
		resources = new HashMap <String, Resource> ();
	}
	
	public void addResource (Resource res) {
		if(resources.containsKey(res.getType())) 
			resources.get(res.getType()).modifyAmount(res.getAmount());
		else 
			resources.put(res.getType(), res);
		
	}
	
	public Iterator getIterator () {
		return resources.entrySet().iterator();
	}
	
	
	public HashMap <String, Resource> getResources () {
		return resources;
	}
	
	/*private Coins coins;
	private Stones stones;
	private Servants servants;
	private Woods woods;
	
  //tutti i set devono lanciare un eccezione nel caso il valore vada sotto 0
	

	@Override
	public int getCoins() {
		return coins.getAmount();
	}

	@Override
	public void modifyCoins(int coins) {
		this.coins.modifyAmount(coins);
	}

	@Override
	public int getWoods() {
		return woods.getAmount();
	}

	@Override
	public void modifyWoods(int woods) {
		this.woods.modifyAmount(woods);
	}

	@Override
	public int getStones() {
		return stones.getAmount();
	}

	@Override
	public void modifyStones(int stones) {
		this.stones.modifyAmount(stones);
	}

	@Override
	public int getServants() {
		return servants.getAmount();
	}

	@Override
	public void modifyServants(int servants) {
		this.servants.modifyAmount(servants);
	}
	
	public void modifyResources(String resource, int n){
		switch(resource){
		case "coins": this.modifyCoins(n);
		              break;
		case "woods": this.modifyWoods(n);
		              break;
		case "stones": this.modifyStones(n);
		               break;
		case "servants": this.modifyServants(n);
		                 break;
		}
	}
	
    public void swapResources(String swapped, String swappedFor, int n, int m){
    	this.modifyResources(swapped, -n);
    	this.modifyResources(swappedFor, m);
    }*/

}
	
	
