package it.polimi.ingsw.ps29.view_client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



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
	    	} while(!inputChoice.equals("Socket") && !inputChoice.equals("RMI"));
	    	
	    	
	    	System.out.println("\nPlayer name: ");
    		playerName= scanner.next();
    		
    		ViewClient view = new ViewClient (inputChoice,  playerName);
    		
    		try {
    			
        		Client client = new Client (view, inputConnection);
        		view.addObserver(client);
    		
        	} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
	 
    		scanner.close();
	 
	 }
}
