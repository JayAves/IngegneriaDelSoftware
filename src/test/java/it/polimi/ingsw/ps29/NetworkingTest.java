package it.polimi.ingsw.ps29;


import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.server.Room;
import it.polimi.ingsw.ps29.server.RoomCreator;
import it.polimi.ingsw.ps29.server.SocketGathererInServer;
import it.polimi.ingsw.ps29.view.View;
import it.polimi.ingsw.ps29.view_client.Client;
import junit.framework.TestCase;

public class NetworkingTest extends TestCase {

	
	Client client1;
	SocketGathererInServer server;
	RoomCreator queue;
	String inputChoice = "CLI";
	String inputConnection = "Socket";
	
	public NetworkingTest (String testName) {
		super (testName);
	}
	
	@BeforeClass
	public void setUp () {

		/*queue = new RoomCreator();
		server= new SocketGathererInServer(9001);
		server.addObserver(queue);
		server.startServer();*/

		String playerName1 = "primo";
		View view1 = new View (inputChoice,  playerName1);
		client1 = new Client (view1, inputConnection);
		view1.addObserver(client1);
		
		String playerName2 = "secondo";
		View view2 = new View (inputChoice,  playerName2);
		Client client2 = new Client (view2, inputConnection);
		view2.addObserver(client2);
		
		

		
		
		
	}
	
	@Test
	public void test() {
		
		
		assertTrue(client1 != null);
		//assertTrue( !server.getClients().isEmpty());
		//assertTrue(!queue.getPlayersInQueue().isEmpty());
		
		/*String playerName2 = "secondo";
		View view2 = new View (inputChoice,  playerName2);
		Client client2 = new Client (view2, inputConnection);
		view2.addObserver(client2);
		
		assertTrue(client2!=null);
		assertTrue(server.getClients().size()==2);
		assertTrue(queue.getPlayersInQueue().size()==2);
		
		String playerName3 = "terzo";
		View view3 = new View (inputChoice,  playerName3);
		Client client3 = new Client (view3, inputConnection);
		view3.addObserver(client3);
	
		assertTrue(client3!=null);
		assertTrue(server.getClients().size()==3);
		assertTrue(queue.getPlayersInQueue().size()==3);
		
		String playerName4= "quarto";
		View view4 = new View (inputChoice,  playerName4);
		Client client4 = new Client (view4, inputConnection);
		view4.addObserver(client4);
		
		
		
		assertTrue(client4!=null);
		assertTrue(server.getClients().size()==4);
		assertTrue((server.getClients().size()==4)||(server.getClients().size()==0));
		assertTrue(!queue.getRooms().isEmpty());
		
		Room room= queue.getRooms().get(0);*/
	}
	

}
