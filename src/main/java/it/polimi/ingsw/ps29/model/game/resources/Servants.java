package it.polimi.ingsw.ps29.model.game.resources;

public class Servants extends Resource{
	
	public Servants (int n){
		amount = n;
		type = ResourceType.SERVANT;
	}
	
	public Servants(){
		type = ResourceType.SERVANT;
		
	}

}
