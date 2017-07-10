package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.space.MarketArea;
import it.polimi.ingsw.ps29.model.space.TowerArea;
import junit.framework.TestCase;

public class BonusSpaceTest extends TestCase {
	private Match match;
	
	public BonusSpaceTest (String testName) {
		super(testName);
	}
	

	@BeforeClass
	public void setUp () throws FileNotFoundException {
		match = new Match(new ArrayList<>());
	}
	
	@Test
	public void test() {
		assertEquals(1, ((TowerArea)match.getBoard().getSpace("buildingTower")).getFloors().get(2).getResource().size());
		assertEquals("military", ((TowerArea)match.getBoard().getSpace("buildingTower")).getFloors().get(2).getResource().get(0).getType());
		assertEquals("wood", ((TowerArea)match.getBoard().getSpace("territoryTower")).getFloors().get(2).getResource().get(0).getType());
		assertEquals(1, ((TowerArea)match.getBoard().getSpace("territoryTower")).getFloors().get(2).getResource().get(0).getAmount());
	
		assertEquals(2, ((MarketArea)match.getBoard().getSpace("ThirdMarket")).getSlot().getBonus().size());
	}
	
}
