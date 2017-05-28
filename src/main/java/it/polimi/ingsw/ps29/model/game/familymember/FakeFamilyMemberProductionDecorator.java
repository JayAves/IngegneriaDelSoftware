package it.polimi.ingsw.ps29.model.game.familymember;

public class FakeFamilyMemberProductionDecorator extends FakeFamilyMemberDecorator{
	
	private int productionModifier;

	public FakeFamilyMemberProductionDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		productionModifier = n;
	}

	@Override
	public int getProductionPower() {
		return super.getProductionPower() + productionModifier;
	}


}
