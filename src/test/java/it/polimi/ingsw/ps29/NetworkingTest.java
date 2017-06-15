package it.polimi.ingsw.ps29;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.ps29.server.RoomCreator;
import it.polimi.ingsw.ps29.server.ServerMain;
import it.polimi.ingsw.ps29.server.SocketGathererInServer;
import junit.framework.TestCase;

public class NetworkingTest extends TestCase {
	
	public NetworkingTest (String testName) {
		super (testName);
	}
	
	@BeforeClass
	public void setUp () {
		String[] args = new String[2];
		ServerMain.main(args);
		
	}
	
	@Test
	public void test() {
		
	}
	

}
