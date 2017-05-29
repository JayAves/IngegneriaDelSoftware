package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.space.MarketArea;

public class MarketAction extends Action{

	private MarketArea space;

	public MarketAction(Match model, Move move) {
		super(model, move);
		this.space = (MarketArea) model.getBoard().getSpace(move.getSpace());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isForbidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPlaceable() {
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getMarketPower()+move.getServants());
	}

	@Override
	public void performAction(/*Player currentPlayer, MarketArea area*/) {
		/*
		 * ArrayList <Gift> giftsCopy = area.getGifts();
		 * ciclo for in cui per ogni gift di tipo Gift nell'ArrayList viene eseguito
		 * currentPlayer.Resources.getResources(gift.getResource();, gift.getAmount();)
		 */
		
		GainResourcesEffect effect = new GainResourcesEffect(space.getSlot().getBonus());
		effect.performEffect(move.getPlayer());
		
	}
	

}
