package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.model.action.ExchangeResources;
import it.polimi.ingsw.ps29.model.action.actionstates.AskAboutExchangeState;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.resources.Container;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import junit.framework.TestCase;

public class PerformExchangeTest extends TestCase {
	
	Match model;
	
	public PerformExchangeTest (String testName) {
		super (testName);
	}
	
	@BeforeClass
	public void setUp () throws FileNotFoundException {
		ArrayList<String> playerNames = new ArrayList<String> ();
		playerNames.add("aa");
		playerNames.add("bb");
		model = new Match(playerNames);
		
		ArrayList<Resource> r3 = new ArrayList<Resource>();
		r3.add(new Resource("servant", 1));
		r3.add(new Resource("stone", 2));
		ArrayList<Resource> r4 = new ArrayList<Resource>();
		r4.add(new Resource("servant", 2));
		ArrayList<Resource> r5 = new ArrayList<Resource>();
		r5.add(new Resource("wood", 2));
		ArrayList<Resource> r6 = new ArrayList<Resource>();
		r6.add(new Resource("privilege", 2));
		r6.add(new Resource("faith", 2));
		
		ExchangeResourceHandler erh2 = new ExchangeResourceHandler(r3, r4, false, false);
		ExchangeResourceHandler erh3 = new ExchangeResourceHandler(r5, r6, false, false);
		
		ArrayList <ExchangeResourceHandler> arr2 = new ArrayList <ExchangeResourceHandler> ();
		arr2.add(erh2);
		arr2.add(erh3);
		
		ExchangeResourcesEffect eff2 = new ExchangeResourcesEffect(arr2);
		ArrayList <ExchangeResourcesEffect> array = new ArrayList<ExchangeResourcesEffect>();
		array.add(eff2);
		
		Exchange msg = new Exchange("aa", eff2);
		//scelgo il secondo scambio
		msg.setChoice(0, 2);
		
		Container container = new Container();
		//player.setSupport(new ExchangeSupport(array, container));
		
		ExchangeResources eR = new ExchangeResources(model, new AskAboutExchangeState(array));
		eR.exchangeHandler(msg);
	}
	
	@Test
	public void test() {
		assertEquals(0, model.getBoard().getPlayerByName("aa").getPersonalBoard().getSpecificResource("wood").getAmount());
		
	}
	

}