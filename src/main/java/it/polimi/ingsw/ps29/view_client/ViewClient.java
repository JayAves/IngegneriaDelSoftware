package it.polimi.ingsw.ps29.view_client;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.DTO.InfoDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.BonusPlacementEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.view.InputOutput;
import it.polimi.ingsw.ps29.view.InputoutputFactory;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;

public class ViewClient extends Observable implements Observer {
	
	private InputOutput inputOutput;
	private InputoutputFactory inputOutputFactory = new InputoutputFactory ();
	private String namePlayer;
	
	public ViewClient (String inputType, String n) {
		
		inputOutput = inputOutputFactory.getInput(inputType);
		namePlayer = n;
	}
	
	public void askNextAction () {
		
		int [] choice = new int [4];
		int[] temp = inputOutput.askTypeOfAction();
		choice[0] = temp[0];
		choice[1] = temp[1];
		if (temp[0]!=12){
			choice[2] = inputOutput.askNumberOfServants();
			choice[3] = inputOutput.askFamiliarColor();
		}
/*(p)		ActionChoice move = new ActionChoice(namePlayer, choice);
		setChanged();
		notifyObservers(move);*/
	}
	
	public void askBonusAction (BonusActionEffect effect) {
		ActionChoice move;
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
/*(P)		move = new ActionChoice(namePlayer, userChoice);
		setChanged();
		notifyObservers(move);*/
	}
	
	public void askAboutExchange (ExchangeResourcesEffect effect) {
		setChanged();
//(P)		notifyObservers(inputOutput.askExchange(effect));
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public void showBoard(InfoDTO infoForView) {
		inputOutput.showUpdatedSituation(namePlayer, infoForView);
	}
	public String getName(){
		return namePlayer;
	}
}
