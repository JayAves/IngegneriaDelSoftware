package it.polimi.ingsw.ps29.server;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

public class RoomCreator extends Thread implements Observer{
	
	
	private ArrayList<ClientThread> playersInQueue;
	private int counter; //metodi su counter devono essere synchronized
	private ArrayList<Room> roomHandler;
	private static Timer timer;
	
	
	public RoomCreator(){
		
		this.counter =0;
		
		this.roomHandler= new ArrayList<Room>();
		
		this.playersInQueue= new ArrayList<ClientThread>();
		
		//start timer
	}
	
	
	
	
	public void addPlayer(ClientThread s) throws FileNotFoundException{
		
		playersInQueue.add(s);
		
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
		
		if (!(o instanceof ClientThread)){
			
			//da gestire
		}
		
		
			
	}
	
	public void run(){
		
		while (true){
			
			//quando scatta il timer
			
			if (playersInQueue.size()>1){
				
				try {
					
					roomHandler.add(new Room(playersInQueue));
					counter=0;
					playersInQueue.clear();
				
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
		


