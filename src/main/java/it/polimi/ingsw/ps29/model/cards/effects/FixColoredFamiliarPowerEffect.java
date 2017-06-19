package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;

public class FixColoredFamiliarPowerEffect extends EmpowermentActionEffect{
	
	@Override
	public void performEffect(Player player) {
		
		for (int i = 0; i < 3; i++){
			player.getFamily()[i].setPower(diceEmpowerment);
			player.getFamily()[i].setFixedPower();
		}
		
		
	}
	
	

}
