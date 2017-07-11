package it.polimi.ingsw.ps29.model.game.resources;

/**
 * Patter Decorator's realization for Resources. gains are reduced or increased by a constant
 * @author Giovanni Mele
 *
 */
public class ResourceDecorator implements ResourceInterface {
	
	int modifier;
	private ResourceInterface decoratedResource;
	
	public ResourceDecorator (ResourceInterface decoratedResource, int n){
		this.decoratedResource = decoratedResource;
		modifier = n;
	}

	@Override
	public void modifyAmount(int amount) {
		if(amount <= 0)
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
		return decoratedResource.getType().toLowerCase();
	}

	@Override
	public void negativeAmount(){
		decoratedResource.negativeAmount();
	}

	@Override
	public String toString() {
		return decoratedResource.toString() + ", modifier: " + modifier + "\n";
	}
	
	public ResourceDecorator clone () {
		return new ResourceDecorator(decoratedResource.clone(), modifier);
	}
		
	
}
