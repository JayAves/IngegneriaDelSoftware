package it.polimi.ingsw.ps29.model.game.resources;

public abstract class ResourcesDecorator implements ResourcesInterface {
	
	private Resources decoratedResources;

	public ResourcesDecorator(Resources decoratedResources) {
		super();
		this.decoratedResources = decoratedResources;
	}

	public int getCoins() {
		return decoratedResources.getCoins();
	}

	public void modifyCoins(int coins) {
		decoratedResources.modifyCoins(coins);
	}

	public int getStones() {
		return decoratedResources.getStones();
	}

	public void modifyStones(int stones) {
		decoratedResources.modifyStones(stones);
	}

	public int getServants() {
		return decoratedResources.getServants();
	}

	public void modifyServants(int servants) {
		decoratedResources.modifyServants(servants);
	}

	public int getWoods() {
		return decoratedResources.getWoods();
	}

	public void modifyWoods(int woods) {
		decoratedResources.modifyWoods(woods);
	}
	
	
}
