package it.polimi.ingsw.ps29.model.action;



import it.polimi.ingsw.ps29.model.game.FamilyMember;

public class ProductionAction implements Action {

	@Override
	public void standardPlacement(FamilyMember familyMember, int servants) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPlaceable(FamilyMember familyMember, int servants) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void performAction() {
		System.out.println("Azione di production");

	}

}