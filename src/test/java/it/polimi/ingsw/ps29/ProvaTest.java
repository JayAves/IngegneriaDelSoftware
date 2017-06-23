package it.polimi.ingsw.ps29;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.action.Action;
import it.polimi.ingsw.ps29.model.action.TowerAction;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.PersonalBonusTile;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundSetupState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundState;
import it.polimi.ingsw.ps29.model.space.TowerArea;
import junit.framework.TestCase;

public class ProvaTest extends TestCase {
	
	Resource res;
	Move move;
	Player player, player2;	
	PersonalBonusTile pbt;
	FamilyMember fam;
	Match model;
	Action action;
	
	public ProvaTest (String testName) {
		
		super (testName);
	}
	
	@Before
	public void setUp () throws FileNotFoundException {
		pbt = new PersonalBonusTile(1, new ArrayList<Resource> (), new ArrayList <Resource> ());
		String player1 = "aa";
		String player2 = "bb";
		ArrayList<String> names= new ArrayList<String>();
		names.add(player1);
		names.add(player2);
		
		model = new Match(names);
		FamilyMemberInterface member = model.getBoard().getCurrentPlayer().getFamiliarByColor(DiceColor.ORANGE);
		member.setPower(5);
		System.out.println(model.getBoard().getCurrentPlayer().getPersonalBoard().getResources());
		
		RoundState rss = new RoundSetupState();
		rss = rss.doAction(1, model);

		move = new Move (model.getBoard().getCurrentPlayer(), "buildingTower", 2, 2, member);
		System.out.println (((TowerArea)model.getBoard().getSpace(move.getSpace())).printCards());
		System.out.println("\n\nCarta presa: \n\n");
		((TowerArea)model.getBoard().getSpace(move.getSpace())).setPlacementFloor(2);
		System.out.println (((TowerArea)model.getBoard().getSpace(move.getSpace())).takeCard());
		
		action = new TowerAction(model, move);
		action.actionHandler();
		
		
		
		System.out.println(move.getPlayer().getPersonalBoard().getResources());
		//System.out.println(model.getBoard().getCurrentPlayer().getPersonalBoard().getResources());
		//System.out.println(model.getBoard().getDecks().get(2).getCard(2));
		
	}

	@Test
	public void test() {
		//costi da diminuire anzichè aumentare
		
		
		//assertEquals(true, ((TowerAction)action).isPlaceable());
		//assertEquals(true, move.getPlayer().getPersonalBoard().getResources());
	}

}
