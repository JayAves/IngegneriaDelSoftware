package it.polimi.ingsw.ps29.model.game.familymember;

public class FakeFamilyMemberHarvestTowerDecorator extends FakeFamilyMemberDecorator{
	
	private int hTowerModifier;

	public FakeFamilyMemberHarvestTowerDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		hTowerModifier = n;
	}

	@Override
	public int getHarvestTowerPower() {
		return super.getHarvestTowerPower() + hTowerModifier;
	}


}
