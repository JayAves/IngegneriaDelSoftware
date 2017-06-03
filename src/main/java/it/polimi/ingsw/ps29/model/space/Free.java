package it.polimi.ingsw.ps29.model.space;

public class Free implements State{

	@Override
	public void returnStatus() {
		System.out.println("Ok lo spazio è libero!");
	}
	
}

