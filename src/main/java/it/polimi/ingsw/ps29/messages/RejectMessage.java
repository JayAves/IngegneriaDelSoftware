package it.polimi.ingsw.ps29.messages;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

/**
 * Contains a message about an Action being rejected.
 * @author Pietro Melzi
 *
 */

public class RejectMessage extends InteractionMessage {

	private RejectException exception;
	
	public RejectMessage(String player, RejectException exception) {
		super(player,false);
		this.exception = exception;
	}

	//auto-generated serialVersionUID
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
