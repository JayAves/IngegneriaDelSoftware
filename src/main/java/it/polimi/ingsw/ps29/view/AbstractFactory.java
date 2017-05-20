package it.polimi.ingsw.ps29.view;

public abstract class AbstractFactory {
	
	abstract Input getInput (String input);
	abstract Networking getNetworking (String networking);

}
