package it.polimi.ingsw.ps29.model.game.resources;

public class Woods extends Resource{
	
	public Woods(int n){
		amount = n;
	}
	
	public Woods(){
		
	}

	int amount;

	public int getAmount() {
		return amount;
	}

	public void modifyAmount(int amount) {
		this.amount += amount;
	}
	
}
