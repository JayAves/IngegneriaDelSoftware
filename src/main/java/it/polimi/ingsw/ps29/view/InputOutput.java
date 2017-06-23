package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.messages.BonusChoice;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.view.messages.TowersAndDicesForView;

public interface InputOutput {
	
	abstract void showMessage (String message);
	
	abstract int[] askTypeOfAction ();
	
	abstract int askNumberOfServants ();
	
	abstract int askFamiliarColor ();

	abstract Exchange askExchange(Exchange msg);
	
	abstract int askFloor ();
	
	abstract void printBonusAction (BonusActionEffect effect);

	abstract ResourceType askSpecificPrivilege();
	
	abstract int askAboutExcommunication ();
	
	abstract void showInfo (GameBoardDTO gameBoardDTO, TowersDTO towerdDTO, HashMap <String, PersonalBoardDTO> personalBoardsDTO);

	abstract void showTowerAndDices(TowersAndDicesForView msg);

	abstract ArrayList<ArrayList<Object>> askLeader(ArrayList<ArrayList<Object>> leaderSituation);

	abstract void showFirstInfo (FirstBoardInfo msg);

	abstract ActionChoice handleAskNextAction(ActionChoice msg);
	
	abstract BonusChoice handleBonusAction (BonusChoice msg);
}
