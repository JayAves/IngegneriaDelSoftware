package it.polimi.ingsw.ps29.viewclient;

import java.io.IOException;

public class ConnectionFactory extends AbstractFactory {

	/*@Override
	Input getInput(String input) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	Connection getNetworking(String networking, String playerName) {

		if(networking.equals("Socket")) {
			return new SocketConnection(playerName);
		} 
		
		else if (networking.equals("RMI")) {
		
			try {
				return new RmiConnection(playerName);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
