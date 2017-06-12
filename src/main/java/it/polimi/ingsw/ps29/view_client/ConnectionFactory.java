package it.polimi.ingsw.ps29.view_client;

import java.io.IOException;

public class ConnectionFactory extends AbstractFactory {

	/*@Override
	Input getInput(String input) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	Connection getNetworking(String networking) {

		if(networking.equals("Socket")) {
			try {
				return new SocketConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (networking.equals("RMI")) {
			return new RMIConnection();
		}
		return null;
	}

}
