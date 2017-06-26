package it.polimi.ingsw.ps29.model.game.resources;

public interface ResourceInterface {

	void modifyAmount(int amount);

	int getAmount();
	
	public String getType();
	
	public void negativeAmount();
	
	public ResourceInterface clone();
		
}
