package it.polimi.ingsw.ps29.view_client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import it.polimi.ingsw.ps29.view.View;



public class OnlineApp {
	 
	public static void main( String[] args ) throws FileNotFoundException{
	    	
	    	Scanner scanner = new Scanner (System.in);
	    	String inputChoice;
	    	String inputConnection;
	    	String playerName;
	    	
	    	
	    	System.out.println("[CLI-GUI]");
	    	
	    	do { inputChoice = scanner.nextLine();
	    	} while(!inputChoice.equals("CLI") && !inputChoice.equals("GUI"));
	    	
	    	System.out.println("\nSocket o RMI?");
	    	
	    	do { inputConnection = scanner.nextLine();
	    	} while(!inputConnection.equals("Socket") && !inputConnection.equals("RMI"));
	    	
	    	
	    	System.out.println("\nPlayer name: ");
    		playerName= scanner.next();
    		
    		View view = new View (inputChoice,  playerName);
    		
    		try {
    			
        		Client client = new Client (view, inputConnection);
        		view.addObserver(client);
    		
        	} catch (IOException e) {
    			// TODO Auto-generated catch block
    			System.out.println("Could not create Client!");
    		}
	 
    		scanner.close();
	 
	 }
}
