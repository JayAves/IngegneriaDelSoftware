package it.polimi.ingsw.ps29.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

/**
 * Sends final scores to all players at the end of the game
 * @author Pietro Grotti
 *
 */
public class FinalScores extends InteractionMessage {

	//auto-generated serial version UID
	private static final long serialVersionUID = -6698961857718437974L;
	private ArrayList<String> playerNames;
	private int[] scores;
	
	public FinalScores(String player, int[] scores, ArrayList<String> playerNames) {
		super(player, false);
		this.scores= scores;
		this.playerNames=playerNames;
	}

	

	@Override
	public void visit(VisitorMessages visitor) {
		// TODO Auto-generated method stub
		//not used
		
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		// TODO Auto-generated method stub
		visitor.receive(this);
	}
	
	public ArrayList<String> getPlayers(){
		return playerNames;
	}
	
	public int[] getScores() {
		return scores;
	}
}
