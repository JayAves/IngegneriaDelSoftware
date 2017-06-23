package it.polimi.ingsw.ps29.view.GUI;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.InputOutput;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.view.messages.TowersAndDicesForView;

public class InputOutputGUI implements InputOutput {
	private GUICore screen;
	
	public InputOutputGUI () {
		screen = new GUICore();
	}


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
	public void showTowerAndDices(TowersAndDicesForView msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ArrayList<Object>> askLeader(ArrayList<ArrayList<Object>> leaderSituation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showFirstInfo(FirstBoardInfo msg) {
		int period = 0;
		if(msg.getExCards().get(0).getPeriod() == Period.FIRST)
			period = 1;
		else if(msg.getExCards().get(0).getPeriod() == Period.SECOND)
			period = 2;
		else
			period = 3;
		screen.excommunication1 = new ImageToPrint("excomm_card/ecomm_"+period+"_"+msg.getExCards().get(0).getId()+".png");
		screen.frame.repaint();
	}



}
