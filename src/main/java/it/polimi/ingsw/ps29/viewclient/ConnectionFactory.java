package it.polimi.ingsw.ps29.viewclient;

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
		
			return new RmiConnection(playerName);
		}
		return null;
	}

}
