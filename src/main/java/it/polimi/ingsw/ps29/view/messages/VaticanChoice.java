package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.view.View.VisitorServerMessages;

public class VaticanChoice extends InteractionMessage {
	private boolean sustain;
	
	

	public VaticanChoice(String player) {
		super(player);
	}
	
	

	@Override
	public void visit(VisitorMessages visitor) {
		visitor.visit(this);
	}

	


	public boolean isSustain() {
		return sustain;
	}



	public void setSustain(boolean sustain) {
		this.sustain = sustain;
	}



	@Override
	public void receive(VisitorServerMessages visitor) {
		visitor.receive(this);
		
	}

}
