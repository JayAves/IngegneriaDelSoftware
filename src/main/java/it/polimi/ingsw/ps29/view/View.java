package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.model.DTO.InfoDTO;
import it.polimi.ingsw.ps29.model.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.BonusChoice;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.view.messages.VaticanChoice;

public class View extends Observable implements Observer {
	
	private InputOutput inputOutput;
	private InputoutputFactory inputOutputFactory = new InputoutputFactory ();
	private String namePlayer;
	public InfoDTO infoForView;
	
	public View (String inputType, String n) {
		
		inputOutput = inputOutputFactory.getInput(inputType);
		namePlayer = n;
		infoForView = new InfoDTO(new GameBoardDTO(),new ArrayList<PersonalBoardDTO>());
		
	}
	
	
	
	public void askNextAction (ActionChoice msg) {
		System.out.println("\n--> sono la view del player: "+namePlayer+"\n");
		int[] temp = inputOutput.askTypeOfAction();
		msg.setChoice(0, temp[0]);
		msg.setChoice(1, temp[1]);
		if (temp[0]!=12){
			msg.setChoice(2, inputOutput.askNumberOfServants());
			msg.setChoice(3, inputOutput.askFamiliarColor());
		}
		setChanged();
		notifyObservers(msg);
	}
	
	public void askBonusAction (BonusChoice msg) {
		BonusActionEffect effect = msg.getBonus();
		
		
		inputOutput.printBonusAction (effect);
		if(effect.getType().equals("territory")||effect.getType().equals("building")||
				effect.getType().equals("character")||effect.getType().equals("venture"))
			msg.setFloor(inputOutput.askFloor());
		
		msg.setServants(inputOutput.askNumberOfServants());
		setChanged();
		notifyObservers(msg);
	}
	
	public void askAboutExchange (Exchange msg) {
		msg = inputOutput.askExchange(msg);
		setChanged();
		notifyObservers(msg);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

	public void showBoard(InfoDTO infoForView) {
		this.infoForView = infoForView;
		inputOutput.showUpdatedSituation(namePlayer, infoForView);
	}
	
	public void askAboutExcommunication (VaticanChoice msg) {
		setChanged();
		notifyObservers(msg);
	}
	
	public void askAboutPrivileges (PrivilegeChoice msg) {
		ResourceType type;
		while(msg.getChoices().size()<msg.getPrvilieges()){
			type = inputOutput.askSpecificPrivilege();
			if(!msg.isIn(type))
				msg.getChoices().add(type);
		}
		setChanged();
		notifyObservers(msg);
	}
	
	
	public void startInteraction (InteractionMessage controllerMessage) {
		VisitorServerMessages visitor = new VisitorServerMessages();
		controllerMessage.receive(visitor);
	}

	
	public class VisitorServerMessages{
    	
    	public void receive (ActionChoice msg) {
			askNextAction(msg);
		}
		
    	public void receive (Exchange msg) {
			askAboutExchange(msg);
    	}
		
    	public void receive (BonusChoice msg) {
			askBonusAction(msg);
		}
    	
		public void receive(VaticanChoice msg){
			askAboutExcommunication (msg);
		}
		
		public void receive(PrivilegeChoice msg){
			askAboutPrivileges (msg);
		}
    }
}
