package it.polimi.ingsw.ps29;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps29.messages.exception.RejectException;
import it.polimi.ingsw.ps29.model.action.Action;
import it.polimi.ingsw.ps29.model.action.TowerAction;
import it.polimi.ingsw.ps29.model.cards.BuildingCard;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.PersonalBonusTile;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.resources.Stones;
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
		FamilyMemberInterface member = model.getCurrentPlayer().getFamiliarByColor(DiceColor.ORANGE);
		member.setPower(5);
		
		RoundState rss = new RoundSetupState();
		rss = rss.doAction(1, model);

		move = new Move (model.getCurrentPlayer(), "buildingTower", 2, 2, member);
		((TowerArea)model.getBoard().getSpace(move.getSpace())).setPlacementFloor(2);
		ArrayList<Resource> cost = new ArrayList<Resource>();
		cost.add(new Stones(2));
		Card card = new BuildingCard("", null, "building", 1, new ArrayList<Effect>(), null, cost, 2);
		((TowerArea)model.getBoard().getSpace(move.getSpace())).setCard(card, 2);
		
		action = new TowerAction(model, move);
		try {
			action.actionHandler();
		} catch (RejectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void test() {
		assertEquals(0, move.getPlayer().getPersonalBoard().getSpecificResource("stone").getAmount());
	}

}
