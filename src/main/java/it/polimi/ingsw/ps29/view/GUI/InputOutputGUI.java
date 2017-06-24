package it.polimi.ingsw.ps29.view.GUI;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.InputOutput;

public class InputOutputGUI implements InputOutput {
	private GUICore screen;
	
	public InputOutputGUI () {
		screen = new GUICore();
	}


	@Override
	public void showMessage(InteractionMessage message) {
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
		screen.setTowers(msg.getTowers());
		TowersDTO towers = msg.getTowers();
		ArrayList<Integer> idCards = new ArrayList<Integer>();
		String[] types = {"territory", "building", "character", "venture"};
		for(int i=0; i<types.length; i++)
			for(CardDTO card: towers.getTowers().get(types[i]))
				idCards.add(card.getId());
		screen.board.setCards(idCards);
		
	}

	@Override
	public ArrayList<ArrayList<Object>> askLeader(ArrayList<ArrayList<Object>> leaderSituation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showFirstInfo(FirstBoardInfo msg) {
		screen.statusBar.setText("Player: "+msg.getName());
		int tileId = msg.getTiles().get(msg.getName()).getId();
		screen.tile.setImage("bonus_tiles/personalbonustile_"+tileId+".png");
		screen.excommunication1.setImage("excomm_card/excomm_1_"+msg.getExCards().get(0).getId()+".png");
		screen.excommunication2.setImage("excomm_card/excomm_2_"+msg.getExCards().get(1).getId()+".png");
		screen.excommunication3.setImage("excomm_card/excomm_3_"+msg.getExCards().get(2).getId()+".png");
	}


	@Override
	public ActionChoice handleAskNextAction(ActionChoice msg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BonusChoice handleBonusAction(BonusChoice msg) {
		// TODO Auto-generated method stub
		return null;
	}

}
