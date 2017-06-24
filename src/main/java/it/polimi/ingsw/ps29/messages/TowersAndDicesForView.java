package it.polimi.ingsw.ps29.messages;

import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class TowersAndDicesForView extends InteractionMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1158495027760084133L;
	private TowersDTO towers;
	private int[] dices = new int[3];
	
	/*0 black
	 *1 white
	 *2 orange*/
	private int timer;
	
	public TowersAndDicesForView(String player, TowersDTO towers, int[] dices) {
		super(player, false);
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
	public void setTimer(int timer) {
		this.timer=timer;
	}
	public int getTimer() {
		return timer;
	}
}
