package it.polimi.ingsw.ps29.model.game.resources;

public class Coins extends Resource{
	
	public Coins(int n){
		amount = n;
	}
	
	public Coins(){
		
	}
	
	int amount;

	public int getAmount() {
		return amount;
	}

	public void modifyAmount(int amount) {//da implementare eccezione nel caso diventa < 0
		this.amount += amount;
	}
	

}
