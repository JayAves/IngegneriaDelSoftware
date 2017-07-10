package it.polimi.ingsw.ps29.viewclient;

import java.io.IOException;

/**
 * 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */

public class ConnectionFactory {

	
	/**
	 * Establishes a connection to server, using the technology specified
	 * @author Pietro Melzi
	 * @author Pietro Grotti
	 * @author Giovanni Mele
	 * @param networking	 is the connection technology wanted
	 * @param playerName	name to be sent to server
	 * @return a working connection with server
	 */
	
	Connection getNetworking(String networking, String playerName) {

		if(networking.equals("socket")) {
			return new SocketConnection(playerName);
		} 
		
		else if (networking.equals("rmi")) {
		
			try {
				return new RmiConnection(playerName);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Failed to setup connection");
				Thread.currentThread().interrupt();
			}
		}
		return null;
	}

}
