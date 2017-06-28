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
	
	public int read() throws ExpiredTimeException {
		int input = -1;
		BufferedReader in;
		startTime = System.currentTimeMillis();
		running = true;
		
		//parte il thread che controlla la scadenza del timer
		new Thread(this).start();
		
		do{
			in = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				while (!endTime  && !in.ready()) {
					try {
						Thread.sleep(100);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	
				if (!in.ready()) 
					//sono uscito dal while ma l'utente non ha inserito nessun messaggio
					throw new ExpiredTimeException();
				
				try {
					input = new Integer(in.readLine());
					
				} catch (NumberFormatException e) {
					//l'input inserito dall'utente non è un intero
					input = -1;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
