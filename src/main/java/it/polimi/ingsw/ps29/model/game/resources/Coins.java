package it.polimi.ingsw.ps29.model.game.resources;

public class Coins extends Resource{
	
	public Coins(int n){
		amount = n;
		type = ResourceType.COIN;
	}
	
	public Coins(){
		type = ResourceType.COIN;
		
	}

}
