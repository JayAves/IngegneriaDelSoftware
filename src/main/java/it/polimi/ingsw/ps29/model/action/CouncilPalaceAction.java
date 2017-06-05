package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.Coins;
import it.polimi.ingsw.ps29.model.game.resources.Privilege;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;

public class CouncilPalaceAction extends Action{

	private CouncilPalaceArea space;
	

	public CouncilPalaceAction(Match model, Move move) {
		super(model, move);
		this.space = (CouncilPalaceArea) model.getBoard().getSpace(move.getSpace());
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
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getCouncilPower()+move.getServants());
	}

	@Override
	public void performAction() {
		
		space.getQueue().addMember(move.getFamiliar());
		ArrayList<Resource> councilBonus = new ArrayList<Resource> ();
		councilBonus.add(new Coins(1));
		councilBonus.add(new Privilege(1));
		//l'array andrebbe creato da un'altra parte, con le risorse caricate da file
		GainResourcesEffect effect= new GainResourcesEffect(councilBonus);
		effect.performEffect(move.getPlayer());
		
		
	}
	
	

}
