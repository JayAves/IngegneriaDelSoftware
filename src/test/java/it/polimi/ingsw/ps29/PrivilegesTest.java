package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.action.actionstates.PerformedState;
import it.polimi.ingsw.ps29.model.action.actionstates.PrivilegesState;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import junit.framework.TestCase;

public class PrivilegesTest extends TestCase {
	
	Player player;
	ActionState state;
	Match model;
	
	public PrivilegesTest (String testName) {
		super (testName);
	}
	
	@BeforeClass
	public void setUp () {
		
		model = null;
		try {
			ArrayList<String> names = new ArrayList<String> ();
			names.add("aa");
			names.add("bb");
			model = new Match(names);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Resource res = new Resource ("privilege", 1);
		model.getCurrentPlayer().getPersonalBoard().getResources().updateResource(res);
		//System.out.println(model.getBoard().getCurrentPlayer().getPersonalBoard().getResources().removeResource(ResourceType.PRIVILEGE));
		state = new PrivilegesState (new PerformedState(), 1);
		state = state.afterAction(model);
	}
	
	@Test
	public void test() {
		
		assertEquals(new Resource("privilege",0).toString(), 
				model.getCurrentPlayer().getPersonalBoard().getSpecificResource("privilege").toString());
		assertTrue(state.getState().equals("performed"));
		
	}
	

}
