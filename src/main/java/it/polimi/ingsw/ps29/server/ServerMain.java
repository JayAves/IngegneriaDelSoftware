package it.polimi.ingsw.ps29.server;

public class ServerMain {

	
	
	public static void main( String[] args ){
		
		RoomCreator creator= new RoomCreator();
		creator.run();
		SocketGathererInServer socketGatherer = new SocketGathererInServer();
		socketGatherer.startServer();
		RMIGathererInServer rmiGatherer= new RMIGathererInServer();
		socketGatherer.addObserver(creator);
		rmiGatherer.addObserver(creator);
		
		
		
	
		
	}
}
