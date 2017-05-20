package it.polimi.ingsw.ps29.model.space;

public class Occupied implements State{

	@Override
	public void returnStatus() {
		//lancia eccezione
		System.out.println("E' occupato!");
	}

	//@Override
	//public void isThisFamilyMemberPlaceable() {
		// TODO Auto-generated method stub
		
	//}

}
