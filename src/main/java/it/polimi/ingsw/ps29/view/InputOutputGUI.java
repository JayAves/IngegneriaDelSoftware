package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.TowersForView;

public class InputOutputGUI implements InputOutput {


	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] askTypeOfAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askNumberOfServants() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askFamiliarColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Exchange askExchange(Exchange msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askFloor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printBonusAction(BonusActionEffect effect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResourceType askSpecificPrivilege() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askAboutExcommunication() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void showInfo(GameBoardDTO gameBoardDTO, TowersDTO towerdDTO,
			HashMap<String, PersonalBoardDTO> personalBoardsDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showTower(TowersDTO msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askLeader(ArrayList<ArrayList<Object>> leaderSituation) {
		// TODO Auto-generated method stub
		
	}



}
