package it.polimi.ingsw.ps29;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.model.action.LeaderAction;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import junit.framework.TestCase;

public class LeaderCardsTest extends TestCase{
	
	Match model;
	int playedSize;
	
	public LeaderCardsTest(String testName){
		
		super(testName);
	}
	
	@Before
	public void setUp () throws FileNotFoundException {
		
		ArrayList<ArrayList<Object>> leaderChoice = new ArrayList<ArrayList<Object>>();
		String player1 = "primo";
		String player2 = "secondo";
		ArrayList<String> names= new ArrayList<String>();
		names.add(player1);
		names.add(player2);
		
		model = new Match(names);
		
		leaderChoice = model.getBoard().getPlayerByName("primo").getPersonalBoard().buildLeaderChoice();
		
		for (int i = 0; i < leaderChoice.size(); i++)
			leaderChoice.get(i).add("PLAY");
		
		Move move = new Move(model.getBoard().getPlayerByName("primo"), "string", 0, 0, null);
		ActionChoice aChoice = new ActionChoice(model.getBoard().getPlayerByName("primo").getName());
		aChoice.setLeaderSituation(leaderChoice);
		
		LeaderAction action = new LeaderAction(model, move, aChoice);	
		action.actionHandler();
		
		playedSize = model.getBoard().getPlayerByName("primo").getPersonalBoard().getPlayedLeaderCards().size();
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(4, playedSize);
	}

}
