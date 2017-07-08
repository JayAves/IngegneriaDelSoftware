package it.polimi.ingsw.ps29.view;

import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.RestoreSituation;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

/**
 * Contains all the methods to interact with user. Half is for showing, half is for asking and capturing input for View. A few methods are used for setting input timer.
 * @author Pietro Grotti
 * @author Pietro Melzi
 * @author Giovanni Mele
 *
 */

public interface InputOutput {
	
	abstract void showMessage(InteractionMessage message);
	
	abstract void showFirstInfo (FirstBoardInfo msg);
	
	abstract void showInfo (InfoForView info, GameBoardDTO gameBoardDTO, TowersDTO towersDTO, HashMap <String, PersonalBoardDTO> personalBoardsDTO);

	abstract void showTowerAndDices(TowersAndDicesForView msg);

	abstract Exchange askExchange(Exchange msg) throws ExpiredTimeException;

	abstract ResourceType askSpecificPrivilege(boolean different, boolean multiple) throws ExpiredTimeException;
	
	abstract int askAboutExcommunication () throws ExpiredTimeException;

	abstract ActionChoice handleAskNextAction(ActionChoice msg) throws ExpiredTimeException;
	
	abstract BonusChoice handleBonusAction (BonusChoice msg) throws ExpiredTimeException;
	
	abstract void setTimer(int timer);
	
	abstract int getTimer();
	
	abstract long getTimeStart ();

	abstract void restore(RestoreSituation msg);
}
