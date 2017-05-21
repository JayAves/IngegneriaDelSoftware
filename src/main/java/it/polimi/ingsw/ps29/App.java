package it.polimi.ingsw.ps29;

import java.util.Scanner;

import it.polimi.ingsw.ps29.view.View;

/**
 * Hello world!
 *
 */
public class App 
{
	static Scanner scanner; 
	
    public static void main( String[] args )
    {
    	scanner = new Scanner (System.in);
    	String inputChoice;
    	System.out.println("[CLI-GUI]");
    	
    	do { inputChoice = scanner.nextLine();
    	} while(!inputChoice.equals("CLI") && !inputChoice.equals("GUI"));
    	
    	View view = new View (inputChoice);
    	/*Client client = new Client ("CLI", "Socket");
		GameEngine gameEngine = new GameEngine ();
		Controller controller = new Controller (gameEngine);
		client.getInput().addObserver(controller);
		client.getInput().run();*/
    }
}
