package it.polimi.ingsw.ps29.server;

public class ServerMain {

	
	
	public static void main( String[] args ){
		
		RoomCreator creator= new RoomCreator();
		creator.start();
		SocketGathererInServer socketGatherer = new SocketGathererInServer(9001);
		socketGatherer.addObserver(creator);
		
		//RMIGathererInServer rmiGatherer= new RMIGathererInServer();
		//rmiGatherer.addObserver(creator);
		
		socketGatherer.startServer();
		
	}
}
