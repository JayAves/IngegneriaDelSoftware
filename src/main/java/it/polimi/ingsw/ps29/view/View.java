package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.ExcommunicationCardDTO;
import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBonusTileDTO;
import it.polimi.ingsw.ps29.DTO.ResourceDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.messages.RestoreSituation;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.VaticanChoice;
import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

/**
 * Displays all to info to user, catches and notifies any input from user.
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */

public class View extends Observable implements Observer {
	
	private InputOutput inputOutput;
	private InputoutputFactory inputOutputFactory = new InputoutputFactory ();
	private String namePlayer;
	private GameBoardDTO gameBoardDTO;
	private TowersDTO towersDTO;
	private HashMap <String, PersonalBoardDTO> personalBoardsDTO;
	private ArrayList<ExcommunicationCardDTO> exCards;
	private int[] dices;
	
	public View (String inputType, String name) {
		
		inputOutput = inputOutputFactory.getInput(inputType, name);
		namePlayer = name;
		gameBoardDTO = new GameBoardDTO();
		towersDTO = new TowersDTO();
		personalBoardsDTO = new HashMap <String, PersonalBoardDTO> ();
		personalBoardsDTO.put(name, new PersonalBoardDTO(name, null));
		exCards = new ArrayList<ExcommunicationCardDTO>();
		dices = new int[3];
		
	}
	
	public String getName(){
		return namePlayer;
	}
	
	public void askNextAction (ActionChoice msg) {
		try {
			msg = inputOutput.handleAskNextAction (msg);
			setChanged();
			notifyObservers(msg);
		} catch (ExpiredTimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void askBonusAction (BonusChoice msg) {
		try {
			msg = inputOutput.handleBonusAction (msg);
			setChanged();
			notifyObservers(msg);
		} catch (ExpiredTimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void askAboutExchange (Exchange msg) {
		try {
			msg = inputOutput.askExchange(msg);
			setChanged();
			notifyObservers(msg);
		} catch (ExpiredTimeException e) {
			e.getMessage();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

	
	public void askAboutExcommunication (VaticanChoice msg) {
		try {
			int choice = inputOutput.askAboutExcommunication();
			//1 to sustain Church, 0 otherwise
			
			msg.setSustain(choice==1);
			setChanged();
			notifyObservers(msg);
		} catch (ExpiredTimeException e) {
			e.getMessage();
		}
	}
	
	public void askAboutPrivileges (PrivilegeChoice msg) {
		try {
			ResourceType type;
			while(msg.getChoices().size()<msg.getPrvilieges()){
				type = inputOutput.askSpecificPrivilege();
				if(!msg.isIn(type))
					msg.getChoices().add(type);
			}
			setChanged();
			notifyObservers(msg);
		
		} catch (ExpiredTimeException e) {
			e.getMessage();
		}
	}
	
	
	public void handleInfo (InfoForView msg) {
		
		
		InfoForView info = (InfoForView)msg;
		CardDTO takenCard;
		
		//if familiar is placed, board is updated
		if(info.familiar>0 && info.familiar<5)
			gameBoardDTO.insertFamiliar(msg);
		
		
		//at the beginning: if no personal board is found, it is created
		for(String name: info.resSituation.keySet())
			if(personalBoardsDTO.get(name)==null)
				personalBoardsDTO.put(name, new PersonalBoardDTO(name, null));
		
		
		// if a card was taken, must be took off the tower and  put in personal board.
		if(info.space>2 && info.space<7) {
			takenCard = towersDTO.takeCard(info.space, info.floor);
			personalBoardsDTO.get(info.getName()).insertCard(takenCard);
		}
		
		//update resources and show
		for (HashMap.Entry <String, ArrayList<ResourceDTO>> resSituation: info.resSituation.entrySet())
			personalBoardsDTO.get(resSituation.getKey()).setResources(resSituation.getValue());
		
		inputOutput.showInfo(msg, gameBoardDTO, towersDTO, personalBoardsDTO);
	}

	public void showTowersAndDices (TowersAndDicesForView msg) {
		towersDTO = msg.getTowers();
		dices = msg.getDices();
		inputOutput.showTowerAndDices (msg);
		gameBoardDTO.cleanSpace();
	}
	
	
	public void showMessage(InteractionMessage message) {

			inputOutput.showMessage(message);
				
	}
	
	public void showInitialInfo (FirstBoardInfo msg) {
		
		//every board needsits tile
		for(HashMap.Entry<String, PersonalBonusTileDTO> tile: msg.getTiles().entrySet())
			if(personalBoardsDTO.get(tile.getKey()) == null)
				personalBoardsDTO.put(tile.getKey(), new PersonalBoardDTO(tile.getKey(), tile.getValue()));
			else
				personalBoardsDTO.replace(tile.getKey(), new PersonalBoardDTO(tile.getKey(), tile.getValue()));
		
		exCards = msg.getExCards();
		inputOutput.showFirstInfo (msg);
		showTowersAndDices(msg.getTowers());
	}
	
	public void restoreSituation (RestoreSituation msg) {
		showInitialInfo(msg.getFirstInfo());
		gameBoardDTO = msg.getGameBoard();
		for (PersonalBoardDTO pBoard: msg.getPersonalBoard())
			if(personalBoardsDTO.get(pBoard.getName())==null)
				personalBoardsDTO.put(pBoard.getName(), pBoard);
			else
				personalBoardsDTO.replace(pBoard.getName(), pBoard);
		
	}
	
	public InputOutput getInputOutput() {
		return inputOutput;
	}
}
