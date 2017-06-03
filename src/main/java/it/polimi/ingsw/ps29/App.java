package it.polimi.ingsw.ps29;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.cards.customadapters.ResourceAdapter;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.PersonalBonusTile;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.view.View;

/**
 * Hello world!
 *
 */
public class App 
{ 
	
    public static void main( String[] args ) throws FileNotFoundException{
    	
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
    		View view = new View (inputChoice,  name);
    		view.addObserver(controller);
    		controller.addView(view, name);   		
    	}
    	
    	model.addObserver(controller);
    	//while state!= fine partita
    	controller.callCorrectView();
    	
    	/*Client client = new Client ("CLI", "Socket");
		GameEngine gameEngine = new GameEngine ();
		Controller controller = new Controller (gameEngine);
		client.getInput().addObserver(controller);
		client.getInput().run();*/
    	scanner.close();
    }
    
    private static ArrayList<Player> createPlayers (ArrayList<String> names) throws FileNotFoundException {
    	ArrayList<Player> players = new ArrayList<Player> ();
    	
    	Color[] colors = Color.values();
    	
    	BufferedReader tiles = new BufferedReader(new FileReader("src/main/java/personalbonustile.json"));
	    GsonBuilder gtiles = new GsonBuilder();
	    
	    gtiles.registerTypeAdapter(Resource.class, new ResourceAdapter());
	    PersonalBonusTile[] tilez = gtiles.create().fromJson(tiles, PersonalBonusTile[].class);
    	
	    
    	for(int i=0; i<names.size(); i++) {
    		int rnd= new Random().nextInt(tilez.length);
    		players.add(new Player(names.get(i), colors[i], tilez[rnd])); // assegno una bonusTile casuale
    	}
    	
    	int startingCoins=5; //distribuisco le monete iniziali
    	
    	for (int i=0; i<players.size(); i++) {
    		players.get(i).getPersonalBoard().getResources().updateResource(new Resource("coin",startingCoins));
    		startingCoins++;
    	}
    	return players;
    }
}
