package it.polimi.ingsw.ps29.model.game.resources;

public class ServantsResourcesModifier extends ResourcesDecorator {
	
	int servantsModifier;

	public ServantsResourcesModifier(Resources decoratedResources, int n) {
		super(decoratedResources);
		servantsModifier = n;
	}

	@Override
	public void setServants(int servants) {
		if (servants < 0)
			super.setServants(servants);
		else
			super.setServants(servants + servantsModifier);
	}
	
	

}
