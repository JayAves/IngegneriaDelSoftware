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

	public void setCoins(int coins) {
		decoratedResources.setCoins(coins);
	}

	public int getStones() {
		return decoratedResources.getStones();
	}

	public void setStones(int stones) {
		decoratedResources.setStones(stones);
	}

	public int getServants() {
		return decoratedResources.getServants();
	}

	public void setServants(int servants) {
		decoratedResources.setServants(servants);
	}

	public int getWoods() {
		return decoratedResources.getWoods();
	}

	public void setWoods(int woods) {
		decoratedResources.setWoods(woods);
	}
	
	
}
