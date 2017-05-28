package it.polimi.ingsw.ps29.model.game.familymember;

public class FakeFamilyMemberDecorator implements FakeFamilyMemberInterface {
	
	protected FakeFamilyMemberInterface decoratedFakeFamilyMember;
	
	public FakeFamilyMemberDecorator(FakeFamilyMemberInterface fake) {
		decoratedFakeFamilyMember = fake;
	}

	@Override
	public int getHarvestPower() {
		return decoratedFakeFamilyMember.getHarvestPower();
	}

	@Override
	public int getProductionPower() {
		return decoratedFakeFamilyMember.getProductionPower();
	}

	@Override
	public int getMarketPower() {
		return decoratedFakeFamilyMember.getMarketPower();
	}

	@Override
	public int getCharacterTowerPower() {
		return decoratedFakeFamilyMember.getCharacterTowerPower();
	}

	@Override
	public int getHarvestTowerPower() {
		return decoratedFakeFamilyMember.getHarvestTowerPower();
	}

	@Override
	public int getProductionTowerPower() {
		return decoratedFakeFamilyMember.getProductionTowerPower();
	}

	@Override
	public int getVenturesTowerPower() {
		return decoratedFakeFamilyMember.getVenturesTowerPower();
	}

}
