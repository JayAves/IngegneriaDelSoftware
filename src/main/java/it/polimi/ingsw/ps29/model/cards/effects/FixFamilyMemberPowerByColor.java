package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Player;

/**
 * LEADER: Set all familiars' power to 5.
 * @author Giovanni Mele
 *
 */
public class FixFamilyMemberPowerByColor extends EmpowermentActionEffect{
	
	private ArrayList<String> colors;
	
	public FixFamilyMemberPowerByColor(ArrayList<String> colors, int diceEmpowerment) {
		
		this.colors = colors;
		this.diceEmpowerment = diceEmpowerment;
	}
	
	@Override
	public void performEffect(Player player) {
		
		for (String color: colors){
			player.getFamiliarByColor(DiceColor.valueOf(color)).setFixedPower();
			player.getFamiliarByColor(DiceColor.valueOf(color)).setPower(diceEmpowerment);
		}
				
	}
	
}
