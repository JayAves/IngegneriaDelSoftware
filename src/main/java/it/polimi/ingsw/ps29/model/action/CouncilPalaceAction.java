package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.Coins;
import it.polimi.ingsw.ps29.model.game.resources.Privilege;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;

public class CouncilPalaceAction implements Action{

	private Move move; 
	private CouncilPalaceArea space;
	
	
	public CouncilPalaceAction(Move move) {
		
		this.move = move;
		this.space=(CouncilPalaceArea) move.getSpace();
		
	}

	@Override
	public boolean isForbidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPlaceable() {
		// TODO Auto-generated method stub
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getCouncilPower());
	}

	@Override
	public void performAction() {
		
		((CouncilPalaceArea) move.getSpace()).getQueue().addMember(move.getFamiliar());
		ArrayList<Resource> councilBonus = new ArrayList<Resource> ();
		councilBonus.add(new Coins(1));
		councilBonus.add(new Privilege());
		GainResourcesEffect effect= new GainResourcesEffect(councilBonus);
		effect.performEffect(move.getPlayer());
		
	}
	
	

}
