package it.polimi.ingsw.ps29.model.game.resources;

public class Resources implements ResourcesInterface{
	
	private Coins coins;
	private Stones stones;
	private Servants servants;
	private Woods woods;
	
  //tutti i set devono lanciare un eccezione nel caso il valore vada sotto 0
	

	@Override
	public int getCoins() {
		return coins.getAmount();
	}

	@Override
	public void modifyCoins(int coins) {
		this.coins.modifyAmount(coins);
	}

	@Override
	public int getWoods() {
		return woods.getAmount();
	}

	@Override
	public void modifyWoods(int woods) {
		this.woods.modifyAmount(woods);
	}

	@Override
	public int getStones() {
		return stones.getAmount();
	}

	@Override
	public void modifyStones(int stones) {
		this.stones.modifyAmount(stones);
	}

	@Override
	public int getServants() {
		return servants.getAmount();
	}

	@Override
	public void modifyServants(int servants) {
		this.servants.modifyAmount(servants);
	}
		
	
}
	
	
