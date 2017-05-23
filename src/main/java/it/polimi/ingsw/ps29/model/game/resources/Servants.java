package it.polimi.ingsw.ps29.model.game.resources;

public class Servants extends Resource{
	
	public Servants (int n){
		amount = n;
	}
	
	public Servants(){
		
	}

	int amount;

	public int getAmount() {
		return amount;
	}

	public void modifyAmount(int amount) {//da implementare eccezione nel caso amount diventa < 0
		this.amount += amount;
	}
}
