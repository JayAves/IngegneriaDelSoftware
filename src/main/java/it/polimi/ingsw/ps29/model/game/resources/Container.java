package it.polimi.ingsw.ps29.model.game.resources;

import java.util.HashMap;
import java.util.Map;

public class Container {
	
	private Map <String, Resource> container;
	
	public Container () {
		container = new HashMap <String, Resource> ();
	}
	
	private void addResource (Resource res) {
		String index = res.type.getType();
		//...
	}
	
	public void modifyResources (String res, int amount) {
		container.get(res).modifyAmount(amount);
	}
	
	public Resource getResource (String res) {
		return container.get(res);
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
	
	
