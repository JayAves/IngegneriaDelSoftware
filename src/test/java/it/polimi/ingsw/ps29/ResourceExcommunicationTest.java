package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.MalusResourcesExcommunication;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import junit.framework.TestCase;

public class ResourceExcommunicationTest extends TestCase{
	
	ArrayList<Resource> first;
	ArrayList<Resource> second;
	Effect excommunication1;
	Effect excommunication2;
	ExcommunicationCard testCard;
	ExcommunicationCard testCard2;
	Player player;
	Match model;
	int startingCoin1;
	int startingCoin2;
	int startingServant1;
	
	public ResourceExcommunicationTest (String testName){
		super(testName);
	}
	
	@BeforeClass
	public void setUp () throws FileNotFoundException {
	first = new ArrayList<Resource>();
	second = new ArrayList<Resource>();
	
	first.add(new Resource("coin", 1));
	first.add(new Resource("military", 1));
	second.add(new Resource("coin", 2));
	second.add(new Resource("servant", 1));
	
	
	excommunication1 = new MalusResourcesExcommunication(first);
	excommunication2 = new MalusResourcesExcommunication(second);
	
	testCard = new ExcommunicationCard(3, excommunication1, Period.FIRST);
	testCard2 = new ExcommunicationCard(4, excommunication2, Period.FIRST);
	
	String player1 = "primo";
	String player2 = "secondo";
	ArrayList<String> names= new ArrayList<String>();
	names.add(player1);
	names.add(player2);
	
	model = new Match(names);
	
	startingCoin1 = model.getBoard().getPlayerByName("primo").getPersonalBoard().getSpecificResource("coin").getAmount();
	startingCoin2 = model.getBoard().getPlayerByName("secondo").getPersonalBoard().getSpecificResource("coin").getAmount();
	startingServant1 = model.getBoard().getPlayerByName("primo").getPersonalBoard().getSpecificResource("servant").getAmount();
	
	testCard.getEffect().performEffect(model.getBoard().getPlayerByName("primo"));
	testCard.getEffect().performEffect(model.getBoard().getPlayerByName("secondo"));
	testCard2.getEffect().performEffect(model.getBoard().getPlayerByName("primo"));
	
	model.getBoard().getPlayerByName("primo").getPersonalBoard().getResources().updateResource(new Resource("coin", 5));
	model.getBoard().getPlayerByName("primo").getPersonalBoard().getResources().updateResource(new Resource("military", 5));
	model.getBoard().getPlayerByName("primo").getPersonalBoard().getResources().updateResource(new Resource("servant", 5));
	model.getBoard().getPlayerByName("secondo").getPersonalBoard().getResources().updateResource(new Resource("coin", 5));
	
	
	}
	
	
	
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		
		assertEquals(startingCoin1 + 2,model.getBoard().getPlayerByName("primo").getPersonalBoard().getSpecificResource("coin").getAmount());
		assertEquals(4,model.getBoard().getPlayerByName("primo").getPersonalBoard().getSpecificResource("military").getAmount());
		assertEquals(startingServant1 + 4,model.getBoard().getPlayerByName("primo").getPersonalBoard().getSpecificResource("servant").getAmount());
		assertEquals(startingCoin2 + 4,model.getBoard().getPlayerByName("secondo").getPersonalBoard().getSpecificResource("coin").getAmount());
	}

}
