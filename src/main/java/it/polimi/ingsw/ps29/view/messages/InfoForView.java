package it.polimi.ingsw.ps29.view.messages;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.ResourceDTO;
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
	public HashMap <String, ArrayList<ResourceDTO>> resSituation;
	
	public InfoForView(String player) {
		super(player);
		space = 0;
		floor = 0;
		familiar = 0;
		IDcard = 0;
		IDexcommunicationCard = 0;
		resSituation = new HashMap <String, ArrayList<ResourceDTO>> ();
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
