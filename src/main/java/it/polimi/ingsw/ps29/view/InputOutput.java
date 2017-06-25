package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

public interface InputOutput {
	
	abstract void showMessage(InteractionMessage message);
	
	abstract int[] askTypeOfAction () throws ExpiredTimeException;
	
	abstract int askNumberOfServants () throws ExpiredTimeException;
	
	abstract int askFamiliarColor () throws ExpiredTimeException;

	abstract Exchange askExchange(Exchange msg);
	
	abstract int askFloor () throws ExpiredTimeException;
	
	abstract void printBonusAction (BonusActionEffect effect);

	abstract ResourceType askSpecificPrivilege();
	
	abstract int askAboutExcommunication ();
	
	abstract void showInfo (GameBoardDTO gameBoardDTO, TowersDTO towerdDTO, HashMap <String, PersonalBoardDTO> personalBoardsDTO);

	abstract void showTowerAndDices(TowersAndDicesForView msg);

	abstract ArrayList<ArrayList<Object>> askLeader(ArrayList<ArrayList<Object>> leaderSituation);

	abstract void showFirstInfo (FirstBoardInfo msg);

	abstract ActionChoice handleAskNextAction(ActionChoice msg) throws ExpiredTimeException;
	
	abstract BonusChoice handleBonusAction (BonusChoice msg);
	
	abstract void setTimer(int timer);
}
