package it.polimi.ingsw.ps29.viewclient;

import java.util.Scanner;

import it.polimi.ingsw.ps29.view.View;



public class OnlineApp {
	 
	public static void main( String[] args ) {
	    	
	    	Scanner scanner = new Scanner (System.in);
	    	String inputChoice;
	    	String inputConnection;
	    	String playerName;
	    	
	    	
	    	/*System.out.println("[CLI-GUI]");
	    	
	    	do { inputChoice = scanner.nextLine();
	    	} while(!inputChoice.equals("CLI") && !inputChoice.equals("GUI"));
	    	
	    	System.out.println("\nSocket o RMI?");
	    	
	    	do { inputConnection = scanner.nextLine();
	    	} while(!inputConnection.equals("Socket") && !inputConnection.equals("RMI"));*/
	    	
	    
<<<<<<< HEAD
	    	inputChoice = "CLI";	    			
	    	inputConnection = "RMI";
=======
	    	inputChoice = "CLI";
	    	inputConnection = "Socket";
>>>>>>> 049691f7e0af9c29a6d8e9d14b0f8890debceee9
	    	
	    	System.out.println("\nPlayer name: ");
    		playerName= scanner.next();
    		
    		View view = new View (inputChoice,  playerName);
    		
    		Client client = new Client (view, inputConnection);
			view.addObserver(client);
    		
    		//System.out.println("\nWaiting for a match");
    		
    		
	 }
}
