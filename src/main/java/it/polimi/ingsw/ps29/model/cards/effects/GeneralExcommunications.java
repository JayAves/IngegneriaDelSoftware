package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class GeneralExcommunications extends Effect{
	
	private boolean noPlacementMarket;
	private boolean loseFirstTurn;
	private boolean doubleCostServants;

	@Override
	public void performEffect(Player player) {
		if(noPlacementMarket){
			//new FamilyMemberMarketDecorator(player.fakeFamiliar, 0);
		}
		else if (loseFirstTurn) {
			//chiamo la funzione che modifica la gestione del turno
		}
		else if (doubleCostServants) {
			//chiamo il modificatore della risorsa 
		}
		
	}

	public boolean isNoPlacementMarket() {
		return noPlacementMarket;
	}

	public void setNoPlacementMarket(boolean noPlacementMarket) {
		this.noPlacementMarket = noPlacementMarket;
	}

	public boolean isLoseFirstTurn() {
		return loseFirstTurn;
	}

	public void setLoseFirstTurn(boolean loseFirstTurn) {
		this.loseFirstTurn = loseFirstTurn;
	}

	public boolean isDoubleCostServants() {
		return doubleCostServants;
	}

	public void setDoubleCostServants(boolean doubleCostServants) {
		this.doubleCostServants = doubleCostServants;
	}

}
