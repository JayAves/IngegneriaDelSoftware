package it.polimi.ingsw.ps29.model.game.familymember;

/**the power of the familiar is increased or decreased by a modifier if placed on a character tower
 * 
 * @author Giovanni Mele
 *
 */
public class FakeFamilyMemberCharacterTowerDecorator extends FakeFamilyMemberDecorator{
	
	private int cTowerModifier;

	public FakeFamilyMemberCharacterTowerDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		cTowerModifier = n;
	}

	@Override
	public int getCharacterTowerPower() {
		return super.getCharacterTowerPower() + cTowerModifier;
	}


}
