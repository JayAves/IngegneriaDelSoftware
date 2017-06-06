package it.polimi.ingsw.ps29.view;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.BonusPlacementEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.view.usermessages.UserChoice;

public class View extends Observable implements Observer {
	
	private InputOutput inputOutput;
	private InputoutputFactory inputOutputFactory = new InputoutputFactory ();
	private String namePlayer;
	
	public View (String inputType, String n) {
		
		inputOutput = inputOutputFactory.getInput(inputType);
		namePlayer = n;
	}
	
	public void askNextAction () {
		System.out.println("\n--> sono la view del player: "+namePlayer+"\n");
		int [] choice = new int [4];
		int[] temp = inputOutput.askTypeOfAction();
		choice[0] = temp[0];
		choice[1] = temp[1];
		if (temp[0]!=12){
			choice[2] = inputOutput.askNumberOfServants();
			choice[3] = inputOutput.askFamiliarColor();
		}
		UserChoice move = new UserChoice(namePlayer, choice);
		setChanged();
		notifyObservers(move);
	}
	
	public void askBonusAction (BonusActionEffect effect) {
		UserChoice move;
		int[] userChoice = new int[4];
		userChoice[1] = 0;
		
		switch(effect.getType()) {
			case "harvest":
				userChoice[0] = 1;
				break;
			case "production":
				userChoice[0] = 2;
				break;
			case "territory":
				userChoice[0] = 3;
				break;
			case "building":
				userChoice[0] = 4;
				break;
			case "character":
				userChoice[0] = 5;
				break;
			case "venture":
				userChoice[0] = 6;
				break;
			default:
				userChoice[0] = 0;
				break;
			
		}
		
		System.out.println("\nBonus action value: "+effect.getValue());
		System.out.println("\nType of action: "+effect.getType());
		if(effect instanceof BonusPlacementEffect){
			System.out.println("\nDicount:\n");
			for (Resource res: ((BonusPlacementEffect) effect).getDiscount())
				System.out.println(res);
			userChoice[1] = inputOutput.askFloor();
		}
		
		userChoice [2] = inputOutput.askNumberOfServants();
		userChoice[3] = -1;
		move = new UserChoice(namePlayer, userChoice);
		setChanged();
		notifyObservers(move);
	}
	
	public void askAboutExchange (ExchangeResourcesEffect effect) {
		setChanged();
		notifyObservers(inputOutput.askExchange(effect));
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
