package it.polimi.ingsw.ps29.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

public class RoomCreator implements Observer{
	
	
	private ArrayList<String> playersInQueue;
	private ArrayList<ClientThread> threads;
	//private ArrayList<ClientThread> SocketPlayers;
	//private ArrayList<String> RMIPlayers;
	private int counter; //metodi su counter devono essere synchronized
	private ArrayList<Room> roomHandler;
	private Timer timer;
	
	
	public RoomCreator(){
		
		this.counter =0;
		
		this.roomHandler= new ArrayList<Room>();
		
		this.playersInQueue= new ArrayList<String>();
		
	}
	
	
	
	
	public void addPlayer(String s){
		
		playersInQueue.add(s);
		
		increaseCounter();
	}
	
	private synchronized void increaseCounter(){
			counter++;
			
			if (counter==4){
				
				roomHandler.add(new Room(playersInQueue));
				
			}
	}


	@Override
	public void update(Observable o, Object arg) {
		
		// TODO Auto-generated method stub
		
		if (arg instanceof String){
			
			addPlayer((String) arg);
		}
			
		if (o instanceof SocketGathererInServer){
				
			threads.add((ClientThread) arg);
			
			addPlayer(arg.toString());
		}	
	}
		
	}
		


