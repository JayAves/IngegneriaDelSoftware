/*package it.polimi.ingsw.ps29;


import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.server.RoomCreator;
import it.polimi.ingsw.ps29.server.SocketGatherer;
import it.polimi.ingsw.ps29.view.View;
import it.polimi.ingsw.ps29.viewclient.Client;
import it.polimi.ingsw.ps29.viewclient.SocketConnection;
import it.polimi.ingsw.ps29.viewclient.SocketConnectionForTest;
import junit.framework.TestCase;

public class SocketConnectionTest extends TestCase {

	
	it.polimi.ingsw.ps29.viewclient.Client client1;
	SocketGatherer server;
	RoomCreator queue;
	String inputChoice = "CLI";
	String inputConnection = "Socket";
	
	public SocketConnectionTest (String testName) {
		super (testName);
	}
	

	@BeforeClass
	public void setUp () {

		queue = new RoomCreator();
		server= new SocketGatherer(9003, 60000);
		server.addObserver(queue);
		Thread t= new Thread(server);
		t.start();
		
		
	}
	
	@Test
	public void test() {
		
		String playerName1 = "primo";
		SocketConnectionForTest socket1= new SocketConnectionForTest(playerName1);
		String playerName2 = "secondo";
		SocketConnectionForTest socket2= new SocketConnectionForTest(playerName2);
		
		assertTrue(socket1 != null);
		assertTrue(socket2!=null);
		
		assertTrue(server.getClients().size()!= 0);
		assertTrue(queue.getPlayersInQueue().size()!=0);
		
		
		//assertTrue((server.getClients().size()==4)||(server.getClients().size()==0));
		//assertTrue(!queue.getRooms().isEmpty());
		
		
		//server.endOfConnection();
		//Room room= queue.getRooms().get(0);
		
	}
	

}*/
