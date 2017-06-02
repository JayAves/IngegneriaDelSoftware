package it.polimi.ingsw.ps29.model.action.state;

import it.polimi.ingsw.ps29.model.game.Match;

public interface ActionState {
	public ActionState beforeAction ();
	public ActionState afterAction(Match model);
	public String getState ();
}
