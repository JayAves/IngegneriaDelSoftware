package it.polimi.ingsw.ps29.model.game.resources;

public class StonesResourcesDecorator extends ResourcesDecorator{
	
	int stonesModifier;

	public StonesResourcesDecorator(Container decoratedResources, int n) {
		super(decoratedResources);
		stonesModifier = n;
	}

	@Override
	public void modifyStones(int stones) {
		if (stones < 0)
		   super.modifyStones(stones);
		else
			super.modifyStones(stones + stonesModifier);
			
	}
	
	

}
