package it.polimi.ingsw.ps29.model.game.familymember;

/**the power of the familiar is increased or decreased by a modifier if used to perform a harvest
 * 
 * @author Giovanni Mele
 *
 */
public class FakeFamilyMemberHarvestDecorator extends FakeFamilyMemberDecorator{
	
	private int harvestModifier;

	public FakeFamilyMemberHarvestDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		harvestModifier = n;
	}

	@Override
	public int getHarvestPower() {
		return super.getHarvestPower() + harvestModifier;
	}

}
