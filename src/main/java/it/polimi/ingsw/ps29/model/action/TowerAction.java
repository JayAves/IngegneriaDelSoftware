package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.tower.TowerArea;

public class TowerAction extends Action {

	private TowerArea space;
	
	public TowerAction(Match model, Move move) {
		super(model, move);
		this.space = (TowerArea) model.getBoard().getSpace(move.getSpace());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isForbidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPlaceable() {
		// TODO Auto-generated method stub
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) 
				&& space.isEnoughPowerful(move.getFamiliar().getTowerPower()) 
				&& canAffordMalus() 
				&& canAffordCard()
				&& enoughSlotSpace();
		
	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		
		/*non considero l'effetto che blocca il bonus da torri...lo implementeremo in seguito*/
		
		if (!space.isEmpty()) move.getPlayer().getPersonalBoard().getResources().updateResource(new Resource("coin",-3)); //pago le 3 monetef
		
		GainResourcesEffect effect= new GainResourcesEffect (space.takeResource()); //leggo risorse dal piano, se non ne ha aggiungo null
		
		effect.performEffect(move.getPlayer()); //aggiungo le risorse al player, dobbiamo gestire il caso in cui non ci siano risorse
		
		move.getPlayer().getPersonalBoard().addCard(space.takeCard());
		
		for(Resource res: space.takeCard().getCost()) { //pago costi
			
			move.getPlayer().getPersonalBoard().getResources().updateResource(res); 
		
		}
		
		for(Effect immediateEffect : space.takeCard().getImmediateEffects()) {
			
			/*
			if (immediateEffect instanceof BonusActionEffect)
				model.getBoard().getStateOfAction(StateOfActionIdentifier.INCOMPLETE);
			else
				*/
			
			immediateEffect.performEffect(move.getPlayer());
		}
		
		space.getPlacementFloor().setCard(null); //il piano si svota della carta
		
		//move.getFamiliar().setBusy();
		
		//aggiungo l'effetto permanente della carta appena pescata dal giocatore alla classe BonusAndMalusPlayer - da implementare
		
	}
	
	private boolean canAffordMalus() {
		return (move.getPlayer().getPersonalBoard().getResources().getResource((String)"coin").getAmount()>=3);
	}
	
	private boolean canAffordCard() {
		
		ArrayList<Resource> discountedCost=space.takeCard().getCost();
		
		highFloorDiscount(discountedCost); //posso spendere il guadagno di risorse del terzo/quarto piano per prendere la carta
		
		for(Resource res: discountedCost) {
			
			if (res.getAmount()> move.getPlayer().getPersonalBoard().getResources().getResource(res.getType()).getAmount()) return false; 
			
			}
		
		return true;
		
	}
	
	private void highFloorDiscount(ArrayList<Resource> cost) {
		
		for (Resource res: cost) {
			
			for(Resource source: space.takeResource()) {
				
				if (res.getType()==source.getType()) res.modifyAmount(-source.getAmount());
			}
		}
	}
	
	private boolean enoughSlotSpace(){
		
		return move.getPlayer().getPersonalBoard().getCards(space.takeCard().getType()).size()<6;
	}


}
