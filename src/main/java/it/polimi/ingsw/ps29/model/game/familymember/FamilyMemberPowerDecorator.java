package it.polimi.ingsw.ps29.model.game.familymember;

/**increase or decrease the familiar power by a constant. It affects all kinds of action.
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
	
	@Override
	public String toString(){
		return "FamilyMember [power=" + getPower() + ", busy=" + getBusy() + ", familiarColor=" + getFamiliarColor() + ", playerColor="
				+ getPlayerColor() + "]";
	}
	
	

}
