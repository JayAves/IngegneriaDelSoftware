package it.polimi.ingsw.ps29.view_client;

import java.io.IOException;

public interface Connection {
	
	public void connect(String hostname) throws IOException;
}
