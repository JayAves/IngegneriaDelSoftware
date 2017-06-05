package it.polimi.ingsw.ps29;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberProductionDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import junit.framework.TestCase;

public class FamilyMemberTest extends TestCase {

	FamilyMember member; 
	FakeFamilyMemberProductionDecorator fakedec;
	
	
	public FamilyMemberTest (String testName) {
		super (testName);
	}
	
	@BeforeClass
	public void setUp () {
		member = new FamilyMember(DiceColor.ORANGE, Color.RED);
		FakeFamilyMember fake = new FakeFamilyMember();
		fakedec = new FakeFamilyMemberProductionDecorator (fake, 2);
		member.setPower(3);
	}
	
	@Test
	public void test() {
		//assertEquals("RED", member.getPlayerColor().toString());
		assertEquals(5, member.getPower()+fakedec.getProductionPower());
	}

}
