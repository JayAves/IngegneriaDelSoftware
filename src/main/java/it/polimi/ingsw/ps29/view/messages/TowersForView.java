package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class TowersForView extends InteractionMessage {

	private TowersDTO towers;
	
	public TowersForView(String player, TowersDTO towers) {
		super(player);
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

	public TowersDTO getTowers() {
		return towers;
	}

}
