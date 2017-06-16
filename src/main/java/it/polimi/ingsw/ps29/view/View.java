package it.polimi.ingsw.ps29.view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.BonusChoice;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.InfoForView;
import it.polimi.ingsw.ps29.view.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.view.messages.VaticanChoice;
import it.polimi.ingsw.ps29.viewclient.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.viewclient.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.viewclient.DTO.PersonalBonusTileDTO;

public class View extends Observable implements Observer {
	
	private InputOutput inputOutput;
	private InputoutputFactory inputOutputFactory = new InputoutputFactory ();
	private String namePlayer;
	private GameBoardDTO gameBoardDTO;
	private HashMap <String, PersonalBoardDTO> personalBoardsDTO;
	PersonalBonusTileDTO tileDTO = new PersonalBonusTileDTO(""); //da cambiare poi
	
	public View (String inputType, String name) {
		
		inputOutput = inputOutputFactory.getInput(inputType);
		namePlayer = name;
		gameBoardDTO = new GameBoardDTO();
		personalBoardsDTO = new HashMap <String, PersonalBoardDTO> ();
		personalBoardsDTO.put(name, new PersonalBoardDTO(name, tileDTO));
		
	}
	
	public String getName(){
		return namePlayer;
	}
	
	public void askNextAction (ActionChoice msg) {
		System.out.println("\n--> sono la view del player: "+namePlayer+"\n");
		int[] temp = inputOutput.askTypeOfAction();
		msg.setChoice(0, temp[0]);
		msg.setChoice(1, temp[1]);
		if (temp[0]!=12)
			msg.setChoice(2, inputOutput.askNumberOfServants());
		msg.setChoice(3, inputOutput.askFamiliarColor());
		
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

	
	public void askAboutExcommunication (VaticanChoice msg) {
		int choice = inputOutput.askAboutExcommunication();
		msg.setSustain(choice==1);
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
	
	
	public void handleInfo (InfoForView msg) {
		InfoForView info = (InfoForView)msg;
		if(info.familiar>0 && info.familiar<5)
			gameBoardDTO.insertFamiliar(msg);
		
		if(personalBoardsDTO.get(info.getName())==null)
			personalBoardsDTO.put(info.getName(), new PersonalBoardDTO(info.getName(), tileDTO));
		
		personalBoardsDTO.get(info.getName()).setResources(info.resources);
		inputOutput.showInfo(gameBoardDTO, personalBoardsDTO);
	}

	
	
}
