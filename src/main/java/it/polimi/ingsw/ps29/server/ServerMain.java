package it.polimi.ingsw.ps29.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.GsonBuilder;

public class ServerMain {

	
	
	public static void main( String[] args ) throws FileNotFoundException{
		
		/*BufferedReader btimer = new BufferedReader(new FileReader("src/main/java/timer.json"));
	    GsonBuilder gtimer = new GsonBuilder();
	    TimerJson timer = gtimer.create().fromJson(btimer, TimerJson.class);
	    System.out.println(timer.getRoomTimer());
	    System.out.println(timer.getTurnTimer());*/
		TimerJson timer= new TimerJson();
		timer.roomTimer= 10000;
		timer.turnTimer=30000;
		
		RoomCreator creator= new RoomCreator();
		creator.start();
		SocketGatherer socketGatherer = new SocketGatherer(9001);
		socketGatherer.addObserver(creator);
		
		RMIGatherer rmiGatherer= new RMIGatherer();
		rmiGatherer.addObserver(creator);
		
		rmiGatherer.startServer();
		socketGatherer.startServer();
		
		
	}
}
