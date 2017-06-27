package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.PrivilegesState;
import it.polimi.ingsw.ps29.model.action.actionstates.ToEstablishState;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.Privilege;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class LeaderAction extends Action{
	
	ActionChoice choice;
	int privilegeCounter = 0;

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
	public ActionState actionHandler(){
		performAction();
		
		state = new ToEstablishState();
		
		if (privilegeCounter > 0)
			state = new PrivilegesState(state, privilegeCounter);
		
		return state;
	}
	

	@Override
	void performAction() {
				
		for (ArrayList<Object> card :choice.getLeaderSituation()){
			
			if (card.size() > 4 ){
				if (card.get(4).equals("DISCARD")){
					System.out.println("\n sono in discard");
					ArrayList<Resource> discardBonus = new ArrayList<Resource>();
					discardBonus.add(new Privilege(1));
					privilegeCounter ++;
					GainResourcesEffect effect = new GainResourcesEffect(discardBonus);
					effect.performEffect(move.getPlayer());
					move.getPlayer().getPersonalBoard().removeLeaderById((int)card.get(0));
				}
					
				if ( (card.get(4).equals("ACTIVATE") || card.get(4).equals("PLAY"))){
					move.getPlayer().getPersonalBoard().getPlayedLeaderCards().add(move.getPlayer().getPersonalBoard().getLeaderById((int)card.get(0), move.getPlayer().getPersonalBoard().getLeaderCards()));
					move.getPlayer().getPersonalBoard().removeLeaderById((int)card.get(0));
					if (card.get(4).equals("ACTIVATE")){
						move.getPlayer().getPersonalBoard().getActivatedLeaderCards().add(move.getPlayer().getPersonalBoard().getLeaderById((int)card.get(0), move.getPlayer().getPersonalBoard().getPlayedLeaderCards()));
						move.getPlayer().getPersonalBoard().getLeaderById((int)card.get(0), move.getPlayer().getPersonalBoard().getActivatedLeaderCards()).getEffect().performEffect(move.getPlayer());;
					}
				}
			}
			}		
	}

}
