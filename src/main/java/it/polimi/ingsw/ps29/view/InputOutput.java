package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.TowersForView;

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

	abstract void showTower(TowersDTO msg);

	abstract void askLeader(ArrayList<ArrayList<Object>> leaderSituation);


}
