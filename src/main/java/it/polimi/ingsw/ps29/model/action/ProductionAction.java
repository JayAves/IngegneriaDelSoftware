package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.messages.exception.SpaceOccupiedException;
import it.polimi.ingsw.ps29.model.action.actionstates.AskAboutExchangeState;
import it.polimi.ingsw.ps29.model.cards.BuildingCard;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.ExchangeSupport;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.ActivityArea;

/**
 * When player tries to place a familiar in ProductionArea
 * @author Pietro Grotti
 * @author Pietro Melzi
 *
 */
public class ProductionAction extends Action {
	
	private ActivityArea space;
	private int penalty;
	
	public ProductionAction(Match model, Move move) {
		super(model, move);
		this.space = (ActivityArea) model.getBoard().getSpace(move.getSpace());
		
		penalty = 0;
	}

	

	@Override
	public boolean isPlaceable() throws RejectException {
		return (twoPlayersCheck() && move.getFamiliar().getFamiliarColor()==DiceColor.BONUS ||  move.getFamiliar().getFamiliarColor()==DiceColor.NEUTRAL ||
				!space.familiarHere(move.getFamiliar().getPlayerColor()) ) && space.isEnoughPowerful(
				move.getFamiliar().getPower() + move.getPlayer().getFakeFamiliar().getProductionPower() + move.getServants());
	}

	private boolean twoPlayersCheck() throws SpaceOccupiedException {
		if ((!space.isEmpty()) && (space.getClosed()))
			throw new SpaceOccupiedException();
		else return true;
	}
	
	@Override
	public void performAction() {
		if(!space.isEmpty() && !move.getPlayer().getLudovicoAriosto())
			penalty = -3;
		
		//placement (excpet for BonusAction
		if(move.getFamiliar().getFamiliarColor()!=DiceColor.BONUS)
			space.placeFamiliar(move.getFamiliar(), move.getPlayer().getLudovicoAriosto());
		
		//save all exchange options
		ArrayList<ExchangeResourcesEffect> options = buildExchangeSupportVector();
		
		if(!options.isEmpty()) {
			//save all resources states and the options in Player
			ExchangeSupport support = new ExchangeSupport(options, move.getPlayer().getPersonalBoard().getResources());
			// remove all exchanges that player can't do
			support.checkVector();
			
			if(!support.getOptions().isEmpty()) {
				move.getPlayer().setSupport(support);
				//state is created with only doable exchanges
				state = new AskAboutExchangeState(move.getPlayer().getSupport().getOptions());
			}
		}
				
		//handle bonus from PersonalBonuTile
		ArrayList<Resource> bonusFromTile= move.getPlayer().getPersonalBoard().getPersonalBonusTile().getProductionBonus();	
		GainResourcesEffect bonusProductionTile = new GainResourcesEffect(bonusFromTile);
		bonusProductionTile.performEffect(move.getPlayer());
		
		for(Card card: move.getPlayer().getPersonalBoard().getCards("building"))
			for(Effect effect: card.getPermanentEffects())
				if(!(effect instanceof ExchangeResourcesEffect))
					effect.performEffect(move.getPlayer());
		
		move.getFamiliar().setBusy(true);
		
	}
	
	/**
	 * Finds exchanges that can be activated and prepares a list of them.
	 * @return a list of Effects
	 * @see it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect
	 */
	public ArrayList<ExchangeResourcesEffect> buildExchangeSupportVector () {
		ArrayList<ExchangeResourcesEffect> options = new ArrayList<ExchangeResourcesEffect>();
		for(Card card: move.getPlayer().getPersonalBoard().getCards("building"))
			for(Effect effect: card.getPermanentEffects()) 
				//if effect is exchange, save it for following state
				if(effect instanceof ExchangeResourcesEffect && ((BuildingCard)card).getProductionForce()<=
						move.getFamiliar().getPower() + penalty + move.getPlayer().getFakeFamiliar().getProductionPower() + move.getServants())
					options.add((ExchangeResourcesEffect)effect);
		return options;
	}
	


}
