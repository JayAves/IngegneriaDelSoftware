package it.polimi.ingsw.ps29.model.game.resources;

public class ServantsResourcesDecorator extends ResourcesDecorator {
	
	int servantsModifier;

	public ServantsResourcesDecorator(Container decoratedResources, int n) {
		super(decoratedResources);
		servantsModifier = n;
	}

	@Override
	/*public void modifyResources(String resource, int amount) {
	 *if resource.equals("servants")
		if (servants < 0)
			super.modifyServants(servants);
		else
			super.modifyServants(servants + servantsModifier);
	}
	*/
	

}
