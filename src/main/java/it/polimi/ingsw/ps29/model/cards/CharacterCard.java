package it.polimi.ingsw.ps29.model.cards;


import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Coin;

public class CharacterCard extends Card {
	
	private Coin coinCost;
	
	public CharacterCard(String name, Period period, String type, Coin cost) {
		super(name, period, type);
		// TODO Auto-generated constructor stub
	}

}
