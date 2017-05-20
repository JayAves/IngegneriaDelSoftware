package it.polimi.ingsw.ps29.view;

public class NetworkingFactory extends AbstractFactory {

	@Override
	Input getInput(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Networking getNetworking(String networking) {

		if(networking.equals("Socket")) {
			return new SocketNetworking();
		} else if (networking.equals("RMI")) {
			return new RMINetworking();
		}
		return null;
	}

}
