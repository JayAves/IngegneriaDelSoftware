package it.polimi.ingsw.ps29.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.GsonBuilder;

/**
 * Entry point to turn the Server on
 * @author Pietro Grotti
 * 
 *
 */
public class ServerMain {

	
	public static void main( String[] args ) throws FileNotFoundException{
		
		BufferedReader btimer = new BufferedReader(new FileReader("src/main/java/Timer.json"));
	    GsonBuilder gtimer = new GsonBuilder();
	    TimerJson timer = gtimer.create().fromJson(btimer, TimerJson.class);	
	    
		RoomCreator creator= new RoomCreator();
		creator.setPeriod(timer.roomTimer);
		creator.start();
		SocketGatherer socketGatherer = new SocketGatherer(9001, timer.actionTimer);
		socketGatherer.addObserver(creator);
		
		RMIGatherer rmiGatherer= new RMIGatherer(timer.actionTimer);
		rmiGatherer.addObserver(creator);
		
		rmiGatherer.startServer();
		socketGatherer.startServer();
		
		
	}
}
