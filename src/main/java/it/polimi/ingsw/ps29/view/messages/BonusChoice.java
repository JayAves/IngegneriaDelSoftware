package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.view_client.Client.VisitorServerMessages;

public class BonusChoice implements Message {

	@Override
	public void visit(VisitorMessages visitor) {
		// TODO Auto-generated method stub
		//
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		// TODO Auto-generated method stub
		visitor.receive(this);
	}

}
