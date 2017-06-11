package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.view.View.VisitorServerMessages;

public class BonusChoice extends InteractionMessage {
	
	private BonusActionEffect bonus;
	private String player;
	private int floor;
	private int servants;
	
	public BonusChoice(BonusActionEffect bonus, String player) {
		super(player);
		this.bonus = bonus;
		floor = 0;
	}
	
	
	public BonusActionEffect getBonus() {
		return bonus;
	}

	
	
	public void setFloor(int floor) {
		this.floor = floor;
	}


	public void setServants(int servants) {
		this.servants = servants;
	}


	public int getFloor() {
		return floor;
	}


	public int getServants() {
		return servants;
	}


	@Override
	public void visit(VisitorMessages visitor) {
		visitor.visit(this);
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		visitor.receive(this);
		
	}

}
