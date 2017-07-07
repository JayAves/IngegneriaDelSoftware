package it.polimi.ingsw.ps29.model.game.familymember;

/**
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
