package it.polimi.ingsw.ps29;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.MalusResourcesExcommunication;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import junit.framework.TestCase;

public class MalusResourcesTest extends TestCase{
	
	Match model;
	Effect effect;
	
	public MalusResourcesTest (String testName){
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
		
		ArrayList<Resource> resource = new ArrayList<Resource>();
		resource.add(new Resource("victory", 2));
		
		effect = new MalusResourcesExcommunication(resource);
		effect.performEffect(model.getBoard().getPlayerByName(player1));
		
		Resource toUpdate = new Resource("victory", 5);
		model.getBoard().getPlayerByName(player1).getPersonalBoard().getResources().updateResource(toUpdate);
		
	}
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(3,model.getBoard().getPlayerByName("primo").getPersonalBoard().getSpecificResource("victory").getAmount());
	}

}

