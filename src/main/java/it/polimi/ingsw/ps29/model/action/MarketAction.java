package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.messages.exception.SpaceClosedException;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.space.MarketArea;

/**
 * When player tries to place a familiar in one MarketArea
 * @author Pietro Grotti
 * 
 */
public class MarketAction extends Action{

	private MarketArea space;

	public MarketAction(Match model, Move move) {
		super(model, move);
		this.space = (MarketArea) model.getBoard().getSpace(move.getSpace());
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public boolean isPlaceable() throws RejectException {
		return spaceClosedCheck() && space.isEnoughPowerful(
				move.getFamiliar().getPower() + move.getPlayer().getFakeFamiliar().getMarketPower() + move.getServants()) &&
				(space.isEmpty() || move.getPlayer().getLudovicoAriosto());
	}

	@Override
	public void performAction() {		
		GainResourcesEffect effect = new GainResourcesEffect(space.getSlot().getBonus());
		effect.performEffect(move.getPlayer());
		space.placeFamiliar(move.getFamiliar(), move.getPlayer().getLudovicoAriosto());
		move.getFamiliar().setBusy(true);
	}
	
	private boolean spaceClosedCheck() throws SpaceClosedException {
		if (space.getClosed())
			throw new SpaceClosedException();
		else return true;
	}

}
