package it.polimi.ingsw.ps29.model.game.resources;

public class Woods extends Resource{
	
	public Woods(int n){
		amount = n;
		type = ResourceType.WOOD;
	}
	
	public Woods(){
		type = ResourceType.WOOD;
		
	}
	
}
