package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.view_client.Client;
import it.polimi.ingsw.ps29.view_client.Client.VisitorServerMessages;

public class Exchange extends InteractionMessage {
	
	private ExchangeResourcesEffect exchange;
	private int [] choice;
	
	public Exchange(String player, ExchangeResourcesEffect exchange) {
		super (player);
		this.exchange = exchange;
		choice = new int [3];
		choice[1] = 0; //resOUT
		choice [2] = 0; //resIN
	}
	
	public void setChoice (int index, int ch) {
		choice[index] = ch; 
	}
	
	public int getChoice (int index) {
		return choice[index];
	}

	public ExchangeResourcesEffect getExchange() {
		return exchange;
	}

	@Override
	public void visit(it.polimi.ingsw.ps29.controller.Controller.VisitorMessages visitor) {
		visitor.visit(this);
		
	}

	@Override
	public void receive(Client.VisitorServerMessages visitor) {
		// TODO Auto-generated method stub
		visitor.receive(this);
	}

	

	
	

	
	
}
