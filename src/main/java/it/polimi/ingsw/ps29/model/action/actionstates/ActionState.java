package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;

public interface ActionState {
	
	public ActionState beforeAction ();
	
	public ActionState afterAction(Match model);
	
	public String getState ();

	InteractionMessage objectForView(String player);
	
}
