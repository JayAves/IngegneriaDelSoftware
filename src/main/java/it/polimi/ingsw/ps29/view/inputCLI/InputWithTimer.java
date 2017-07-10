package it.polimi.ingsw.ps29.view.inputCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;
import it.polimi.ingsw.ps29.view.InputOutput;

/**
 * Puts a time limit for Scanner to read user input from CLI. Received a timer, uses System.currentTimeMillis to count;
 * @author Pietro Melzi
 * @throws ExpiredTimeException when time is over
 * @see Scanner
 *
 */

public class InputWithTimer implements Runnable {
	private boolean endTime = false;
	private boolean running = false;
	private int timer;
	private InputOutput inOut;
	
	public InputWithTimer(int timer, InputOutput inOut) {
		this.timer = timer;
		this.inOut = inOut;
	}
	
	public int read() throws ExpiredTimeException {
		int input = -1;
		BufferedReader in;
		running = true;
		
		//timer thread starts
		new Thread(this).start();
		
		do{
			in = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				while (!endTime  && !in.ready()) {
					try {
						Thread.sleep(100);
						
					} catch (InterruptedException e) {

						Thread.currentThread().interrupt();
					}
				}
	
				if (!in.ready())  {
					//no input was captured
					running = false;
					throw new ExpiredTimeException();
				}
				
				try {
					input = new Integer(in.readLine());
					
				} catch (NumberFormatException e) {
					//input is not Integer
					input = -1;
				}
				
			} catch (IOException e) {

				System.err.println("Failed to read scanner input");
				Thread.currentThread().interrupt();
			}
		
		} while (input == -1);
		
		running = false;
		return input;
		
			
		
	}

	@Override
	public void run() {
		while (running) {
			
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {

				Thread.currentThread().interrupt();
			}
			
			if (System.currentTimeMillis() - inOut.getTimeStart() > timer)
				endTime = true;
		}
	}
	
	public int getTimer () {
		return timer;
	}
}
