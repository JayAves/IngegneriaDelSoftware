package it.polimi.ingsw.ps29.model.cards;

public class EmpowermentActivityEffect extends EmpowermentDiceEffect {
	

	private int activityEmpowerment;
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
	public EmpowermentActivityEffect(int diceEmpowerment, int activityEmpowerment, String activityName) {
		super(diceEmpowerment);
		this.activityEmpowerment = activityEmpowerment;
		this.activityName = activityName;
	}
		


}
	
