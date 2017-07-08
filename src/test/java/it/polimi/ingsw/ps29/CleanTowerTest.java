package it.polimi.ingsw.ps29;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.space.TowerArea;
import junit.framework.TestCase;

public class CleanTowerTest extends TestCase {
	
	TowerArea tower;
	
	public CleanTowerTest (String testName) {
		super (testName);
	}
	
	@BeforeClass
	public void setUp () {
		tower = new TowerArea();
		tower.setPlacementFloor(1);
		tower.placeFamiliar(new FamilyMember(DiceColor.BLACK, PlayerColor.GREEN), false);
		
		tower.setPlacementFloor(2);
		tower.placeFamiliar(new FamilyMember(DiceColor.BLACK, PlayerColor.GREEN), false);
		
		tower.setPlacementFloor(3);
		tower.placeFamiliar(new FamilyMember(DiceColor.BLACK, PlayerColor.GREEN), false);
		
		tower.setPlacementFloor(4);
		tower.placeFamiliar(new FamilyMember(DiceColor.BLACK, PlayerColor.GREEN), false);
		
		tower.cleanSpace();
		
	}
	
	@Test
	public void test() {
		assertTrue(tower.isEmpty());
	}
	

}
