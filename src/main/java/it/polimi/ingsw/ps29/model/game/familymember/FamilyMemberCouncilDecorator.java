package it.polimi.ingsw.ps29.model.game.familymember;
public class FamilyMemberCouncilDecorator extends FamilyMemberDecorator {
	
	int councilModifier;

	public FamilyMemberCouncilDecorator(FamilyMember decoratedFamilyMember, int n) {
		super(decoratedFamilyMember);
		councilModifier = n;
	}

	@Override
	public int getCouncilPower() {
		return super.getCouncilPower() + councilModifier;
	}
	
	

}
