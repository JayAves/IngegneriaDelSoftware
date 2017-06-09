package it.polimi.ingsw.ps29.view.messages;

public interface Message {
	
	public void visit (it.polimi.ingsw.ps29.controller.Controller.VisitorMessages visitor) ;
	
	public void receive(it.polimi.ingsw.ps29.view_client.Client.VisitorServerMessages visitor);

	
}
