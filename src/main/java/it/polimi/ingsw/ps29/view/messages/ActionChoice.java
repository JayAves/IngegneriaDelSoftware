package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.view_client.Client.VisitorServerMessages;

public class ActionChoice implements Message {

	private String name;
	private int[] choices;
	//int [0] spazio
	//int [1] piano (ev. vale 0)
	//int [2] num servitori
	//int [3] familiare
	
	public ActionChoice (String name, int[] choices) {
		this.setName(name);
		this.setChoices(choices);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChoices(int i) {
		return choices[i];
	}

	public void setChoices(int[] choices) {
		this.choices = choices;
	}

	@Override
	public void visit(it.polimi.ingsw.ps29.controller.Controller.VisitorMessages visitor) {
		visitor.visit(this);
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		// TODO Auto-generated method stub
		visitor.receive(this);
	}

	
	
}
