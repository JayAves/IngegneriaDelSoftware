package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberHarvestDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberProductionDecorator;

/**
 * CARD EFFECT: Empowers familiars for production or harvest.
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
public class EmpowermentActivityEffect extends EmpowermentActionEffect {
	
	private String activityName;
	
	public EmpowermentActivityEffect(int diceEmpowerment, String activityName) {
		this.diceEmpowerment = diceEmpowerment;
		this.activityName = activityName;
	}

	@Override
	public void performEffect(Player player) {
		if(activityName.equals("Harvest")) 
		 	player.setFakeFamiliar(new FakeFamilyMemberHarvestDecorator(player.getFakeFamiliar(), diceEmpowerment));
		 else if (activityName.equals("Production"))
			player.setFakeFamiliar(new FakeFamilyMemberProductionDecorator(player.getFakeFamiliar(), diceEmpowerment));
		
	}

	@Override
	public String toString () {
		return super.toString()+"Dice empowerment of "+diceEmpowerment+", for "+activityName.toUpperCase()+" ";
	}

}
	
