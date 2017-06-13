package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.DTO.CardDTO;
import it.polimi.ingsw.ps29.model.cards.effects.DiscountForCardTypeEffect;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.TowerArea;

public class TowerAction extends Action {

	private TowerArea space;
	
	public TowerAction(Match model, Move move) {
		super(model, move);
		this.space = (TowerArea) model.getBoard().getSpace(move.getSpace());
		space.setPlacementFloor(move.getFloor());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isForbidden() {
		return false;
	}

	@Override
	public boolean isPlaceable() {
		// TODO Auto-generated method stub
		/*System.out.println(move.getFamiliar().getTowerPower());
		System.out.println(space.familiarHere(move.getFamiliar().getPlayerColor()));
		System.out.println(space.isEnoughPowerful(move.getFamiliar().getTowerPower()+move.getServants()));
		System.out.println(canAffordMalus());
		System.out.println(canAffordCard());
		System.out.println(enoughSlotSpace());
		System.out.println(enoughVictoryPoints());*/
		
		if (!space.familiarHere(move.getFamiliar().getPlayerColor()) 
		&& canAffordMalus() && canAffordCard()&& enoughSlotSpace()
		&& enoughVictoryPoints() && !move.getFamiliar().getBusy()) {
			int power;
			switch (move.getSpace()) {
				case "territoryTower":
					power = move.getFamiliar().getPower() + move.getPlayer().getFakeFamiliar().getTerritoryTowerPower() + move.getServants();
					break;
				case "buildingTower":
					power = move.getFamiliar().getPower() + move.getPlayer().getFakeFamiliar().getBuildingTowerPower() + move.getServants();
					break;
				case "characterTower":
					power = move.getFamiliar().getPower() + move.getPlayer().getFakeFamiliar().getCharacterTowerPower() + move.getServants();
					break;
				case "ventureTower":
					power = move.getFamiliar().getPower() + move.getPlayer().getFakeFamiliar().getVenturesTowerPower() + move.getServants();
					break;
				default:
					power = move.getFamiliar().getPower() + move.getServants();
			}
			return space.isEnoughPowerful(power);
		}
		return false;
		
	}

	@Override
	public void performAction() {
		/*non considero l'effetto che blocca il bonus da torri...lo implementeremo in seguito*/
		
		if (!space.isEmpty()) 
			move.getPlayer().getPersonalBoard().getResources().updateResource(new Resource("coin",-3)); //pago le 3 monete
			
		
		if(move.getFloor()>2) {
			GainResourcesEffect effect= new GainResourcesEffect (space.takeResource()); //leggo risorse dal piano, se non ne ha aggiungo null
		
			effect.performEffect(move.getPlayer()); //aggiungo le risorse al player
		}
		
		move.getPlayer().getPersonalBoard().addCard(space.takeCard());
		model.infoForView.getPersonalBoard(move.getPlayer().getName()).insertCard(
				new CardDTO (0, space.takeCard().getType(), space.takeCard().toString()));
		
		ArrayList<Resource> discountedCosts= space.takeCard().getCost();
		
		applyDiscounts(discountedCosts);
		
		for(Resource res: discountedCosts)  //pago costi
			
			move.getPlayer().getPersonalBoard().getResources().updateResource(res); 
			
		
		for(Effect immediateEffect : space.takeCard().getImmediateEffects()) {
			
			/*
			if (immediateEffect instanceof BonusActionEffect)
				state = new BonuActionState (((BonusActionEffect)immediateEffect).clone());
			else
				*/
			
			immediateEffect.performEffect(move.getPlayer());
		}
		
		space.getPlacementFloor().setCard(null); 
		
		move.getFamiliar().setBusy(true);
	
	}
		
		//aggiungo l'effetto permanente della carta appena pescata dal giocatore alla classe BonusAndMalusPlayer - da implementare
	
	private boolean canAffordMalus() {
		return (move.getPlayer().getPersonalBoard().getResources().getResource("coin").getAmount()>=3);
	}
	
	private boolean canAffordCard() {
		
		ArrayList<Resource> discountedCost=space.takeCard().getCost();
		
		if(move.getFloor()>2)
			highFloorDiscount(discountedCost); //posso spendere il guadagno di risorse del terzo/quarto piano per prendere la carta
		
		applyDiscounts(discountedCost);
		
		for(Resource res: discountedCost) {
			
			if (res.getAmount()> move.getPlayer().getPersonalBoard().getSpecificResource(res.getType()).getAmount()) 
				return false; 
			
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

	private boolean enoughVictoryPoints() {
		if(space.takeCard().getType().equals("territory")) {
			
			int size= move.getPlayer().getPersonalBoard().getCards("territory").size();
			
			switch (size) {
			
			case 2:
				return(move.getPlayer().getPersonalBoard().getSpecificResource("territory").getAmount()>=1);
			
			case 3:
				return(move.getPlayer().getPersonalBoard().getSpecificResource("territory").getAmount()>=4);
			
			case 4:
				return(move.getPlayer().getPersonalBoard().getSpecificResource("territory").getAmount()>=10);
				
			case 5:
				return(move.getPlayer().getPersonalBoard().getSpecificResource("territory").getAmount()>=20);	

			default:
				return true;
				
			}
			
		}
		
		else 
			return true;
	}
	
	private void applyDiscounts( ArrayList<Resource> costs) {
		
		for (Effect eff: move.getPlayer().specialPermanentEffects) {
			
			if (eff.toString().equals("DiscountForCardType")) {
				
				for(Resource res: ((DiscountForCardTypeEffect)eff).getDiscount()) {
					
					for (Resource source: costs) {
						
						if (res.getType()==source.getType()) 
							
							source.modifyAmount(-res.getAmount());
					
					}
				}
			}
		}
	}


}
