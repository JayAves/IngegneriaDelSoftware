package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.PersonalBonusTile;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import junit.framework.TestCase;

public class FinalScoringTest extends TestCase{
	
	PersonalBonusTile personalTile;
	Match model;
	
public FinalScoringTest (String testName) {
		
	super(testName);
	}

	
	@Before
	public void setUp () throws FileNotFoundException {
		//personalTile = new PersonalBonusTile(1, new ArrayList<Resource> (), new ArrayList <Resource> ());
		String player1 = "primo";
		String player2 = "secondo";
		String player3 = "terzo";
		ArrayList<String> names= new ArrayList<String>();
		names.add(player1);
		names.add(player2);
		names.add(player3);
		
		model = new Match(names);
		
		model.getBoard().getPlayerByName("primo").getPersonalBoard().getResources().updateResource(new Resource("victory", 15));
		model.getBoard().getPlayerByName("primo").getPersonalBoard().getResources().updateResource(new Resource("military", 15));
		model.getBoard().getPlayerByName("primo").getFinalScoring().setResourcePenalty();
		model.getBoard().getPlayerByName("primo").passPersonalBoard();
		model.getBoard().getPlayerByName("primo").getFinalPoints();
		
		model.getBoard().getPlayerByName("secondo").getPersonalBoard().addCard(model.getBoard().getDecks().get(2).getCard(0));
		model.getBoard().getPlayerByName("primo").getPersonalBoard().getResources().updateResource(new Resource("military", 10));
		model.getBoard().getPlayerByName("secondo").passPersonalBoard();
		model.getBoard().getPlayerByName("secondo").getFinalPoints();
		
		model.getBoard().getPlayerByName("terzo").getPersonalBoard().addCard(model.getBoard().getDecks().get(1).getCard(0));
		model.getBoard().getPlayerByName("terzo").getPersonalBoard().addCard(model.getBoard().getDecks().get(1).getCard(0));
		model.getBoard().getPlayerByName("primo").getPersonalBoard().getResources().updateResource(new Resource("military", 10));
		model.getBoard().getPlayerByName("primo").getFinalScoring().setTerritoryCardsPenalty();
		model.getBoard().getPlayerByName("terzo").passPersonalBoard();
		model.getBoard().getPlayerByName("terzo").getFinalPoints();
		
		model.getBoard().assignPointsForMilitaryTrack();
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(10,model.getBoard().getPlayerByName("primo").getPersonalBoard().getSpecificResource("victory").getAmount());
		assertEquals(5,model.getBoard().getPlayerByName("secondo").getPersonalBoard().getSpecificResource("victory").getAmount());
		assertEquals(4,model.getBoard().getPlayerByName("terzo").getPersonalBoard().getSpecificResource("victory").getAmount());
	}

}
