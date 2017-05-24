package it.polimi.ingsw.ps29.model.game.resources;

public class WoodResoucresDecorator extends ResourcesDecorator{
	
	int woodModifier;
	
	public WoodResoucresDecorator(Container decoratedResources, int n) {
		super(decoratedResources);
		woodModifier = n;
	}

	@Override
	public void modifyWoods(int woods) {
		if (woods < 0)
			super.modifyWoods(woods);
		else
			super.modifyWoods(woods + woodModifier);
	}
	
	

}
