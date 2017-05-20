package it.polimi.ingsw.ps29.model.game.resources;

public class StonesResourcesDecorator extends ResourcesDecorator{
	
	int stonesModifier;

	public StonesResourcesDecorator(Resources decoratedResources, int n) {
		super(decoratedResources);
		stonesModifier = n;
	}

	@Override
	public void setStones(int stones) {
		if (stones < 0)
		   super.setStones(stones);
		else
			super.setStones(stones + stonesModifier);
			
	}
	
	

}
