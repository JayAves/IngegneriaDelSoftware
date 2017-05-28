package it.polimi.ingsw.ps29.model.game.familymember;

public class FakeFamilyMemberProductionTowerDecorator extends FakeFamilyMemberDecorator{
	
	private int pTowerModifier;

	public FakeFamilyMemberProductionTowerDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		pTowerModifier = n;
	}

	@Override
	public int getProductionTowerPower() {
		return super.getProductionTowerPower() + pTowerModifier;
	}


}
