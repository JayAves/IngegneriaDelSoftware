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
import it.polimi.ingsw.ps29.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.VaticanChoice;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

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
		
		inputOutput = inputOutputFactory.getInput(inputType);
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
		msg = inputOutput.handleAskNextAction (msg);
		setChanged();
		notifyObservers(msg);
	}
	
	
	public void askBonusAction (BonusChoice msg) {
		msg = inputOutput.handleBonusAction (msg);
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
		//1 per sostenere, 0 per non sostenere
		//la condizione choice==1 traduce la scelta in un boolean
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
		//DA RIVEDERE QUANDO SI INTRODUCE LA GUI
		
		InfoForView info = (InfoForView)msg;
		CardDTO takenCard;
		
		//se ho piazzato un familiare lo aggiungo alla board
		if(info.familiar>0 && info.familiar<5)
			gameBoardDTO.insertFamiliar(msg);
		
		//all'inizio: se non ho ancora memorizzato una personal board la creo
		for(String name: info.resSituation.keySet())
			if(personalBoardsDTO.get(name)==null)
				personalBoardsDTO.put(name, new PersonalBoardDTO(name, null));
		
		//se ho preso una carta la tolgo dalla torre e la metto nella personal board
		if(info.space>2 && info.space<7) {
			takenCard = towersDTO.takeCard(info.space, info.floor);
			personalBoardsDTO.get(info.getName()).insertCard(takenCard);
		}
		
		//aggiorno le risorse e stampo
		for (HashMap.Entry <String, ArrayList<ResourceDTO>> resSituation: info.resSituation.entrySet())
			personalBoardsDTO.get(resSituation.getKey()).setResources(resSituation.getValue());
		
		inputOutput.showInfo(gameBoardDTO, towersDTO, personalBoardsDTO);
	}

	public void showTowersAndDices (TowersAndDicesForView msg) {
		towersDTO = msg.getTowers();
		dices = msg.getDices();
		inputOutput.showTowerAndDices (msg);
	}
	
	public void showMessage(String message) {
			inputOutput.showMessage(message);
	}
	
	public void showInitialInfo (FirstBoardInfo msg) {
		//setto le tile su ogni board
		for(HashMap.Entry<String, PersonalBonusTileDTO> tile: msg.getTiles().entrySet())
			if(personalBoardsDTO.get(tile.getKey())==null)
				personalBoardsDTO.put(tile.getKey(), new PersonalBoardDTO(tile.getKey(), tile.getValue()));
		exCards = msg.getExCards();
		inputOutput.showFirstInfo (msg);
		showTowersAndDices(msg.getTowers());
	}
	
}
