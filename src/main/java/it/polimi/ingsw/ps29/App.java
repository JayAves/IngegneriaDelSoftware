package it.polimi.ingsw.ps29;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.ps29.controller.Controller;
import it.polimi.ingsw.ps29.model.game.Match;
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
    	int playerNumber;
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
    	
    	model = new Match (playersName);
    	
    	
    	controller = new Controller (model);
    	
    	for (String name: playersName) {
    		View view = new View (inputChoice,  name);
    		view.addObserver(controller);
    		//controller.addView(view, name);   		
    	}
    	
    	model.addObserver(controller);
    	model.gameEngine();
    	//while state!= fine partita
    	//controller.callCorrectView();
    	
    	
		
    	scanner.close();
    }
    
    
}
