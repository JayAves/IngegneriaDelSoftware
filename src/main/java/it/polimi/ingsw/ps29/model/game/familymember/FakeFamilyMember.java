package it.polimi.ingsw.ps29.model.game.familymember;

/**
 * Instead of decorating the real {@link FamilyMember}
 * @author Giovanni Mele
 * @author Pietro Melzi
 *
 */
public class FakeFamilyMember implements FakeFamilyMemberInterface{
	
	private final int fakepower = 0;
	
	public int getHarvestPower(){
		return fakepower;
	}

	public int getProductionPower(){
		return fakepower;
	}
	
	public int getCharacterTowerPower(){
		return fakepower;
	}
	
	public int getMarketPower(){
		return fakepower;
	}
	
	public int getTerritoryTowerPower(){
		return fakepower;
	}
	
	public int getBuildingTowerPower(){
		return fakepower;
	}
	
	public int getVenturesTowerPower(){
		return fakepower;
	}
}
