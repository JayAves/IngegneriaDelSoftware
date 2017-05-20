package it.polimi.ingsw.ps29.model.game.resources;

public class Resources implements ResourcesInterface{
	
	private int coins;
	private int stones;
	private int servants;
	private int woods;
	
  //tutti i set devono lanciare un eccezione nel caso il valore vada sotto 0
	
	@Override
	public int getCoins() {
		return coins;
	}
	
	@Override
	public void setCoins(int coins) {
		this.coins += coins;
	}
	
	@Override
	public int getStones() {
		return stones;
	}
	
	@Override
	public void setStones(int stones) {
		this.stones += stones;
	}
	
	@Override
	public int getServants() {
		return servants;
	}
	
	@Override
	public void setServants(int servants) {
		this.servants += servants;
	}
	
	@Override
	public int getWoods() {
		return woods;
	}
	
	@Override
	public void setWoods(int woods) {
		this.woods += woods;
	}
		
}
	
	
