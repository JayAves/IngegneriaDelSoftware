package it.polimi.ingsw.ps29.model.game.familymember;

/**the power of the familiar is increased or decreased by a modifier if placed on a building tower
 * 
 * @author Giovanni Mele
 *
 */
public class FakeFamilyMemberBuildingTowerDecorator extends FakeFamilyMemberDecorator{
	
	private int pTowerModifier;

	public FakeFamilyMemberBuildingTowerDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		pTowerModifier = n;
	}

	@Override
	public int getBuildingTowerPower() {
		return super.getBuildingTowerPower() + pTowerModifier;
	}


}