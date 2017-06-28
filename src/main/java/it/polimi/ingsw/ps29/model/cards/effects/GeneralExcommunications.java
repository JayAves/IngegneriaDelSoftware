package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class GeneralExcommunications extends Effect{
	
	private String effectName;
	
	public GeneralExcommunications( String effectName) {
		
		this.effectName = effectName;
	}

	@Override
	public void performEffect(Player player) {
		
		switch (effectName) {
		case "SistoIV" : player.setSistoIV();
		      break;
		case "NoMarket" : player.setNoMarket();
		      break;
		case "Filippo Brunelleschi" : player.setFilippoBrunelleschi();
			  break;
		case "Ludovico Ariosto" : player.setLudovicoAriosto();
		      break;
		}
	}

	@Override
	public String toString() {
		return super.toString()+ effectName;
	}


}
