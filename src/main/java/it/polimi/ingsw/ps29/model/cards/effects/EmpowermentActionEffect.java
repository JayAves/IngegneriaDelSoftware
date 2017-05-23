package it.polimi.ingsw.ps29.model.cards.effects;

public class EmpowermentActionEffect extends Effect {

	private int diceEmpowerment;

	@Override
	public void performEffect() {
		// TODO Auto-generated method stub
		
	}
	

	
	/*	
	@Override
	protected void performEffect(BonusAndMalusPlayer modifier) {
		// TODO Auto-generated method stub
		int a= modifier.getDiceModifier();
		modifier.setDiceModifier(a+=diceEmpowerment);
	}
	*/
	public EmpowermentActionEffect(int diceEmpowerment) {
		this.diceEmpowerment = diceEmpowerment;
	}

	
}
