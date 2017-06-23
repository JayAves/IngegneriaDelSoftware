package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;

public class VaticanChoice extends InteractionMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5276703313626469150L;
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
	public void receive(it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages visitor) {
		// TODO Auto-generated method stub
		visitor.receive(this);
	}



	

}
