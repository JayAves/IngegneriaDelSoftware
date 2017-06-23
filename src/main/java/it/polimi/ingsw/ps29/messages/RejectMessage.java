package it.polimi.ingsw.ps29.messages;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class RejectMessage extends InteractionMessage {

	private RejectException exception;
	
	public RejectMessage(String player, RejectException exception) {
		super(player);
		this.exception = exception;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1338638932183505667L;

	@Override
	public void visit(VisitorMessages visitor) {
		// not used
		
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		visitor.receive(this);
		
	}

	public RejectException getException () {
		return exception;
	}
}
