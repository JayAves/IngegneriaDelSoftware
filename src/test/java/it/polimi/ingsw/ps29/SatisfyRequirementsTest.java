package it.polimi.ingsw.ps29;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.cards.BuildingCard;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.CharacterCard;
import it.polimi.ingsw.ps29.model.cards.LeaderCard;
import it.polimi.ingsw.ps29.model.cards.TerritoryCard;
import it.polimi.ingsw.ps29.model.cards.VentureCard;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.PersonalBonusTile;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import junit.framework.TestCase;

public class SatisfyRequirementsTest extends TestCase{
	
	Player player;
	
	public SatisfyRequirementsTest(String testName) {
		
		super(testName);
		
	}
	
	@BeforeClass
	public void setUp(){
		
	ArrayList<Resource> harvest = new ArrayList<Resource>();
	ArrayList<Resource> production = new ArrayList<Resource>();
		
	PersonalBonusTile tile = new PersonalBonusTile(1, harvest, production);
		
	player = new Player("primo",Color.BLUE, null);
	
	for (int i = 0; i < 5 ; i++){
		Card card = new CharacterCard("stub", Period.FIRST, "character", i, null, null, null);
		player.getPersonalBoard().addCard(card);
	}
	
	for (int i = 0; i < 4 ; i++){
		Card card = new VentureCard("stub",i, Period.FIRST, "venture", i + 5, null, null, null);
		player.getPersonalBoard().addCard(card);
	}
	
	for (int i = 0; i < 6 ; i++){
		Card card = new TerritoryCard("stub", Period.FIRST, "territory", i + 9, null, null, null, i);
		player.getPersonalBoard().addCard(card);
	}
	
	for (int i = 0; i < 2 ; i++){
		Card card = new BuildingCard("stub", Period.FIRST, "building", i + 15, null, null, null, i);
		player.getPersonalBoard().addCard(card);
	}
	ArrayList<LeaderCard> toAdd = new ArrayList<LeaderCard>();
	
	ArrayList<Resource> resRequrements = new ArrayList<Resource>();
	ArrayList<String> cardRequirements = new ArrayList<String>();
	ArrayList<Integer> cRequrements = new ArrayList<Integer>();
	
	resRequrements.add(new Resource("servant", 2));
	cardRequirements.add("territory");
	cardRequirements.add("building");
	cRequrements.add(2);
	cRequrements.add(3);
	LeaderCard leader1 = new LeaderCard("stub", 0, 0, null, resRequrements, cardRequirements, cRequrements);
    toAdd.add(leader1);
    
    ArrayList<Resource> resRequrements2 = new ArrayList<Resource>();
	ArrayList<String> cardRequirements2 = new ArrayList<String>();
	ArrayList<Integer> cRequrements2 = new ArrayList<Integer>();
	
	resRequrements2.add(new Resource("servant", 2));
	cardRequirements2.add("character");
	cardRequirements2.add("building");
	cRequrements2.add(8);
	cRequrements2.add(3);
	LeaderCard leader2 = new LeaderCard("stub", 0, 0, null, resRequrements2, cardRequirements2, cRequrements2);
    toAdd.add(leader2);
    
    ArrayList<Resource> resRequrements3 = new ArrayList<Resource>();
	ArrayList<String> cardRequirements3 = new ArrayList<String>();
	ArrayList<Integer> cRequrements3 = new ArrayList<Integer>();
	
	resRequrements3.add(new Resource("servant", 2));
	cardRequirements3.add("any");
	cRequrements3.add(2);
	LeaderCard leader3 = new LeaderCard("stub", 0, 0, null, resRequrements3, cardRequirements3, cRequrements3);
    toAdd.add(leader3);
    
    player.getPersonalBoard().addLeaderCards(toAdd);
      
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(false,player.getPersonalBoard().satisfyRequirements(player.getPersonalBoard().getLeaderCards().get(0)));
		assertEquals(false,player.getPersonalBoard().satisfyRequirements(player.getPersonalBoard().getLeaderCards().get(1)));
		assertEquals(true ,player.getPersonalBoard().satisfyRequirements(player.getPersonalBoard().getLeaderCards().get(2)));
	}

}
