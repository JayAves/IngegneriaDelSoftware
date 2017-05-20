package it.polimi.ingsw.ps29.model.action;

public class ManageAction {
	private Action action;
	private BonusAndMalusPlayer permanentBonusAndMalus;
	
	public ManageAction (Action action) {
		this.action = action;
	}
	
	public void doAction() {
		action.performAction();
		
	}

}
