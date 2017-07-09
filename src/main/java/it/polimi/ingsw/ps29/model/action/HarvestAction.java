package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.messages.exception.SpaceOccupiedException;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.TerritoryCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.ActivityArea;

/**
 * When player tries to place a familiar in HarvestArea 
 * @author Pietro Grotti
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.model.space.HarvestArea
 *
 */
public class HarvestAction extends Action {
	
	private ActivityArea space;
	private int penalty;
	
	public HarvestAction(Match model, Move move) {
		super(model, move);
		this.space = (ActivityArea) model.getBoard().getSpace(move.getSpace());
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public boolean isPlaceable() throws RejectException {
		return ( twoPlayersCheck() && move.getFamiliar().getFamiliarColor()==DiceColor.BONUS || move.getFamiliar().getFamiliarColor()==DiceColor.NEUTRAL ||
				!space.familiarHere(move.getFamiliar().getPlayerColor())) 
				&& space.isEnoughPowerful(move.getFamiliar().getPower() + move.getPlayer().getFakeFamiliar().getHarvestPower() + move.getServants());
	}

	@Override
	public void performAction() {
		if(!space.isEmpty())
			penalty = -3;
		
		//placement (se azione bonus non lo faccio)
		if(move.getFamiliar().getFamiliarColor()!=DiceColor.BONUS)
			space.placeFamiliar(move.getFamiliar(), move.getPlayer().getLudovicoAriosto());
		
		//gestione bonus della tile
		ArrayList<Resource> bonusFromTile= move.getPlayer().getPersonalBoard().getPersonalBonusTile().getHarvestBonus();	
		GainResourcesEffect bonusHarvestTile = new GainResourcesEffect(bonusFromTile);
		bonusHarvestTile.performEffect(move.getPlayer());
		
		
		ArrayList<Card> importedSlot= move.getPlayer().getPersonalBoard().getCards("territory");
		//ciclo lettura effetti da personalBoard
		for(Card card: importedSlot) {
			for(Effect effect: card.getPermanentEffects()) {
				if (move.getFamiliar().getPower() + penalty + move.getPlayer().getFakeFamiliar().getProductionPower() + move.getServants() >=
				 ((TerritoryCard)card).getHarvestForce()) {
					effect.performEffect(move.getPlayer());
					if (effect instanceof GainResourcesEffect){
						System.out.println(move.getFamiliar().getPower() + penalty + move.getPlayer().getFakeFamiliar().getProductionPower() + move.getServants());
						System.out.println(((TerritoryCard)card).getHarvestForce());
						for (Resource res: ((GainResourcesEffect)effect).getResources())
							System.out.println(res.getType()+" - "+res.getAmount());
					}
				}
			}
		}
		move.getFamiliar().setBusy(true);
		
	}
	
	private boolean twoPlayersCheck() throws SpaceOccupiedException {
		if ((!space.isEmpty()) && space.getClosed())
				throw new SpaceOccupiedException();
		else return true;
	}
}
