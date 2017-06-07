package it.polimi.ingsw.ps29.server;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

public class RoomCreator implements Observer{
	
	
	private ArrayList<String> playersInQueue;
	private ArrayList<ClientThread> threads;
	private int counter; //metodi su counter devono essere synchronized
	private ArrayList<Room> roomHandler;
	private Timer timer;
	
	
	public RoomCreator(){
		
		this.counter =0;
		
		this.roomHandler= new ArrayList<Room>();
		
		this.playersInQueue= new ArrayList<String>();
		
	}
	
	
	
	
	public void addPlayer(String s) throws FileNotFoundException{
		
		playersInQueue.add(s);
		
		increaseCounter();
	}
	
	private synchronized void increaseCounter() throws FileNotFoundException{
			counter++;
			
			if (counter==4){
				
				roomHandler.add(new Room(playersInQueue,threads));
				
			}
	}


	@Override
	public void update(Observable o, Object arg) {
		
		// TODO Auto-generated method stub
		
		if (arg instanceof String){
			
			try {
				
				addPlayer((String) arg);
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		if (o instanceof SocketGathererInServer){
				
			threads.add((ClientThread) arg);
			
			try {
				
				addPlayer(arg.toString());
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
		
}
		


