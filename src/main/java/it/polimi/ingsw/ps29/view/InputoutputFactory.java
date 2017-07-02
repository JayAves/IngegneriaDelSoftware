package it.polimi.ingsw.ps29.view;

import it.polimi.ingsw.ps29.view.GUI.InputOutputGUI;
import it.polimi.ingsw.ps29.view.inputCLI.InputOutputCLI;

/**
 * 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class InputoutputFactory { 
	
	public InputOutput getInput (String inputOutputType, String name) {
		
		if(inputOutputType.equals("CLI")) 
			return new InputOutputCLI ();
		else if (inputOutputType.equals("GUI"))
			return new InputOutputGUI (name);
		return new InputOutputCLI();
	}

}
