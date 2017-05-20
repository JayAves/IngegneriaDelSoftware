package it.polimi.ingsw.ps29.model.cards;

import it.polimi.ingsw.ps29.model.action.BonusAndMalusPlayer;

public class EmpowermentDiceEffect extends Effect {

	private int diceEmpowerment;
	
	
	@Override
	protected void performEffect(BonusAndMalusPlayer modifier) {
		// TODO Auto-generated method stub
		int a= modifier.getDiceModifier();
		modifier.setDiceModifier(a+=diceEmpowerment);
	}

	public EmpowermentDiceEffect(int diceEmpowerment) {
		this.diceEmpowerment = diceEmpowerment;
	}

	
}
