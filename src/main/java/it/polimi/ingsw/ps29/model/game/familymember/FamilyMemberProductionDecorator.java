package it.polimi.ingsw.ps29.model.game.familymember;

public class FamilyMemberProductionDecorator extends FamilyMemberDecorator {
	
	int productionModifier;

	public FamilyMemberProductionDecorator(FamilyMember decoratedFamilyMember, int n) {
		super(decoratedFamilyMember);
		productionModifier = n;
	}

	@Override
	public int getProductionPower() {
		return super.getProductionPower() + productionModifier;
	}


	
}
