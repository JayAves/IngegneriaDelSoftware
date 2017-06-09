package it.polimi.ingsw.ps29.view.messages;

import java.io.Serializable;

public interface Message extends Serializable {
	
	public void visit (it.polimi.ingsw.ps29.controller.Controller.VisitorMessages visitor) ;
	
	public void receive(it.polimi.ingsw.ps29.view_client.Client.VisitorServerMessages visitor);

	
}
