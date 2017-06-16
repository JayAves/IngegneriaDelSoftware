package it.polimi.ingsw.ps29.server;

public class ServerMain {

	
	
	public static void main( String[] args ){
		
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
