package it.polimi.ingsw.ps29.viewclient;

public abstract class AbstractFactory {
	
	
	
	//abstract Input getInput (String input);
	abstract Connection getNetworking (String networking, String playerName);

}
