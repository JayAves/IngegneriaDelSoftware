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
	private int period;
	
	
	
	public RoomCreator(){
		
		this.counter =0;
		
		this.roomHandler= new ArrayList<Room>();
		
		this.playersInQueue= new ArrayList<ClientThread>();
		
		this.timer= new Timer();
		
		
		
		
		
	}
	
	public void addPlayer(ClientThread s) throws FileNotFoundException{
		
		playersInQueue.add(s);
		System.out.println("Player queued");
		
		increaseCounter();
	}
	
	private synchronized void increaseCounter() throws FileNotFoundException{
			
		counter++;
		
		if (counter==2) //countdown to game start is set
			timer.schedule(new Task(), period);
			
		if (counter==4){ //enough players for a new Room
			counter=0;
			System.out.println("New Room");
			Room newRoom= new Room(playersInQueue);
			roomHandler.add(newRoom);
			System.out.println(roomHandler.size());
			playersInQueue.clear();
			newRoom.start();
			
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
			throw new RuntimeException();
					
				}
			}	
	}
	
	private boolean inQueueCheck(ClientThread arg) {
		
		for (ClientThread th: playersInQueue) {
			
			if (th.IDcode.contentEquals(arg.IDcode)) {
				
				playersInQueue.remove(th);
				playersInQueue.add(arg);
				return true;
			}
				
		}
		return false;
		
	}

	private boolean updateView(ClientThread thread) {
		
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
	
	public void setPeriod(int period) {
		this.period=period;
	}

	public void run(){
		
		while (true){
			
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
				System.out.println(e.getLocalizedMessage());
			}
			
			
		
			
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
		
				if (counter>1) {
					
					counter=0;
					System.out.println("Room from timer");
					Room newRoom= new Room(playersInQueue);
					roomHandler.add(newRoom);
					System.out.println(roomHandler.size());
					playersInQueue.clear();
					newRoom.start();
					
					
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Could not sleep!");
						throw new RuntimeException();
					}
				}
				
				//System.out.println("RoomTimer accessed");
				
			
		}
		
	}


	
}
		


