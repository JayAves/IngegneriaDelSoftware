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
	private Long period;
	
	
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
			
		if (counter==2){
				
				roomHandler.add(new Room(playersInQueue));
				counter=0;
				playersInQueue.clear();
			}
	}

	
	@Override
	public void update(Observable o, Object arg) {
		
		if (!(inQueue((ClientThread) arg))) {
			
			try {
			
				addPlayer((ClientThread) arg);
		
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find json file- and then no game was initialized");
				}
			}
		
			else {
				
				updateView((ClientThread) arg);
				
			}
	}
	
	private synchronized boolean inQueue(ClientThread arg) {
		
		for (ClientThread th: playersInQueue) {
			
			if (th.IDcode.contentEquals(arg.IDcode)) {
				
				playersInQueue.remove(th);
				playersInQueue.add(arg);
				return true;
			}
				
		}
		return false;
	}

	private void updateView(ClientThread thread) {
		
		for (Room room: roomHandler) {
			
			for (ClientThread client: room.getViews()) {
				
				if (thread.IDcode.contentEquals(client.IDcode)) {
					
					room.getController().removeView(client.getClientName(), client);
					room.getController().addView(thread.getClientName(),  thread );
				}
			}
		}
		
	}

	public void run(){
		
		while (true){
			
			//timer.scheduleAtFixedRate(new Task(), 0, period);
			
			
				try {
					
					sleep(1000);
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
			if (playersInQueue.size()>1){
				
				roomHandler.add(new Room(playersInQueue));
				counter=0;
				playersInQueue.clear();
			}
		}
		
	}


	
}
		


