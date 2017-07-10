package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.controller.ChoiceToMove;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.roundstates.EndOfTheRoundState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundState;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;
import junit.framework.TestCase;

public class CouncilPalaceTest extends TestCase{
	
	public CouncilPalaceTest(String testName) {
		super(testName);
	}
	
	Match model;
	CouncilPalaceAction frstAction;
	CouncilPalaceAction scndAction;
	CouncilPalaceAction thrdAction;
	CouncilPalaceArea space;
	ArrayList<PlayerColor> councilPlayers;
	ArrayList<PlayerColor> beforePlayers;
	
	
	@BeforeClass
	public void setUp() throws FileNotFoundException, RejectException{
		
		councilPlayers = new ArrayList<PlayerColor>();
		beforePlayers = new ArrayList<PlayerColor>();
		
		String player1 = "primo";
		String player2 = "secondo";
		String player3 = "terzo";
 		ArrayList<String> names= new ArrayList<String>();
		names.add(player1);
		names.add(player2);
		names.add(player3);
		
		model = new Match(names);
		
		ArrayList<Player> temp = new ArrayList<Player>();
		temp = model.getBoard().getPlayers();
		for (Player player : temp)
			beforePlayers.add(player.getColor());
		
		System.out.println(beforePlayers);
		
		model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.BLACK).setPower(4);
		model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.WHITE).setPower(2);
		model.getBoard().getPlayerByName("terzo").getFamiliarByColor(DiceColor.ORANGE).setPower(3);
		
		ActionChoice aChoiceFirst = new ActionChoice("terzo");
		aChoiceFirst.setChoice(0, 11);
		aChoiceFirst.setChoice(3, 3);
		
		Move move = ChoiceToMove.createMove(aChoiceFirst, model.getBoard());
		frstAction = new CouncilPalaceAction(model, move);
		frstAction.actionHandler();
		
		ActionChoice aChoiceSecond = new ActionChoice("primo");
		aChoiceSecond.setChoice(0, 11);
		aChoiceSecond.setChoice(3, 1);
		
		Move moveSecond = ChoiceToMove.createMove(aChoiceSecond, model.getBoard());
		scndAction = new CouncilPalaceAction(model, moveSecond);
		scndAction.actionHandler();
		
		ActionChoice aChoiceThird = new ActionChoice("secondo");
		aChoiceThird.setChoice(0, 11);
		aChoiceThird.setChoice(3, 2);
		
		Move moveThird = ChoiceToMove.createMove(aChoiceThird, model.getBoard());
		thrdAction = new CouncilPalaceAction(model, moveThird);
		thrdAction.actionHandler();
		
		space = (CouncilPalaceArea) model.getBoard().getSpace("CouncilPalace");
		councilPlayers =space.playersOrder();
		System.out.println(space);
		
		RoundState state = new EndOfTheRoundState();
		state.doAction(2, model);
		
		//councilPlayers =space.playersOrder();
		System.out.println(councilPlayers);
		
		
	}
	
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(true, councilPlayers.get(0) == beforePlayers.get(2));
		assertEquals(true, councilPlayers.get(1) == beforePlayers.get(0));
		assertEquals(true, councilPlayers.get(2) == beforePlayers.get(1));
		
	}

}
