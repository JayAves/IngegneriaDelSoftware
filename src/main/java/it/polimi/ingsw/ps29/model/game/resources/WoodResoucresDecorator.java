package it.polimi.ingsw.ps29.model.game.resources;

public class WoodResoucresDecorator extends ResourcesDecorator{
	
	int woodModifier;
	
	public WoodResoucresDecorator(Resources decoratedResources, int n) {
		super(decoratedResources);
		woodModifier = n;
	}

	@Override
	public void setWoods(int woods) {
		if (woods < 0)
			super.setWoods(woods);
		else
			super.setWoods(woods + woodModifier);
	}
	
	

}
