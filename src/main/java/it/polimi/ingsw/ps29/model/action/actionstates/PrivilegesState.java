package it.polimi.ingsw.ps29.model.action.actionstates;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Coins;
import it.polimi.ingsw.ps29.model.game.resources.FaithPoints;
import it.polimi.ingsw.ps29.model.game.resources.MilitaryPoints;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.model.game.resources.Servants;
import it.polimi.ingsw.ps29.model.game.resources.Stones;
import it.polimi.ingsw.ps29.model.game.resources.Woods;

/**
 * When one ore more privileges are to be dealt with.
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.model.game.resources.Privilege
 * 
 *
 */
public class PrivilegesState implements ActionState {
	private ActionState previousState;
	private int privileges;
	private boolean different;
	private final StateOfActionIdentifier state = StateOfActionIdentifier.PRIVILEGES;
	
	
	public PrivilegesState(ActionState previousState, int privileges, boolean different) {
		this.privileges = privileges;
		this.different = different;
		this.previousState = previousState;
	}

	@Override
	public ActionState beforeAction() {
		return this;
	}

	@Override
	public ActionState afterAction(Match model) {
		model.getCurrentPlayer().getPersonalBoard().getResources().removeResource(ResourceType.PRIVILEGE);
		return previousState;
	}

	@Override
	public String getState() {
		return state.toString().toLowerCase();
	}

	@Override
	public InteractionMessage objectForView(String player) {
		return new PrivilegeChoice(player, privileges, different);
	}

	public ActionState getPreviousState() {
		return previousState;
	}

	public void setPreviousState(ActionState previousState) {
		this.previousState = previousState;
	}
	
	public void handlePrivileges (Player player, ArrayList <ResourceType> res) {
		for(ResourceType resType: res) 
			
			switch(resType.getType()) {
				
				case "wood":
					player.getPersonalBoard().getResources().updateResource(new Woods (1));
					player.getPersonalBoard().getResources().updateResource(new Stones(1));
					break;
				case "servant":
					player.getPersonalBoard().getResources().updateResource(new Servants (2));
					break;
				case "coin":
					player.getPersonalBoard().getResources().updateResource(new Coins (2));
					break;
				case "military":
					player.getPersonalBoard().getResources().updateResource(new MilitaryPoints(2));
					break;
				case "faith":
					player.getPersonalBoard().getResources().updateResource(new FaithPoints(1));
					break;
			}
		
	}

}
