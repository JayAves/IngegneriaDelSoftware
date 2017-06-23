package it.polimi.ingsw.ps29.model.game.resources;

public class ResourceSantaRitaDecorator extends ResourceDecorator{
	
	int modifier;
	private ResourceInterface decoratedResource;

	public ResourceSantaRitaDecorator(ResourceInterface decoratedResource, int n) {
		super(decoratedResource, n);	
	}

	@Override
	public void modifyAmount(int amount) {
		if(amount < 0)
			decoratedResource.modifyAmount(amount);
		else
			decoratedResource.modifyAmount(amount * 2);
		
	}
}