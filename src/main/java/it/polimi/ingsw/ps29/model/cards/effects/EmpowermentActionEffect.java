package it.polimi.ingsw.ps29.model.cards.effects;


abstract class EmpowermentActionEffect extends Effect {

	protected int diceEmpowerment;
	//può essere sia positivo (effetto permanente), sia negativo (scomunica)
	
	
	/*	
	@Override
	protected void performEffect(BonusAndMalusPlayer modifier) {
		// TODO Auto-generated method stub
		int a= modifier.getDiceModifier();
		modifier.setDiceModifier(a+=diceEmpowerment);
	}
	*/
	
}
