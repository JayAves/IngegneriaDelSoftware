package it.polimi.ingsw.ps29.model.game.familymember;

/**the power of the familiar is increased or decreased by a modifier if placed on a territory tower
 * 
 * @author Giovanni Mele
 *
 */
public class FakeFamilyMemberTerritoryTowerDecorator extends FakeFamilyMemberDecorator{
	
	private int hTowerModifier;

	public FakeFamilyMemberTerritoryTowerDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		hTowerModifier = n;
	}

	@Override
	public int getTerritoryTowerPower() {
		return super.getTerritoryTowerPower() + hTowerModifier;
	}


}
