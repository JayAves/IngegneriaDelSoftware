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
import it.polimi.ingsw.ps29.model.action.TowerAction;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundSetupState;
import it.polimi.ingsw.ps29.model.space.ActivityArea;
import it.polimi.ingsw.ps29.model.space.TowerArea;
import junit.framework.TestCase;

public class TowerActionTest extends TestCase{
	
	Match model;
	TowerAction frstAction;
	TowerAction scndAction;
	TowerAction thrdAction;
	TowerAction frthAction;
	TowerAction ffthAction;
	TowerAction sxthAction;
	TowerArea space;
	boolean value;
	
	public TowerActionTest (String testName){
		super(testName);
	}
	
	@BeforeClass
	public void setUp() throws FileNotFoundException, RejectException{
		
		String player1 = "primo";
		String player2 = "secondo";
		String player3 = "terzo";
 		ArrayList<String> names= new ArrayList<String>();
		names.add(player1);
		names.add(player2);
		names.add(player3);
		
		model = new Match(names);
		
		RoundSetupState setUp = new RoundSetupState();
		setUp.doAction(1, model);
		
		model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.BLACK).setPower(4);
		model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.BLACK).setPower(4);
		model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.ORANGE).setPower(2);
		model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.WHITE).setPower(5);
		model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.ORANGE).setPower(3);
		model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.ORANGE).setPower(1);
		model.getBoard().getPlayerByName("terzo").getFamiliarByColor(DiceColor.BLACK).setPower(2);
		model.getBoard().getPlayerByName("terzo").getFamiliarByColor(DiceColor.ORANGE).setPower(3);
		model.getBoard().getPlayerByName("terzo").getFamiliarByColor(DiceColor.WHITE).setPower(4);
		
		ActionChoice aChoiceFirst = new ActionChoice("primo");
		aChoiceFirst.setChoice(0, 3);
		aChoiceFirst.setChoice(3, 1);
		aChoiceFirst.setChoice(1, 1);
		
		space = (TowerArea) model.getBoard().getSpace("TerritoryTower");
		
		Move move = ChoiceToMove.createMove(aChoiceFirst, model.getBoard());
		frstAction = new TowerAction(model, move);
		frstAction.actionHandler();
		
		ActionChoice aChoiceSecond = new ActionChoice("primo");
		aChoiceSecond.setChoice(0, 3);
		aChoiceSecond.setChoice(3, 2);
		aChoiceSecond.setChoice(1, 3);
		
		
		Move moveSecond = ChoiceToMove.createMove(aChoiceSecond, model.getBoard());
		scndAction = new TowerAction(model, moveSecond);
		
		ActionChoice aChoiceThird = new ActionChoice("primo");
		aChoiceThird.setChoice(0, 5);
		aChoiceThird.setChoice(3, 3);
		aChoiceThird.setChoice(1, 1);
		
		
		Move moveThird = ChoiceToMove.createMove(aChoiceThird, model.getBoard());
		thrdAction = new TowerAction(model, moveThird);
		
		ActionChoice aChoiceFourth = new ActionChoice("secondo");
		aChoiceFourth.setChoice(0, 5);
		aChoiceFourth.setChoice(3, 1);
		aChoiceFourth.setChoice(1, 2);
		
		
		Move moveFourth = ChoiceToMove.createMove(aChoiceFourth, model.getBoard());
		frthAction = new TowerAction(model, moveFourth);
		
		
	}
	

	@Test(expected = SpaceOccupiedException.class)
	public void test() {
		//fail("Not yet implemented");
		assertEquals(true, model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.BLACK).getBusy());
		try {
			scndAction.isPlaceable();
			fail("expected SpaceOccupiedException");
		} catch (RejectException e) {
			// TODO Auto-generated catch block
		}
		
		
	}

}
