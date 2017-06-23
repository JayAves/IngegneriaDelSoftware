package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class TowersAndDicesForView extends InteractionMessage {

	private TowersDTO towers;
	private int[] dices = new int[3];
	
	/*0 black
	 *1 white
	 *2 orange*/
	
	public TowersAndDicesForView(String player, TowersDTO towers, int[] dices) {
		super(player);
		this.towers = towers;
		this.dices = dices;
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
	
	public int[] getDices () {
		return dices;
	}

}