package it.polimi.ingsw.ps29.server;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Hub where Rooms are made and managed. Has a queue used to put players together in new games. 
 * @author Pietro Grotti
 * @see Room
 */

public class RoomCreator extends Thread implements Observer {
	
	private ArrayList<ClientThread> playersInQueue;
	private int counter; //metodi su counter devono essere synchronized
	private ArrayList<Room> roomHandler;
	private static Timer timer;
	private Timer scheduler;
	private static final int UPDATE_TIME= 3600000;
	private int period;
	
	public RoomCreator(){
		this.counter = 0;
		this.roomHandler= new ArrayList<Room>();
		this.playersInQueue= new ArrayList<ClientThread>();
		
		this.timer= new Timer();
		scheduler= new Timer();
		//every UPDATE_TIME milliseconds, a Room management routine is scheduled
		scheduler.scheduleAtFixedRate(new Updater(), UPDATE_TIME, UPDATE_TIME); 
	}
	
	public void addPlayer(ClientThread s) throws FileNotFoundException{
		playersInQueue.add(s);
		System.out.println("Player queued");
		
		increaseCounter();
	}
	
	/**
	 * Checks the number of players waiting, schedules roomTimer as soon as two players are in queue
	 * @throws FileNotFoundException
	 */
	
	private synchronized void increaseCounter() throws FileNotFoundException{
			
		counter++;
		
		if (counter==2) //countdown to game start is set
			timer.schedule(new Task(), period);
			
		if (counter==2){ //enough players for a new Room
			counter=0;
			System.out.println("New Room");
			Room newRoom= new Room(playersInQueue);
			roomHandler.add(newRoom);
			System.out.println(roomHandler.size());
			playersInQueue.clear();
			newRoom.start();
			
		}
	}

	/**
	 * Handles new players, verifying first if they are already in some games, second if they are already in queue
	 * @param o  connection Gatherer in server
	 * @param arg new ClientThread
	 * @see SocketGatherer
	 * @see RMIGatherer
	 */
	
	@Override
	public void update(Observable o, Object arg) {
		
		boolean updated= updateView((ClientThread) arg);
		boolean queued=	inQueueCheck((ClientThread) arg);
		
		if ((!updated)&& (!queued)) {
			try {
				addPlayer((ClientThread) arg);
		
			} catch (FileNotFoundException e) {
				interrupt();
			}
			
		}
		
		else if (updated)
			((ClientThread)arg).restoreSituation(); //ask Controller updated info for new View
	}
	
	/**
	 * Checks if player disconnected while in queue has reconnected
	 * @param arg ClientThread of some player
	 * @return if player is already in queue
	 */
	private boolean inQueueCheck(ClientThread arg) {
		
		for (ClientThread th: playersInQueue) 
			if (th.IDcode.contentEquals(arg.IDcode)) {
				playersInQueue.remove(th);
				playersInQueue.add(arg);
				return true;
			}
		
		return false;
		
	}

	/**
	 * Looks for a ClientThread with same token among the ClientThreads in all Rooms. If finds any, will swap it and the new one (arg)
	 * @param thread ClientThread of some player
	 * @return if the ClientThread swap was performed
	 */
	private boolean updateView(ClientThread thread) {
		
		for (Room room: roomHandler) {
			
			System.out.println(room.getViews());
			
			for (ClientThread client: room.getViews()) {
				
				if (client.IDcode.contentEquals(thread.IDcode)) {
					
					room.getController().removeView(client.getClientName(), client);
					System.out.println("\nOld view:"+client.toString());
					client.stopClient();
					room.getController().addView(thread.getClientName(),  thread );
					thread.addObserver(room.getController()); //Controller has new reference
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
				interrupt();
			}
			
			
		
			
		}
	}
	
	public ArrayList<ClientThread> getPlayersInQueue() {
		return playersInQueue;
	}
	
	public ArrayList<Room> getRooms(){
		return roomHandler;
	}
	
	/**
	 * Works as a timer for queue. As it runs, if there are enough players in game will creare a new Room
	 * @author Pietro Grotti
	 *
	 */
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
					interrupt();
					
				}
			}
				
		}
		
	}
	
	/**
	 * Checks if there are inactive Room and deletes them.
	 * @author Pietro Grotti
	 *
	 */
	private class Updater extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("\n\nUpdating routine...\n\n");
			
			ArrayList<Room> toDelete= new ArrayList<Room>();
			
			for (Room room: roomHandler) //inactive room's selection
				if (!room.active) 
					toDelete.add(room);
			
			for (Room room: toDelete) //inactive room's deletion
				roomHandler.remove(room);
			
			System.out.println("roomHandler "+ roomHandler);
				
		}
		
	}


	
}
		


