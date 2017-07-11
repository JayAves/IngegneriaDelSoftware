package it.polimi.ingsw.ps29.model.game.familymember;

/**the power of the familiar is increased  or decreased by a modifier if placed on market space
 * 
 * @author Giovanni Mele
 *
 */
public class FakeFamilyMemberMarketDecorator extends FakeFamilyMemberDecorator{

	private int marketModifier;

	public FakeFamilyMemberMarketDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		marketModifier = n;
	}

	@Override
	public int getMarketPower() {
		return super.getMarketPower() + marketModifier;
	}

}
