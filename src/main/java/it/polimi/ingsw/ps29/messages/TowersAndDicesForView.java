package it.polimi.ingsw.ps29.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

/**
 * Contains data to be shown in View about Towers and Dices
 * @author Pietro Melzi
 */
public class TowersAndDicesForView extends InteractionMessage {

	//auto-generated serialVersionUID
	private static final long serialVersionUID = 1158495027760084133L;
	private TowersDTO towers;
	private int[] dices = new int[3];
	ArrayList<PlayerColor> playerOrder = new ArrayList<PlayerColor>();
	
	
	/*0 black
	 *1 white
	 *2 orange*/
	
	
	public TowersAndDicesForView(String player, TowersDTO towers, int[] dices, ArrayList<PlayerColor> order) {
		super(player, false);
		this.towers = towers;
		this.dices = dices;
		playerOrder = order;	    
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
	
	public ArrayList<PlayerColor> getPlayerOrder () {
		ArrayList<PlayerColor> copy = new ArrayList<PlayerColor>();
		for(PlayerColor pColor: playerOrder)
			copy.add(pColor);
		return copy;
	}
	
}
