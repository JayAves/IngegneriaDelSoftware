package it.polimi.ingsw.ps29.model.action;

import org.omg.PortableServer.Servant;

import it.polimi.ingsw.ps29.model.game.FamilyMember;

public class TowerAction implements Action {

	@Override
	public void standardPlacement(FamilyMember familyMember, Servant servants) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPlaceable(FamilyMember familyMember, Servant servants) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void performAction() {
		System.out.println("Azione di tower");

	}

}
