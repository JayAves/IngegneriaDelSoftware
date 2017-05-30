package it.polimi.ingsw.ps29;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.view.View;

/**
 * Hello world!
 *
 */
public class App 
{ 
	
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner (System.in);
    	String inputChoice;
    	int playerNumber = 2;
    	ArrayList<String> playersName = new ArrayList<String> ();
    	Match model;
    	Controller controller;
    	
    	System.out.println("[CLI-GUI]");
    	
    	do { inputChoice = scanner.nextLine();
    	} while(!inputChoice.equals("CLI") && !inputChoice.equals("GUI"));
    
    	do {
    		System.out.println("\nNumber of player: ");
    		playerNumber = scanner.nextInt();
    	} while (playerNumber<2 || playerNumber>4);
    	
    	for(int i=0; i<playerNumber; i++) {
    		System.out.println("\nPlayer name: ");
    		playersName.add( scanner.next());
    	}
    	
    	model = new Match (createPlayers (playersName));
    	controller = new Controller (model);
    	
    	for (String name: playersName) {
    		controller.addView(new View (inputChoice,  name), name);
    	}
    	
    	
    	
    	/*Client client = new Client ("CLI", "Socket");
		GameEngine gameEngine = new GameEngine ();
		Controller controller = new Controller (gameEngine);
		client.getInput().addObserver(controller);
		client.getInput().run();*/
    }
    
    private static ArrayList<Player> createPlayers (ArrayList<String> names) {
    	ArrayList<Player> players = new ArrayList<Player> ();
    	Color[] colors = Color.values();
    	for(int i=0; i<names.size(); i++) {
    		players.add(new Player(names.get(i), colors[i], null));
    	}
    	return players;
    }
}
