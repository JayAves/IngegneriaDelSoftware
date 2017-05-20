package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.FamilyMember;
import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.resources.Servants;
import it.polimi.ingsw.ps29.model.provvisorio.packageTorre.CharacterTower;

public class TowerAction implements Action {
	
	CharacterTower tower;

	@Override
<<<<<<< HEAD
	public void standardPlacement(FamilyMember familyMember, Servants servants) {
=======
	public void standardPlacement(FamilyMember familyMember, int servants) {
>>>>>>> 0c17943064297feeeebc626efe776f3e5cfce82b
		// TODO Auto-generated method stub

	}

	@Override
<<<<<<< HEAD
	public boolean isPlaceable(FamilyMember familyMember, Servants servants) {
=======
	public boolean isPlaceable(FamilyMember familyMember, int servants) {
>>>>>>> 0c17943064297feeeebc626efe776f3e5cfce82b
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void performAction(PersonalBoard board, FamilyMember familymember, Servants servants) {
		/*String input;
		System.out.println("In quale piano vuoi piazzarti");
		switch(input){
		
		}
		board.addCard(tower.placeFamilyMember(dude, stuff, floor));
	}
    */


}
