package it.polimi.ingsw.ps29.view.messages;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.ExcommunicationCardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBonusTileDTO;
import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class FirstBoardInfo extends InteractionMessage {
	
	private HashMap<String, PersonalBonusTileDTO> tile;
	private ArrayList<ExcommunicationCardDTO> exCards;

	public FirstBoardInfo(String player, HashMap<String, PersonalBonusTileDTO> tile, ArrayList<ExcommunicationCardDTO> exCards) {
		super(player);
		this.tile = tile;
		this.exCards = exCards;
	}

	@Override
	public void visit(VisitorMessages visitor) {
		// not used
		
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		visitor.receive(this);
		
	}
	
	public HashMap <String, PersonalBonusTileDTO> getTiles () {
		return tile;
	}
	
	public ArrayList<ExcommunicationCardDTO> getExCards () {
		return exCards;
	}

}
