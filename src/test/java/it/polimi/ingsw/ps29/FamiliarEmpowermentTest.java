package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.FamiliarEmpowermentByColor;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import junit.framework.TestCase;

public class FamiliarEmpowermentTest  extends TestCase{
	
	Match model;
	Effect effect;
	
	public FamiliarEmpowermentTest (String testName){
		super(testName);
	}

	@BeforeClass
	public void setUp() throws FileNotFoundException{
		
		String player1 = "primo";
		String player2 = "secondo";
		String player3 = "terzo";
 		ArrayList<String> names= new ArrayList<String>();
		names.add(player1);
		names.add(player2);
		names.add(player3);
		
		model = new Match(names);
		
		model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.BLACK).setPower(4);
		model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.BLACK).setPower(4);
		model.getBoard().getPlayerByName("terzo").getFamiliarByColor(DiceColor.BLACK).setPower(2);
		model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.WHITE).setPower(4);
		model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.WHITE).setPower(4);
		model.getBoard().getPlayerByName("terzo").getFamiliarByColor(DiceColor.WHITE).setPower(2);
		
		ArrayList<String> colors = new ArrayList<String>();
		colors.add("BLACK");
		colors.add("WHITE");
		
		effect = new FamiliarEmpowermentByColor(colors, -1);
		
		effect.performEffect(model.getBoard().getPlayerByName("primo"));
		effect.performEffect(model.getBoard().getPlayerByName("secondo"));
		effect.performEffect(model.getBoard().getPlayerByName("terzo"));
	}
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(3, model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.BLACK).getPower());
		assertEquals(3, model.getBoard().getPlayerByName("primo").getFamiliarByColor(DiceColor.WHITE).getPower());
		assertEquals(3, model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.BLACK).getPower());
		assertEquals(3, model.getBoard().getPlayerByName("secondo").getFamiliarByColor(DiceColor.WHITE).getPower());
		assertEquals(1, model.getBoard().getPlayerByName("terzo").getFamiliarByColor(DiceColor.BLACK).getPower());
		assertEquals(1, model.getBoard().getPlayerByName("terzo").getFamiliarByColor(DiceColor.WHITE).getPower());
	}

}
