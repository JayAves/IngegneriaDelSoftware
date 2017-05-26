package it.polimi.ingsw.ps29.model.game.familymember;
public class FamilyMemberHarvestDecorator extends FamilyMemberDecorator {
	
	private int harvestModifier;

	public FamilyMemberHarvestDecorator(FamilyMemberInterface decoratedFamilyMember, int n) {
		super(decoratedFamilyMember);
		harvestModifier = n;
	}

	@Override
	public int getHarvestPower() {
		return super.getHarvestPower() + harvestModifier;
	}
		
}
