package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.messages.exception.RejectException;
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
	public boolean isPlaceable() throws RejectException {
		return  space.isEnoughPowerful(
				move.getFamiliar().getPower() + move.getPlayer().getFakeFamiliar().getMarketPower() + move.getServants()) &&
				(space.isEmpty() || move.getPlayer().getLudovicoAriosto());
	}

	@Override
	public void performAction(/*Player currentPlayer, MarketArea area*/) {		
		GainResourcesEffect effect = new GainResourcesEffect(space.getSlot().getBonus());
		effect.performEffect(move.getPlayer());
		space.placeFamiliar(move.getFamiliar(), move.getPlayer().getLudovicoAriosto());
		move.getFamiliar().setBusy(true);
	}
	

}
