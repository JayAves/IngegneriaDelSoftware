package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBonusTileDTO;
import it.polimi.ingsw.ps29.DTO.ResourceDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.BonusChoice;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.InfoForView;
import it.polimi.ingsw.ps29.view.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.view.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.view.messages.VaticanChoice;

public class View extends Observable implements Observer {
	
	private InputOutput inputOutput;
	private InputoutputFactory inputOutputFactory = new InputoutputFactory ();
	private String namePlayer;
	private GameBoardDTO gameBoardDTO;
	private TowersDTO towersDTO;
	private HashMap <String, PersonalBoardDTO> personalBoardsDTO;
	PersonalBonusTileDTO tileDTO = new PersonalBonusTileDTO(""); //da cambiare poi
	
	public View (String inputType, String name) {
		
		inputOutput = inputOutputFactory.getInput(inputType);
		namePlayer = name;
		gameBoardDTO = new GameBoardDTO();
		towersDTO = new TowersDTO();
		personalBoardsDTO = new HashMap <String, PersonalBoardDTO> ();
		personalBoardsDTO.put(name, new PersonalBoardDTO(name, tileDTO));
		
	}
	
	public String getName(){
		return namePlayer;
	}
	
	public void askNextAction (ActionChoice msg) {
		System.out.println("\n"+namePlayer.toUpperCase()+", It's your turn!\n");
		int[] temp = inputOutput.askTypeOfAction();
		msg.setChoice(0, temp[0]);
		msg.setChoice(1, temp[1]);
		if (temp[0] < 12)
			msg.setChoice(2, inputOutput.askNumberOfServants());
		if (temp[0] < 13)
			msg.setChoice(3, inputOutput.askFamiliarColor());
		if (temp[0] == 13)
			msg.setLeaderSituation(inputOutput.askLeader(msg.getLeaderSituation()));
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
		//DA RIVEDERE QUANDO SI INTRODUCE LA GUI
		
		InfoForView info = (InfoForView)msg;
		CardDTO takenCard;
		
		//se ho piazzato un familiare lo aggiungo alla board
		if(info.familiar>0 && info.familiar<5)
			gameBoardDTO.insertFamiliar(msg);
		
		//all'inizio: se non ho ancora memorizzato una personal board la creo
		for(String name: info.resSituation.keySet())
			if(personalBoardsDTO.get(name)==null)
				personalBoardsDTO.put(name, new PersonalBoardDTO(name, tileDTO));
		
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
		inputOutput.showTowerAndDices (msg);
	}
	
	public void showMessage(String message) {
			inputOutput.showMessage(message);
	}
	
}
