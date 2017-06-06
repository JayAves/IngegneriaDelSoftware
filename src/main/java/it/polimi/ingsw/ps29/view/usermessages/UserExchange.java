package it.polimi.ingsw.ps29.view.usermessages;

public class UserExchange implements UserMessage {
	int [] choice;
	
	public UserExchange() {
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
	
	
}
