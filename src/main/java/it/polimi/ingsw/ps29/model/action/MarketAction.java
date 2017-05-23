package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.space.MarketArea;

public class MarketAction implements Action{

	private Move move; 
	private MarketArea space;
	
	
	public MarketAction(Move move, MarketArea space) {
		super();
		this.move = move;
		this.space = space;
	}

	@Override
	public boolean isForbidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPlaceable() {
		// TODO Auto-generated method stub
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getMarketPower());
	}

	@Override
	public void performAction(/*Player currentPlayer, MarketArea area*/) {
		/*
		 * ArrayList <Gift> giftsCopy = area.getGifts();
		 * ciclo for in cui per ogni gift di tipo Gift nell'ArrayList viene eseguito
		 * currentPlayer.Resources.getResources(gift.getResource();, gift.getAmount();)
		 */
		
	}
	

}
