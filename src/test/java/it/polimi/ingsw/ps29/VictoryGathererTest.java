package it.polimi.ingsw.ps29;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.cards.BuildingCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.AddGatherer;
import it.polimi.ingsw.ps29.model.cards.effects.RemoveGatherer;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import junit.framework.TestCase;

public class VictoryGathererTest extends TestCase{
	
	Match model;
	int initialAmount;
	
	public VictoryGathererTest(String testName){
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
		 
		 initialAmount = model.getBoard().getPlayerByName(player1).getPersonalBoard().getSpecificResource("coin").getAmount();
		 
		 ArrayList<Resource> buildingCost = new ArrayList<Resource>();
		 buildingCost.add(new Resource("wood", 3));
		 buildingCost.add(new Resource("stone", 4));
		 
		 model.getBoard().getPlayerByName(player1).getPersonalBoard().getSpecificResource("military").modifyAmount(8);
		 model.getBoard().getPlayerByName(player2).getPersonalBoard().getSpecificResource("servant").modifyAmount(8);
		 model.getBoard().getPlayerByName(player3).getPersonalBoard().getSpecificResource("military").modifyAmount(8);
		 model.getBoard().getPlayerByName(player1).getPersonalBoard().getSpecificResource("wood").modifyAmount(8);
		 model.getBoard().getPlayerByName(player2).getPersonalBoard().getSpecificResource("stone").modifyAmount(8);
		 model.getBoard().getPlayerByName(player2).getPersonalBoard().getSpecificResource("military").modifyAmount(8);
		 model.getBoard().getPlayerByName(player1).getPersonalBoard().getSpecificResource("servant").modifyAmount(8);
		 model.getBoard().getPlayerByName(player3).getPersonalBoard().getSpecificResource("wood").modifyAmount(8);
		 model.getBoard().getPlayerByName(player1).getPersonalBoard().addCard(new BuildingCard("stub", null, "building", 0, null, null, buildingCost, 0));
		 
		 Effect excommunication = new AddGatherer("cost", 1);
		 excommunication.performEffect(model.getBoard().getPlayerByName(player1));
		 
		 Effect excommunication2 = new RemoveGatherer("territory");
		 excommunication2.performEffect(model.getBoard().getPlayerByName(player1));
		 
		 model.getBoard().getPlayerByName(player1).getFinalPoints();
		 
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(-2, model.getBoard().getPlayerByName("primo").getPersonalBoard().getSpecificResource("victory").getAmount());
	}

}
