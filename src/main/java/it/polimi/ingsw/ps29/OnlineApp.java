package it.polimi.ingsw.ps29;

import java.util.Scanner;

import it.polimi.ingsw.ps29.view.View;
import it.polimi.ingsw.ps29.viewclient.Client;

/**
 * App's client-side entry point.
 * @author Pietro Grotti
 * @author Pietro Melzi
 * @author Giovanni Mele
 *
 */


public class OnlineApp {
	 
	public static void main (String[] args) {
	    	
	    	Scanner scanner = new Scanner (System.in);
	    	String inputChoice;
	    	String inputConnection;
	    	String playerName;
	    	System.out.println("              -------------------------------------------------------------- ");
	    	System.out.println("             |                                                              |");
	    	System.out.println("             |                     Lorenzo il Magnifico                     |");
	    	System.out.println("             |                                                              |");
	    	System.out.println("             |          A LONG LONG TIME AGO, IN MEDIEVAL TIMES...          |");
	    	System.out.println("             |                                                              |");
	    	System.out.println("              -------------------------------------------------------------- ");
	    	
	    	/*do { 
	    		System.out.println("\nChoose the interface you want to use between CLI and GUI.");
		    	System.out.println("Type 'CLI' or 'GUI' and press ENTER: ");
	    		inputChoice = scanner.nextLine().toLowerCase();
	    	} while(!inputChoice.equals("cli") && !inputChoice.equals("gui"));
	    	
	    	
	    	do { 
	    		System.out.println("Choose the connection you want to use between Socket and RMI.");
		    	System.out.println("Type 'socket' or 'RMI' and press ENTER: ");
		    	inputConnection = scanner.nextLine().toLowerCase();
	    	} while(!inputConnection.equals("socket") && !inputConnection.equals("rmi")); 
	    	*/
	    	
	    	inputConnection= "rmi";
	    	inputChoice= "cli";
	    
	    	
	    	System.out.println("\nInsert your username: ");
    		playerName= scanner.next();
    		
    		View view = new View (inputChoice,  playerName);
    		Client client = new Client (view, inputConnection);
			
    		
	 }
}
