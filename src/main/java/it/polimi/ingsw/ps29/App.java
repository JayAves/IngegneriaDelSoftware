package it.polimi.ingsw.ps29;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.game.GameEngine;
import it.polimi.ingsw.ps29.view_client.Client;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Client client = new Client ("CLI", "Socket");
		GameEngine gameEngine = new GameEngine ();
		Controller controller = new Controller (gameEngine);
		client.getInput().addObserver(controller);
		client.getInput().run();
    }
}
