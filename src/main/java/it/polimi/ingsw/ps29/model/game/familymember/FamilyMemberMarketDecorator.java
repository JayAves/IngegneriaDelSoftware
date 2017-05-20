package it.polimi.ingsw.ps29.model.game.familymember;
public class FamilyMemberMarketDecorator extends FamilyMemberDecorator{
	
	int marketModifier;

	public FamilyMemberMarketDecorator(FamilyMember decoratedFamilyMember, int n) {
		super(decoratedFamilyMember);
		marketModifier = n;
	}

	@Override
	public int getMarketPower() {
		return super.getMarketPower() + marketModifier;
	}
	
	

}
