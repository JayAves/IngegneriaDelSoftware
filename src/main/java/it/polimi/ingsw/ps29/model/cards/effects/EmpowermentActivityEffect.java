package it.polimi.ingsw.ps29.model.cards.effects;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberHarvestDecorator;

public class EmpowermentActivityEffect extends EmpowermentActionEffect {
	
	private String activityName;
	
	
	
	/*@Override
	public void performEffect(BonusAndMalusPlayer modifier) {
		// TODO Auto-generated method stub
	if (activityName=="PRODUCTION"){
		
		int a= modifier.getProductionValueModifier();
		modifier.setProductionValueModifier(a+activityEmpowerment);
		}
	else if (activityName=="Harvest"){
		int a= modifier.getHarvestValueModifier();
		modifier.setHarvestValueModifier(a+activityEmpowerment);
		}
	}

*/
	public EmpowermentActivityEffect(int diceEmpowerment, String activityName) {
		this.diceEmpowerment = diceEmpowerment;
		this.activityName = activityName;
	}



	@Override
	void performEffect(Player player) {
		if(activityName.equals("Harvest")) 
			player.fakeFamiliar = new FamilyMemberHarvestDecorator(new FamilyMember(), diceEmpowerment);
		else
			player.fakeFamiliar = new FamilyMemberHarvestDecorator(new FamilyMember(), diceEmpowerment);
		
	}
		


}
	
