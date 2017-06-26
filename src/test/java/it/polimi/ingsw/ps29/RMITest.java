package it.polimi.ingsw.ps29;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.server.RMIGatherer;
import it.polimi.ingsw.ps29.server.RoomCreator;
import it.polimi.ingsw.ps29.viewclient.RmiConnection;
import junit.framework.TestCase;

public class RMITest extends TestCase {

	public RMITest(String testName) {
		super(testName);
	}
	
	RoomCreator creator;
	RMIGatherer rmiGatherer;
	
	@BeforeClass
	public void setUp () {

		creator= new RoomCreator();
		rmiGatherer= new RMIGatherer(100000);
		rmiGatherer.addObserver(creator);
		Thread t= new Thread(rmiGatherer);
		t.start();
		
	}
	
	@Test
	public void test() throws IOException {
		
		creator.start();
		
		
		/*String playerName= "Player";
		RmiConnection connection1= new RmiConnection(playerName);
		assertTrue(connection1!=null);
		connection1.run();
		
	
		assertTrue(!creator.getPlayersInQueue().isEmpty());
		connection1.setEnd();*/
		
	}
	
	
	
	
}
