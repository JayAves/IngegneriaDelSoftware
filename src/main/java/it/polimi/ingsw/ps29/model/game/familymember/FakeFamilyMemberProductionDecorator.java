package it.polimi.ingsw.ps29.model.game.familymember;

/**the power of the familiar is increased or decreased by a modifier if used to perform a prodiction
 * 
 * @author Giovanni Mele
 *
 */
public class FakeFamilyMemberProductionDecorator extends FakeFamilyMemberDecorator{
	
	private int productionModifier;

	public FakeFamilyMemberProductionDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		productionModifier = n;
	}

	@Override
	public int getProductionPower() {
		return super.getProductionPower() + productionModifier;
	}


}
