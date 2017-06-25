package it.polimi.ingsw.ps29.messages;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.ExcommunicationCardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBonusTileDTO;
import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class FirstBoardInfo extends InteractionMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5849206424080102207L;
	private HashMap<String, PersonalBonusTileDTO> tile;
	private ArrayList<ExcommunicationCardDTO> exCards;
	private TowersAndDicesForView towers;
	private int timer;

	public FirstBoardInfo(String player, HashMap<String, PersonalBonusTileDTO> tile, ArrayList<ExcommunicationCardDTO> exCards, 
			TowersAndDicesForView towers) {
		super(player,false);
		this.tile = tile;
		this.exCards = exCards;
		this.towers = towers;
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
	
	public TowersAndDicesForView getTowers () {
		return towers;
	}
	public void setTimer(int timer) {
		this.timer=timer;
	}
	public int getTimer() {
		return timer;
	}
}
