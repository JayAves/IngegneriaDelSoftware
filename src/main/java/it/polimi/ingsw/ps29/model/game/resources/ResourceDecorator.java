package it.polimi.ingsw.ps29.model.game.resources;

public class ResourceDecorator implements ResourceInterface {
	
	int modifier;
	private ResourceInterface decoratedResource;
	
	public ResourceDecorator (ResourceInterface decoratedResource, int n){
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

	@Override
	public int getAmount() {
		return decoratedResource.getAmount();
	}

	@Override
	public String getType() {
		return decoratedResource.getType();
	}

	@Override
	public void negativeAmount(){
		decoratedResource.negativeAmount();
	}
		
}
