package it.polimi.ingsw.ps29.view_client;

public class ConnectionFactory extends AbstractFactory {

	/*@Override
	Input getInput(String input) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	Connection getNetworking(String networking) {

		if(networking.equals("Socket")) {
			return new SocketConnection();
		} else if (networking.equals("RMI")) {
			return new RMIConnection();
		}
		return null;
	}

}
