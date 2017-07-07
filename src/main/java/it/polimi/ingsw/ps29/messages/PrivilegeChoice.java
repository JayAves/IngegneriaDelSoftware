package it.polimi.ingsw.ps29.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

/**
 * Contains data about a privilege choice.
 * @author Pietro Melzi
 * 
 */
public class PrivilegeChoice extends InteractionMessage {
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = -7857119179517355732L;
	private int prvilieges;
	private boolean different;
	private ArrayList <ResourceType> choices;

	public PrivilegeChoice(String player, int privileges, boolean different) {
		super(player, true);
		this.prvilieges = privileges;
		this.different = different;
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

	public int getPrivileges() {
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
	
	public boolean getDifferent () {
		return different;
	}

	
}
