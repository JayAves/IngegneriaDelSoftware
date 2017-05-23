package it.polimi.ingsw.ps29.model.game.resources;

public class Stones extends Resource{
	
	public Stones(int n){
		amount = n;
		type = ResourceType.STONE;
	}
	
	public Stones(){
		type = ResourceType.STONE;		
	}
	

	public int getAmount() {
		return amount;
	}

	public void modifyAmount(int amount) {// implementare eccezione nel caso amount < 0
		this.amount += amount;
	}

}
