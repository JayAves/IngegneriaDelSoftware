package it.polimi.ingsw.ps29.model.space;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Resource;

public class MarketArea implements ActionSpace {
	
	private BonusActionSpace slot;
	
	public MarketArea (int power, ArrayList <Resource> bonus) {
		slot = new BonusActionSpace (power, bonus);
	}

	@Override
	public boolean isEmpty() {
		return slot.isEmpty();
	}

	@Override
	public boolean familiarHere(Color playerColor) {
		return slot.familiarHere(playerColor);
	}

	@Override
	public boolean isEnoughPowerful(int valuePlacement) {
		return slot.isEnoughPowerful(valuePlacement);
	}
	
	

}