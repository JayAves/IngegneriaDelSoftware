package it.polimi.ingsw.ps29.view.inputCLI;

import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;
import it.polimi.ingsw.ps29.view.InputOutput;

public class FakeScanner {
	int timer;
	InputOutput inputOutput;
	
	public FakeScanner (int timer, InputOutput inputOutput) {
		this.timer = timer;
		this.inputOutput = inputOutput;
	}
	
	public int nextInt () throws ExpiredTimeException {
		InputWithTimer input = new InputWithTimer(timer, inputOutput);
		int choice = input.read();
		
		return choice;
	}
	
	public int getTimer () {
		return timer;
	}

}
