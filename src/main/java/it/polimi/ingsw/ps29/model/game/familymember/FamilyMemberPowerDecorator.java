package it.polimi.ingsw.ps29.model.game.familymember;

/**
 * 
 * @author Giovanni Mele
 *
 */
public class FamilyMemberPowerDecorator extends FamilyMemberDecorator {
	
	private int diceModifier;

	public FamilyMemberPowerDecorator(FamilyMemberInterface decoratedFamilyMember, int n) {
		super(decoratedFamilyMember);
		diceModifier = n;
	}

	@Override
	public int getPower() {
		return super.getPower() + diceModifier;
	}
	
	

}
