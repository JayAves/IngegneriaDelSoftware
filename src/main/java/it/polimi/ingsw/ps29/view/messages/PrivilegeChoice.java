package it.polimi.ingsw.ps29.view.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

public class PrivilegeChoice extends InteractionMessage {
	private int prvilieges;
	private ArrayList <ResourceType> choices;

	public PrivilegeChoice(String player, int privileges) {
		super(player);
		this.prvilieges = privileges;
		choices = new ArrayList<ResourceType>();
	}
	
	public boolean isIn (ResourceType type) {
		for (ResourceType resType: choices)
			if(resType == type)
				return true;
		return false;
	}

	public void setChoice(ResourceType choice) {
		choices.add(choice);
	}



	public int getPrvilieges() {
		return prvilieges;
	}



	public ArrayList<ResourceType> getChoices() {
		return choices;
	}



	@Override
	public void visit(VisitorMessages visitor) {
		visitor.visit(this);
	}

	
	

	@Override
	public void receive(it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages visitor) {
		// TODO Auto-generated method stub
		visitor.receive(this);
	}

}
