package it.polimi.ingsw.ps29.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

/**
 * Contains data about a normal Action.
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class ActionChoice extends InteractionMessage {

	
	private static final long serialVersionUID = -2158602926454517213L;
	private int[] choices;
	//int [0] space
	//int [1] floor (0 if not needed)
	//int [2] servants' number
	//int [3] familiar
	ArrayList<ArrayList<Object>> leaderSituation;
	//0 :id
	//1 :toString
	//2 : where it comes from (0 hand, 1 board)
	//3 : activable
	//4 : user choice DISCARD PLAY ACTIVATE
	
	public ActionChoice (String player) {
		super (player,true);
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

		visitor.receive(this);
	}

	public ArrayList<ArrayList<Object>> getLeaderSituation() {
		return leaderSituation;
	}

	public void setLeaderSituation(ArrayList<ArrayList<Object>> leaderSituation) {
		this.leaderSituation = leaderSituation;
	}

	
	
}
