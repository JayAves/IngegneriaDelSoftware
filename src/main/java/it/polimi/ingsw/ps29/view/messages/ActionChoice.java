package it.polimi.ingsw.ps29.view.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class ActionChoice extends InteractionMessage {

	
	private static final long serialVersionUID = -2158602926454517213L;
	private int[] choices;
	//int [0] spazio
	//int [1] piano (ev. vale 0)
	//int [2] num servitori
	//int [3] familiare
	ArrayList<ArrayList<Object>> leaderSituation;
	
	public ActionChoice (String player) {
		super (player);
		choices = new int [4];
		choices[1] = 0;
		leaderSituation = new ArrayList<ArrayList<Object>>();
	}

	public int getChoice(int i) {
		return choices[i];
	}
	
	public void setChoices (int[] choices) {
		this.choices = choices;
	}
	
	public void setChoice (int index, int value) {
		choices[index] = value;
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

	public ArrayList<ArrayList<Object>> getLeaderSituation() {
		return leaderSituation;
	}

	public void setLeaderSituation(ArrayList<ArrayList<Object>> leaderSituation) {
		this.leaderSituation = leaderSituation;
	}

	
	
}
