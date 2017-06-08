package it.polimi.ingsw.ps29.view_client;

import java.io.IOException;

public class Client {
	
	private Input input;
	private Connection networking;
	
	public Client (String in, String net) throws IOException {
		AbstractFactory [] creator = new AbstractFactory [2];
		creator[0] = new InputFactory ();
		creator[1] = new ConnectionFactory();
		
		input = creator[0].getInput(in);
		networking = creator[1].getNetworking(net);
		networking.connect("localhost");
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

}
