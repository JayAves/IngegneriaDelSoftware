package it.polimi.ingsw.ps29;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.controller.ChoiceToMove;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.messages.exception.SpaceOccupiedException;
import it.polimi.ingsw.ps29.model.action.HarvestAction;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.space.ActivityArea;
import junit.framework.TestCase;

public class HarvestActionTest extends TestCase{
	
	Match model;
	HarvestAction scndAction;
	ActivityArea space;
	
	@BeforeClass
	public void setUp() throws FileNotFoundException, RejectException{
		
		String player1 = "primo";
		String player2 = "secondo";
		String player3 = "terzo";
 		ArrayList<String> names= new ArrayList<String>();
		names.add(player1);
		names.add(player2);
		
		model = new Match(names);
		
		space = (ActivityArea) model.getBoard().getSpace("Harvest");
		System.out.println(space);
		
		model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.BLACK).setPower(4);
		model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.BLACK).setPower(2);
		model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.ORANGE).setPower(2);
		
		ActionChoice aChoiceFirst = new ActionChoice("primo");
		aChoiceFirst.setChoice(0, 1);
		aChoiceFirst.setChoice(3, 1);
		
		Move move = ChoiceToMove.createMove(aChoiceFirst, model.getBoard());
		
			
		HarvestAction action = new HarvestAction(model, move);
		//System.out.println(model.getBoard().getSpace("harvest"));
		action.actionHandler();
		//System.out.println(model.getBoard().getSpace("harvest"));
		System.out.println(model.getBoard().getSpace("Harvest"));
		
		ActionChoice aChoiceSecond = new ActionChoice("secondo");
		aChoiceSecond.setChoice(0, 1);
		aChoiceSecond.setChoice(3, 1);
		
		Move moveSecond = ChoiceToMove.createMove(aChoiceSecond, model.getBoard());
		
		scndAction = new HarvestAction(model, moveSecond);
		//scndAction.actionHandler();
		
	}
	

	@Test(expected = SpaceOccupiedException.class)
	public void test(){
		//fail("Not yet implemented");
		assertEquals(true, model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.BLACK).getBusy());
		assertEquals(true, space.getClosed());
		try {
			scndAction.isPlaceable();
			fail ("expected SpaceOccupiedException");
		} catch (RejectException e) {
			// TODO Auto-generated catch block
		}
		
		
		assertEquals(false, model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.BLACK).getBusy());
			
	}

}
