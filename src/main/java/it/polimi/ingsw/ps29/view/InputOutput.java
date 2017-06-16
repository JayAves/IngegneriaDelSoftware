package it.polimi.ingsw.ps29.view;

import java.util.HashMap;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.viewclient.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.viewclient.DTO.PersonalBoardDTO;

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
	
	abstract void showInfo (GameBoardDTO gameBoardDTO, HashMap <String, PersonalBoardDTO> personalBoardsDTO);

}
