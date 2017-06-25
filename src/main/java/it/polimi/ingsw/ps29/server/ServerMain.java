package it.polimi.ingsw.ps29.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.GsonBuilder;

public class ServerMain {

	
	
	public static void main( String[] args ) throws FileNotFoundException{
		
		BufferedReader btimer = new BufferedReader(new FileReader("src/main/java/timer.json"));
	    GsonBuilder gtimer = new GsonBuilder();
	    TimerJson timer = gtimer.create().fromJson(btimer, TimerJson.class);	
	    System.out.println(timer.roomTimer);
	    System.out.println(timer.turnTimer);
		
		RoomCreator creator= new RoomCreator();
		creator.setPeriod(timer.roomTimer);
		creator.start();
		SocketGatherer socketGatherer = new SocketGatherer(9001, timer.turnTimer);
		socketGatherer.addObserver(creator);
		
		RMIGatherer rmiGatherer= new RMIGatherer(timer.turnTimer);
		rmiGatherer.addObserver(creator);
		
		rmiGatherer.startServer();
		socketGatherer.startServer();
		
		
	}
}
