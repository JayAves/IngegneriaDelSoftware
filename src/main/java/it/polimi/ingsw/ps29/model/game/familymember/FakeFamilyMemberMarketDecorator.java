package it.polimi.ingsw.ps29.model.game.familymember;

public class FakeFamilyMemberMarketDecorator extends FakeFamilyMemberDecorator{

	private int marketModifier;

	public FakeFamilyMemberMarketDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		marketModifier = n;
	}

	@Override
	public int getMarketPower() {
		return super.getMarketPower() + marketModifier;
	}

}
