package it.polimi.ingsw.ps29.server;

import java.io.FileNotFoundException;
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
	private int period = 10000;
	private boolean startTimer;
	
	
	public RoomCreator(){
		
		this.counter =0;
		
		this.roomHandler= new ArrayList<Room>();
		
		this.playersInQueue= new ArrayList<ClientThread>();
		
		this.timer= new Timer();
		
		startTimer= false;
		
	}
	
	public void addPlayer(ClientThread s) throws FileNotFoundException{
		
		playersInQueue.add(s);
		System.out.println("Player queued");
		
		increaseCounter();
	}
	
	private synchronized void increaseCounter() throws FileNotFoundException{
			
		counter++;
		
		if (counter==1) //countdown to game start is set
			startTimer=true;
			
		if (counter==4){ //enough players for a new Room
			counter=0;
			System.out.println("New Room");
			roomHandler.add(new Room(playersInQueue));
			System.out.println(roomHandler.size());
			playersInQueue.clear();
			
		}
	}

	
	@Override
	public void update(Observable o, Object arg) {
		
		boolean updated= updateView((ClientThread) arg);
		boolean queued=	inQueueCheck((ClientThread) arg);
		
		if ((!updated)&& (!queued)) {
			try {
				
				addPlayer((ClientThread) arg);
		
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find json file- and then no game was initialized");
					
				}
			}	
	}
	
	private synchronized boolean inQueueCheck(ClientThread arg) {
		
		for (ClientThread th: playersInQueue) {
			
			if (th.IDcode.contentEquals(arg.IDcode)) {
				
				playersInQueue.remove(th);
				playersInQueue.add(arg);
				return true;
			}
				
		}
		return false;
		
	}

	private synchronized boolean updateView(ClientThread thread) {
		
		for (Room room: roomHandler) {
			
			System.out.println(room.getViews());
			
			for (ClientThread client: room.getViews()) {
				
				if (client.IDcode.contentEquals(thread.IDcode)) {
					
					room.getController().removeView(client.getClientName(), client);
					System.out.println("\nOld view:"+client.toString());
					client.stopClient();
					room.getController().addView(thread.getClientName(),  thread );
					
					thread.setInGame(true);
					System.out.println("\nNew View:"+thread.toString());
					return true;
				}
			}
		}
		return false;
		
		
		
	}

	public void run(){
		
		while (true){
			
			while (!startTimer) { //wait until 1 player is in queue
				
			}
			
			timer.schedule(new Task(), period);
		
			
				
			
		}
	}
	
	public ArrayList<ClientThread> getPlayersInQueue() {
		return playersInQueue;
	}
	
	public ArrayList<Room> getRooms(){
		return roomHandler;
	}
	
	
	private class Task extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (playersInQueue.size()>1){
				
				Room newRoom=new Room(playersInQueue);
				roomHandler.add(newRoom);
				System.out.println("Room: "+ newRoom);
				counter=0;
				playersInQueue.clear();
			}
		}
		
	}


	
}
		


