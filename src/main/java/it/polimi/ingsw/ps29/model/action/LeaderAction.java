package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;

public class LeaderAction extends Action{
	
	ActionChoice choice;

	public LeaderAction(Match model, Move move, ActionChoice choice) {
		super(model, move);
		this.choice = choice;
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isForbidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isPlaceable() throws RejectException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	void performAction() {
		
		for (ArrayList<Object> card :choice.getLeaderSituation()){
			
			if (card.get(4) == "ACTIVATE" || card.get(4) == "PLAY"){
				move.getPlayer().getPersonalBoard().getPlayedLeaderCards().add(move.getPlayer().getPersonalBoard().getLeaderById((int)card.get(0), move.getPlayer().getPersonalBoard().getLeaderCards()));
			    move.getPlayer().getPersonalBoard().removeLeaderById((int)card.get(0), move.getPlayer().getPersonalBoard().getLeaderCards());
			    if (card.get(4) == "ACTIVATE"){
			    	move.getPlayer().getPersonalBoard().getActivatedLeaderCards().add(move.getPlayer().getPersonalBoard().getLeaderById((int)card.get(0), move.getPlayer().getPersonalBoard().getPlayedLeaderCards()));
			    	move.getPlayer().getPersonalBoard().getLeaderById((int)card.get(0), move.getPlayer().getPersonalBoard().getActivatedLeaderCards()).getEffect().performEffect(move.getPlayer());;
			    }
			}
		}
				
	}

}