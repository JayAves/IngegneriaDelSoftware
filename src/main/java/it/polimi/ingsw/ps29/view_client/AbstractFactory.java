package it.polimi.ingsw.ps29.view_client;

public abstract class AbstractFactory {
	
	abstract Input getInput (String input);
	abstract Networking getNetworking (String networking);

}
