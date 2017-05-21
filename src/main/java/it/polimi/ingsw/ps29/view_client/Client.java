/*package it.polimi.ingsw.ps29.view_client;

import com.sun.xml.internal.stream.buffer.AbstractCreator;

public class Client {
	
	private Input input;
	private Networking networking;
	
	public Client (String in, String net) {
		AbstractFactory [] creator = new AbstractFactory [2];
		creator[0] = new InputFactory ();
		creator[1] = new NetworkingFactory();
		
		input = creator[0].getInput(in);
		networking = creator[1].getNetworking(net);
		
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

}
*/