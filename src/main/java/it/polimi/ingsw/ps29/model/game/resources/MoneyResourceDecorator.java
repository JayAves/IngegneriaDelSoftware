/*package it.polimi.ingsw.ps29.model.game.resources;

public class MoneyResourceDecorator extends ResourcesDecorator{
	
	private int moneyGetterModifier;

	public MoneyResourceDecorator(Container decoratedResources, int n) {
		super(decoratedResources);
		moneyGetterModifier = n;
	}
	
	@Override
	public void modifyCoins(int coins) {
		if (coins < 0 )
			super.modifyCoins(coins);
		else 
			super.modifyCoins(coins + moneyGetterModifier);
	}
	
}

*/