package it.polimi.ingsw.ps29.view.inputCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;

public class InputWithTimer implements Runnable {
	private boolean endTime = false;
	private boolean running = false;
	private long startTime;
	private int timer;
	
	public InputWithTimer(int timer) {
		this.timer = timer;
	}
	
	public String read() throws ExpiredTimeException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		startTime = System.currentTimeMillis();
		running = true;
		new Thread(this).start();
		try {
			while (!endTime  && !in.ready()) {
				try {
					Thread.sleep(100);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Thread.currentThread().interrupt();
				}
			}
			
			running = false;

			//if ((in.ready()) && (in.readLine().chars().allMatch(Character::isDigit)) && (!in.readLine().equals("")))	
			if (in.ready())	
				return in.readLine();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		throw new ExpiredTimeException();
			
		
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
			
			if (System.currentTimeMillis() - startTime > timer)
				endTime = true;
		}
	}
	
	public int getTimer () {
		return timer;
	}
}
