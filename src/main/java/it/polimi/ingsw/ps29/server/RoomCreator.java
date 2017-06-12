package it.polimi.ingsw.ps29.server;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class RoomCreator extends Thread implements Observer{
	
	
	private ArrayList<ClientThread> playersInQueue;
	private int counter; //metodi su counter devono essere synchronized
	private ArrayList<Room> roomHandler;
	private static Timer timer;
	private Long period;
	
	
	public RoomCreator(){
		
		this.counter =0;
		
		this.roomHandler= new ArrayList<Room>();
		
		this.playersInQueue= new ArrayList<ClientThread>();
		
		this.timer= new Timer();
		
	}
	
	public void addPlayer(ClientThread s) throws FileNotFoundException{
		
		playersInQueue.add(s);
		System.out.println("Player aggiunto alla coda");
		
		increaseCounter();
	}
	
	private synchronized void increaseCounter() throws FileNotFoundException{
			
		counter++;
			
		if (counter==4){
				
				roomHandler.add(new Room(playersInQueue));
				counter=0;
				playersInQueue.clear();
			}
	}

	
	@Override
	public void update(Observable o, Object arg) {
		
		// TODO Auto-generated method stub
		
		if (!(arg instanceof ClientThread)){
			
			//da gestire
		}
		
		try {
			
			addPlayer((ClientThread) arg);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find json file- and then no game was initialized");
		}
			
	}
	
	public void run(){
		
		int i=0;
		while (true){
			
			//timer.scheduleAtFixedRate(new Task(), 0, period);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not sleep");
			}
		}
	}
	
	
	private class Task extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (playersInQueue.size()>1){
				
				try {
					
					roomHandler.add(new Room(playersInQueue));
					counter=0;
					playersInQueue.clear();
				
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Could not find json file- and then no game was initialized");
				}
			}
		}
		
	}
}
		


