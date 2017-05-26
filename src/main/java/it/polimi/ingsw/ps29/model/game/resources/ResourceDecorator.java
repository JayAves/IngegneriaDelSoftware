package it.polimi.ingsw.ps29.model.game.resources;

public abstract class ResourceDecorator implements ResourceInterface {
	
	int modifier;
	private Resource decoratedResource;
	
	public ResourceDecorator (Resource decoratedResource, int n){
		this.decoratedResource = decoratedResource;
		modifier = n;
	}

	@Override
	public void modifyAmount(int amount) {
		if(amount < 0)
			decoratedResource.modifyAmount(amount);
		else
			decoratedResource.modifyAmount(amount + modifier);
		
	}
		
}
