package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import junit.framework.TestCase;

public class MilitaryTrackTest extends TestCase{
	
	Match model;
	int victoryAmountPrimo;
	int victoryAmountSecondo;
	int victoryAmountTerzo;
	
	public MilitaryTrackTest (String testName){
		super(testName);
	}
	
	@BeforeClass
	public void setUp() throws FileNotFoundException{
		
		String player1 = "primo";
		String player2 = "secondo";
		String player3 = "terzo";
 		ArrayList<String> names= new ArrayList<String>();
		names.add(player1);
		names.add(player2);
		names.add(player3);
		
		model = new Match(names);
		
		model.getBoard().getPlayerByName("primo").getPersonalBoard().getResources().updateResource(new Resource("military" , 8));
		model.getBoard().getPlayerByName("secondo").getPersonalBoard().getResources().updateResource(new Resource("military" , 5));
		model.getBoard().getPlayerByName("terzo").getPersonalBoard().getResources().updateResource(new Resource("military" , 5));
		
		model.getBoard().assignPointsForMilitaryTrack();
		
		victoryAmountPrimo = model.getBoard().getPlayerByName(player1).getPersonalBoard().getSpecificResource("victory").getAmount();
		victoryAmountSecondo = model.getBoard().getPlayerByName(player2).getPersonalBoard().getSpecificResource("victory").getAmount();
		victoryAmountTerzo = model.getBoard().getPlayerByName(player3).getPersonalBoard().getSpecificResource("victory").getAmount();
	}
	

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(5,victoryAmountPrimo);
		assertEquals(2, victoryAmountSecondo);
		assertEquals(true, victoryAmountSecondo == victoryAmountTerzo);
	}

}
