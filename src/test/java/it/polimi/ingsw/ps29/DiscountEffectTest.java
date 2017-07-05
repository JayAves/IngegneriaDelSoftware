package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.model.action.TowerAction;
import it.polimi.ingsw.ps29.model.cards.effects.BonusPlacementEffect;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.EmpowermentPlacementEffect;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.model.game.resources.Woods;
import junit.framework.TestCase;

public class DiscountEffectTest extends TestCase {
	private ArrayList<Resource> cost;
	private TowerAction action;
	private Player p;
	
	public DiscountEffectTest (String testName) {
		super (testName);
	}

	@BeforeClass
	public void setUp () throws FileNotFoundException {
		p = new Player("aa", PlayerColor.BLUE, null);
		ArrayList<Resource> discount = new ArrayList<>();
		discount.add(new Resource ("wood", 2));
		
		//aggiungo due effetti di sconto al player (uno permanente e uno legato ad azione bonus)
		Effect specialPermanentEffect = new EmpowermentPlacementEffect(2, "Territory", discount);
		Effect bonusEffect = new BonusPlacementEffect(2, "building", discount);
		
		p.addSpecialPermanentEffects(specialPermanentEffect);
		p.addSpecialPermanentEffects(bonusEffect);
		
		//setto il player con risorsa di tipo WOOD e quantità 1
		p.getPersonalBoard().getResources().removeResource(ResourceType.WOOD);
		p.getPersonalBoard().getResources().updateResource(new Woods(1));
		
		//costo della carta
		cost = new ArrayList<Resource>();
		cost.add(new Resource("wood", 3));
		
		ArrayList<String> names = new ArrayList<String> ();
		names.add("aa");
		names.add("bb");
		Match model = new Match(names);
		
		//prevedo una mossa che piazza sulla buildingTower (dove sono previsti sconti) per un'azione bonus
		Move move = new Move(p, "buildingTower", 1, 0, new FamilyMember(DiceColor.BONUS, PlayerColor.BLUE));
		action = new TowerAction(model, move);
		
		//mi aspetto uno sconto di 2 wood sul costo della carta (3 wood)
		action.applyDiscounts(cost, DiceColor.BONUS);
		//mi aspetto che lo sconto legato all'azione bonus venga rimosso
		action.removeBonusDiscount();
	}
	
	
	@Test
	public void test() {
		assertEquals(1, cost.get(0).getAmount());
		assertEquals(1, p.getSpecialPermanentEffects().size());
		assertEquals(p.getSpecialPermanentEffects().get(0).getClass(), EmpowermentPlacementEffect.class);
	}

}
