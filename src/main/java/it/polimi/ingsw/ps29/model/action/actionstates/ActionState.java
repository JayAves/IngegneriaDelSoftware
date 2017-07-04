package it.polimi.ingsw.ps29.model.action.actionstates;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.model.game.Match;

/**
 * Describes at what point current player's turn is. BeforeAction method is for preparation, afterAction is for notification.
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public interface ActionState {
	
	public ActionState beforeAction ();
	
	public ActionState afterAction(Match model);
	
	public String getState ();

	InteractionMessage objectForView(String player);
	
}
