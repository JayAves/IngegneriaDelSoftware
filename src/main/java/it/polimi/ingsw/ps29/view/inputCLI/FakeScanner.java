package it.polimi.ingsw.ps29.view.inputCLI;

public class FakeScanner {
	int timer;
	
	public FakeScanner (int timer) {
		this.timer = timer;
	}
	
	public int nextInt () throws Exception {
		InputWithTimer input = new InputWithTimer(timer);
		int choice = 0;
		
		choice = new Integer(input.read());
		
		return choice;
	}

}
