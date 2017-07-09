package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberPowerDecorator;

/**
 * LEADER: Empowers some familiars, not necessarily all of them.
 * @author Giovanni Mele
 *
 */
public class FamiliarEmpowermentByColor extends EmpowermentActionEffect{
	
	private ArrayList<String> colors;
	
	public FamiliarEmpowermentByColor(ArrayList<String> colors, int diceEmpowerment) {
		
		this.colors = colors;
		this.diceEmpowerment = diceEmpowerment;
	}
	
	@Override
	public void performEffect(Player player) {
		
		for (String color: colors){
			FamilyMemberInterface member = player.getFamiliarByColor(DiceColor.valueOf(color));
			member= new FamilyMemberPowerDecorator(member, diceEmpowerment);
			
			for ( int i = 0; i < player.getFamily().length; i++){
				if (player.getFamily()[i].getFamiliarColor() == DiceColor.valueOf(color)){
					player.getFamily()[i] = member;
				}
					
			}
		}
				
	}

	@Override
	public String toString() {
		return super.toString()+"FamiliarEmpowermentByColorExcommunication ";
	}
	
	

}
