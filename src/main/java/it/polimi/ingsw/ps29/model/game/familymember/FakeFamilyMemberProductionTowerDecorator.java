package it.polimi.ingsw.ps29.model.game.familymember;

/**
 * 
 * @author Giovanni Mele
 *
 */
public class FakeFamilyMemberProductionTowerDecorator extends FakeFamilyMemberDecorator{
	
	private int pTowerModifier;

	public FakeFamilyMemberProductionTowerDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		pTowerModifier = n;
	}

	@Override
	public int getBuildingTowerPower() {
		return super.getBuildingTowerPower() + pTowerModifier;
	}


}
