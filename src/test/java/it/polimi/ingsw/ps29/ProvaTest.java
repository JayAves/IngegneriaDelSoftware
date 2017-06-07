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
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundSetupState;
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
		pbt = new PersonalBonusTile(new ArrayList<Resource> (), new ArrayList <Resource> ());
		player = new Player("aa", Color.BLUE, pbt); //di questo il match prende solo il nome
		player2 = new Player("bb", Color.GREEN, pbt);//di questo il match prende solo il nome 
		ArrayList<Player> pl = new ArrayList<Player>();
		pl.add(player);
		pl.add(player2);
		ArrayList<String> names= new ArrayList<String>();
		names.add(player.getName());
		names.add(player2.getName());
		model = new Match(names);
		fam = new FamilyMember(DiceColor.ORANGE, Color.BLUE);
		fam.setPower(1);
		move = new Move (player, "buildingTower", 2, 2, fam);
		move.getPlayer().getPersonalBoard().getResources();
		action = new TowerAction(model, move);
		RoundSetupState rss = new RoundSetupState();
		rss.doAction(1, model.getBoard());
		System.out.println (((TowerArea)model.getBoard().getSpace(move.getSpace())).takeCard());
		//System.out.println(model.getBoard().getDecks().get(2).getCard(2));
		
	}

	@Test
	public void test() {
		//assertEquals(true, ((TowerAction)action).isPlaceable());
		//assertEquals(true, move.getPlayer().getPersonalBoard().getResources());
	}

}
