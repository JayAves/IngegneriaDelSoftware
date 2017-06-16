package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class InfoForView extends InteractionMessage {

	public Color playerColor;
	public int space;
	public int floor;
	public int familiar;
	public int IDcard;
	public int IDexcommunicationCard;
	public String resources;
	
	public InfoForView(String player) {
		super(player);
		space = 0;
		floor = 0;
		familiar = 0;
		IDcard = 0;
		IDexcommunicationCard = 0;
		resources = null;
	}

	@Override
	public void visit(VisitorMessages visitor) {
		//not used
		
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		visitor.receive(this);
		
	}

}
