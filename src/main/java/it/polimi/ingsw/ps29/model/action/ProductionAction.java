package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.action.state.AskAboutExchangeState;
import it.polimi.ingsw.ps29.model.action.state.StateOfActionIdentifier;
import it.polimi.ingsw.ps29.model.cards.BuildingCard;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.ProductionArea;

public class ProductionAction extends Action {
	
	private ProductionArea space;
	
	public ProductionAction(Match model, Move move) {
		super(model, move);
		this.space = (ProductionArea) model.getBoard().getSpace(move.getSpace());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isForbidden() { //da verificare carte scomunica in arrayList di bonusAndMalusPlyer
		
		return false;
	}

	@Override
	public boolean isPlaceable() {
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getProductionPower()+move.getServants());
	}

	@Override
	public void performAction() {
		//placement
		if (space.isEmpty()) space.headPlacement (move.getFamiliar());
		else space.queuePlacement(move.getFamiliar());
		
				
		//gestione bonus della tile
		ArrayList<Resource> bonusFromTile= move.getPlayer().getPersonalBoard().getPersonalBonusTile().getProductionBonus();	
		GainResourcesEffect bonusProductionTile = new GainResourcesEffect(bonusFromTile);
		bonusProductionTile.performEffect(move.getPlayer());
		
		ArrayList<Card> importedSlot= move.getPlayer().getPersonalBoard().getCards("Building");
		//ciclo lettura effetti da personalBoard
		for(Card card: importedSlot) {
			for(Effect effect: card.getPermanentEffects()) {
				if (move.getFamiliar().getProductionPower()> ((BuildingCard)card).getProductionForce()) {
					if(!(state instanceof AskAboutExchangeState))
						state = new AskAboutExchangeState(0);
					else 
						((AskAboutExchangeState)state).next();
					effect.performEffect(move.getPlayer());
				}
			}
		}
		
	}
	
	


}
