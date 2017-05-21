package it.polimi.ingsw.ps29.view;

public class InputoutputFactory { 
	
	public InputOutput getInput (String inputOutputType) {
		
		if(inputOutputType.equals("CLI")) 
			return new InputOutputCLI ();
		else if (inputOutputType.equals("GUI"))
			return new InputOutputGUI ();
		return new InputOutputCLI();
	}

}
