package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.controller.ChoiceToMove;
import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActivityEffect;
import it.polimi.ingsw.ps29.model.cards.effects.BonusPlacementEffect;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import junit.framework.TestCase;

public class BonusActionTest extends TestCase {
	Move move, move2;
	
	public BonusActionTest (String testName) {
		super (testName);
	}
	
	@BeforeClass
	public void setUp () {
		ArrayList<String> name = new ArrayList<String>();
		name.add("aa");
		Match model = null;
		try {
			model = new Match(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameBoard board = model.getBoard();
		Controller c = new Controller(model);
		BonusChoice msg = new BonusChoice(new BonusActivityEffect(2, "harvest"), "aa");
		msg.setServants(0);
		ActionChoice aChoice = c.handleBonusAction(msg);
		move = ChoiceToMove.createMove(aChoice, board);
		
		BonusChoice msg2 = new BonusChoice(new BonusPlacementEffect(2, "all", null), "aa");
		msg2.setSpace(3);
		msg2.setFloor(1);
		msg2.setServants(0);
		ActionChoice aChoice2 = c.handleBonusAction(msg2);
		move2 = ChoiceToMove.createMove(aChoice2, board);
		
	}
	
	@Test
	public void testoo() {
		assertEquals("Harvest", move.getSpace());
		assertEquals("characterTower", move2.getSpace());
		assertEquals(1, move2.getFloor());
	}
	

}
