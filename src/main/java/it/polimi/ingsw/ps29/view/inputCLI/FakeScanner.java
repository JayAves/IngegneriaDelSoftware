package it.polimi.ingsw.ps29.view.inputCLI;

import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;

public class FakeScanner {
	int timer;
	
	public FakeScanner (int timer) {
		this.timer = timer;
	}
	
	public int nextInt () throws ExpiredTimeException {
		InputWithTimer input = new InputWithTimer(timer);
		int choice = new Integer(input.read());
		
		return choice;
	}
	
	public int getTimer () {
		return timer;
	}

}
