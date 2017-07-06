package it.polimi.ingsw.ps29.model.game.familymember;

/**
 * 
 * @author Giovanni Mele
 *
 */
public class FakeFamilyMemberVenturesTowerDecorator extends FakeFamilyMemberDecorator{
	
	private int vTowerModifier;

	public FakeFamilyMemberVenturesTowerDecorator(FakeFamilyMemberInterface decoratedFake, int n) {
		super(decoratedFake);
		vTowerModifier = n;
	}

	@Override
	public int getVenturesTowerPower() {
		return super.getVenturesTowerPower() + vTowerModifier;
	}


}
