package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.exception.FullCardBoardException;
import it.polimi.ingsw.ps29.messages.exception.NotEnoughResourcesException;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.messages.exception.SpaceOccupiedException;
import it.polimi.ingsw.ps29.messages.exception.TerritoryCardException;
import it.polimi.ingsw.ps29.messages.exception.TowerCoinMalusException;
import it.polimi.ingsw.ps29.model.action.actionstates.BonusActionState;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.DiscountForCardTypeEffect;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.DiceColor;
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
	public boolean isPlaceable() throws RejectException {
		// TODO Auto-generated method stub
		/*System.out.println(move.getFamiliar().getTowerPower());
		System.out.println(space.familiarHere(move.getFamiliar().getPlayerColor()));
		System.out.println(space.isEnoughPowerful(move.getFamiliar().getTowerPower()+move.getServants()));
		System.out.println(canAffordMalus());
		System.out.println(canAffordCard());
		System.out.println(enoughSlotSpace());
		System.out.println(enoughVictoryPoints());*/
		
		//se è presente un familiare qualsiasi sulla torre e non si possono pagare tre monete
		if(!space.isEmpty() && (!canAffordMalus() && !move.getPlayer().getBrunelleschi()))
			throw new TowerCoinMalusException();
		
		if(!space.getPlacementFloor().isEmpty() && !move.getPlayer().getLudovicoAriosto())
			throw new SpaceOccupiedException();			
		
		if ( (move.getFamiliar().getFamiliarColor()==DiceColor.BONUS ||  move.getFamiliar().getFamiliarColor()==DiceColor.NEUTRAL ||
				!space.familiarHere(move.getFamiliar().getPlayerColor()) ) &&
		canAffordCardResourcesCost() && enoughSlotSpace()
		&& enoughMilitaryPoints())  {
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
		
		if ((!space.isEmpty() && !move.getPlayer().getBrunelleschi())) 
			move.getPlayer().getPersonalBoard().getResources().updateResource(new Resource("coin",-3)); //pago le 3 monete
		
		if(move.getFloor()>2) {
			GainResourcesEffect effect= new GainResourcesEffect (space.takeResource()); //leggo risorse dal piano, se non ne ha aggiungo null
			effect.performEffect(move.getPlayer()); //aggiungo le risorse al player
		}
		
		move.getPlayer().getPersonalBoard().addCard(space.takeCard());
		ArrayList<Resource> discountedCosts= space.takeCard().getCost();
		//if I have permanent effects about discounts...
		applyDiscounts(discountedCosts);
		
		for(Resource res: discountedCosts){  //pago costi
			res.negativeAmount();
			move.getPlayer().getPersonalBoard().getResources().updateResource(res); 
		}
		
		for(Effect immediateEffect : space.takeCard().getImmediateEffects()) 
			if (immediateEffect instanceof BonusActionEffect)
				state = new BonusActionState(((BonusActionEffect)immediateEffect).clone());
			else //eseguo l'effetto immediato se non si tratta di una bonus action
				immediateEffect.performEffect(move.getPlayer());
		
		space.getPlacementFloor().setCard(null); 
		space.placeFamiliar(move.getFamiliar(), move.getPlayer().getLudovicoAriosto());
		
		move.getFamiliar().setBusy(true);
	
	}
		
		//aggiungo l'effetto permanente della carta appena pescata dal giocatore alla classe BonusAndMalusPlayer - da implementare
	
	private boolean canAffordMalus() {
		return (move.getPlayer().getPersonalBoard().getResources().getResource("coin").getAmount()>=3);
	}
	
	private boolean canAffordCardResourcesCost() throws NotEnoughResourcesException {
		
		ArrayList<Resource> discountedCost=space.takeCard().getCost();
		
		if(move.getFloor()>2)
			highFloorDiscount(discountedCost); //posso spendere il guadagno di risorse del terzo/quarto piano per prendere la carta
		
		//if I have permanent effects about discounts...
		applyDiscounts(discountedCost);
		
		//considero le 3 monete da pagare
		if(!space.isEmpty()) {
			boolean find = false;
			for(Resource res: discountedCost)
				if(res.getType().equals("coin")){
					res.modifyAmount(3);
					find = true;
				}
			if(!find)
				discountedCost.add(new Resource("coin", 3));
		}
		
		for(Resource res: discountedCost) {
			if (res.getAmount()> move.getPlayer().getPersonalBoard().getSpecificResource(res.getType()).getAmount()) 
				throw new NotEnoughResourcesException();
		}
		return true;
		
	}
	
	private void highFloorDiscount(ArrayList<Resource> cost) {
		
		for (Resource res: cost) 
			for(Resource source: space.takeResource()) 
				if ((res.getType().equals(source.getType()))&& (res.getAmount()>source.getAmount())) 
					res.modifyAmount(-source.getAmount());
	}
	
	private boolean enoughSlotSpace() throws FullCardBoardException{
		if (move.getPlayer().getPersonalBoard().getCards(space.takeCard().getType()).size()<6)
			return true;
		throw new FullCardBoardException ();
	}

	private boolean enoughMilitaryPoints() throws TerritoryCardException {
		if(space.takeCard().getType().equals("territory")) {
			
			int size= move.getPlayer().getPersonalBoard().getCards("territory").size();
			boolean canAfford = false;
			
			switch (size) {
				case 2:
					canAfford = (move.getPlayer().getPersonalBoard().getSpecificResource("military").getAmount()>=3);
					break;
				case 3:
					canAfford = (move.getPlayer().getPersonalBoard().getSpecificResource("military").getAmount()>=7);
					break;
				case 4:
					canAfford = (move.getPlayer().getPersonalBoard().getSpecificResource("military").getAmount()>=12);
					break;
				case 5:
					canAfford = (move.getPlayer().getPersonalBoard().getSpecificResource("military").getAmount()>=18);	
					break;
				default:
					canAfford = true;
				
			}
			if (canAfford)
				return true;
			throw new TerritoryCardException();
		}
		
		else 
			return true;
		//da fare la parte per i punti necessari alle venture card
	}
	
	private void applyDiscounts( ArrayList<Resource> costs) {
		for (Effect eff: move.getPlayer().specialPermanentEffects) 
			if (eff.toString().equals("DiscountForCardType")) 
				for(Resource res: ((DiscountForCardTypeEffect)eff).getDiscount()) 
					for (Resource source: costs) 
						if (res.getType()==source.getType()) 
							source.modifyAmount(-res.getAmount());
	}


}
