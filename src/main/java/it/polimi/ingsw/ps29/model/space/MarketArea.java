package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.exception.NotEnoughPowerfulException;
import it.polimi.ingsw.ps29.messages.exception.SpaceOccupiedException;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class MarketArea implements ActionSpace {
	
	private BonusActionSpace slot;
	
	public MarketArea (int power, ArrayList <Resource> bonus) {
		slot = new BonusActionSpace (power);
		slot.setBonus(bonus);
	}
	
	public BonusActionSpace getSlot () {
		return slot;
	}

	@Override
	public boolean isEmpty() throws SpaceOccupiedException {
		if (slot.isEmpty())
			return true;
		throw new SpaceOccupiedException();
	}

	@Override
	public boolean familiarHere(Color playerColor) {
		return slot.familiarHere(playerColor);
	}

	@Override
	public boolean isEnoughPowerful(int valuePlacement) throws NotEnoughPowerfulException {
		if (slot.isEnoughPowerful(valuePlacement))
			return true;
		throw new NotEnoughPowerfulException();
	}

	@Override
	public void cleanSpace() {
		slot.cleanSpace();
		
	}

	@Override
	public void placeFamiliar(FamilyMemberInterface familiar) {
		slot.setFamilyMember(familiar);
		
	}
	
}
