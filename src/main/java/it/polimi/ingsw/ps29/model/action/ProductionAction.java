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

public class ProductionAction extends Action {
	
	private ActivityArea space;
	private int penalty;
	
	public ProductionAction(Match model, Move move) {
		super(model, move);
		this.space = (ActivityArea) model.getBoard().getSpace(move.getSpace());
		// TODO Auto-generated co
		penalty = 0;
	}

	@Override
	public boolean isForbidden() { //da verificare carte scomunica in arrayList di bonusAndMalusPlyer
		
		return false;
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
		
		//placement (se azione bonus non lo faccio)
		if(move.getFamiliar().getFamiliarColor()!=DiceColor.BONUS)
			space.placeFamiliar(move.getFamiliar(), move.getPlayer().getLudovicoAriosto());
		
		//memorizzo tutti gli effetti di scambio per i quali posso chiedere all'utente se li vuole attivare
		ArrayList<ExchangeResourcesEffect> options = buildExchangeSupportVector();
		
		if(!options.isEmpty()) {
			//salvo lo stato delle risorse e gli scambi che devo chiedere all'utente in una variabile di player
			ExchangeSupport support = new ExchangeSupport(options, move.getPlayer().getPersonalBoard().getResources());
			//rimuovo tutti gli scambi che non sono possibili a causa delle risorse del giocatore
			support.checkVector();
			
			move.getPlayer().setSupport(support);
			//creo lo stato con solo gli scambi che possono essere effettivemente fattibili
			state = new AskAboutExchangeState(move.getPlayer().getSupport().getOptions());
		}
				
		//gestione bonus della tile
		ArrayList<Resource> bonusFromTile= move.getPlayer().getPersonalBoard().getPersonalBonusTile().getProductionBonus();	
		GainResourcesEffect bonusProductionTile = new GainResourcesEffect(bonusFromTile);
		bonusProductionTile.performEffect(move.getPlayer());
		
		for(Card card: move.getPlayer().getPersonalBoard().getCards("building"))
			for(Effect effect: card.getPermanentEffects())
				if(!(effect instanceof ExchangeResourcesEffect))
					effect.performEffect(move.getPlayer());
		
		move.getFamiliar().setBusy(true);
		
	}
	
	public ArrayList<ExchangeResourcesEffect> buildExchangeSupportVector () {
		ArrayList<ExchangeResourcesEffect> options = new ArrayList<ExchangeResourcesEffect>();
		for(Card card: move.getPlayer().getPersonalBoard().getCards("building"))
			for(Effect effect: card.getPermanentEffects()) 
				//se l'effetto è di tipo scambio e il valore della mossa è >= del valore della carta
				if(effect instanceof ExchangeResourcesEffect && ((BuildingCard)card).getProductionForce()<=
						move.getFamiliar().getPower() + penalty + move.getPlayer().getFakeFamiliar().getProductionPower() + move.getServants())
					options.add((ExchangeResourcesEffect)effect);
		return options;
	}
	


}
