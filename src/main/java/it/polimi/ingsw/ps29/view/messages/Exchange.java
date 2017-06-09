package it.polimi.ingsw.ps29.view.messages;

import it.polimi.ingsw.ps29.view_client.Client.VisitorServerMessages;

public class Exchange implements Message {
	int [] choice;
	
	public Exchange() {
		choice = new int [3];
		choice [0] = 0; //choice
		choice[1] = 0; //resOUT
		choice [2] = 0; //resIN
	}
	
	public void setChoice (int index, int ch) {
		choice[index] = ch; 
	}
	
	public int getChoice (int index) {
		return choice[index];
	}

	@Override
	public void visit(it.polimi.ingsw.ps29.controller.Controller.VisitorMessages visitor) {
		visitor.visit(this);
		
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		// TODO Auto-generated method stub
		visitor.receive(this);
	}

	

	
	
}
