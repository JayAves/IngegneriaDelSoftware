package it.polimi.ingsw.ps29.view;

public class InputFactory extends AbstractFactory {

	@Override
	Input getInput(String input) {
		
		if (input.equals("CLI")) {
			return new CLIInput();
		} else if (input.equals("GUI")) {
			return new GUIInput();
		}
		return null;
	}

	@Override
	Networking getNetworking(String networking) {
		// TODO Auto-generated method stub
		return null;
	}

}
