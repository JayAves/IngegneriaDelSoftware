package it.polimi.ingsw.ps29.model.game.familymember;

public class FamilyMemberTowerDecorator extends FamilyMemberDecorator{
	
	int towerModifier;

	public FamilyMemberTowerDecorator(FamilyMember decoratedFamilyMember, int n) {
		super(decoratedFamilyMember);
		towerModifier = n;
	}

	@Override
	public int getTowerPower() {
		return super.getTowerPower() + towerModifier;
	}
	
	

}
