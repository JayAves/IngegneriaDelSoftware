package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.messages.PrivilegeChoice;

public class PrivilegesState implements ActionState {
	private ActionState previousState;
	private int privileges;
	private final StateOfActionIdentifier state = StateOfActionIdentifier.PRIVILEGES;
	
	
	public PrivilegesState(ActionState previousState, int privileges) {
		this.privileges = privileges;
		this.previousState = previousState;
	}

	@Override
	public ActionState beforeAction() {
		return this;
	}

	@Override
	public ActionState afterAction(Match model) {
		model.getBoard().getCurrentPlayer().getPersonalBoard().getResources().removeResource(ResourceType.PRIVILEGE);
		return previousState;
	}

	@Override
	public String getState() {
		return state.toString();
	}

	@Override
	public InteractionMessage objectForView(String player) {
		return new PrivilegeChoice(player, privileges);
	}

}