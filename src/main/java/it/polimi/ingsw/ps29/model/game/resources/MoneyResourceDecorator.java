package it.polimi.ingsw.ps29.model.game.resources;

public class MoneyResourceDecorator extends ResourcesDecorator{
	
	private int moneyGetterModifier;

	public MoneyResourceDecorator(Resources decoratedResources, int n) {
		super(decoratedResources);
		moneyGetterModifier = n;
	}
	
	@Override
	public void setCoins(int coins) {
		if (coins < 0 )
			super.setCoins(coins);
		else 
			super.setCoins(coins + moneyGetterModifier);
	}
	
}

