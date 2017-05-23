package it.polimi.ingsw.ps29.model.cards;

import javax.annotation.Resource;

import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.ResourceOld;

public class CharacterCard extends Card {
	
	private Resource coinCost;
	
	public CharacterCard(String name, Period period, CardType type) {
		super(name, period, type);
		// TODO Auto-generated constructor stub
	}

}
