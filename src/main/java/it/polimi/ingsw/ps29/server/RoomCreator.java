package it.polimi.ingsw.ps29.server;

import java.util.ArrayList;

public class RoomCreator extends Thread {
	
	private ArrayList<ClientThread> SocketPlayers;
	private ArrayList<String> RMIPlayers;
	private int counter; //metodi su counter devono essere synchronized
	private boolean running;
	private ArrayList<Room> roomHandler;
	
	public RoomCreator() {
		this.counter =0;
		this.SocketPlayers= new ArrayList<ClientThread>();
		this.RMIPlayers= new ArrayList<String>();
		this.running=false;
		this.roomHandler= new ArrayList<Room>();
	}
	
	public void run(){
		
		running=true;
		
		while (running){
			
			if (counter%4==0){
				//creo partita
			}
			
		}
		
		
	}
}
