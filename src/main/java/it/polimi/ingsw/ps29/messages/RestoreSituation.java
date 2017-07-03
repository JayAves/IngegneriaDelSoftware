package it.polimi.ingsw.ps29.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

public class RestoreSituation extends InteractionMessage {
	//contains info about dices, towers, tiles and excommunication cards
	FirstBoardInfo firstInfo; 
	GameBoardDTO gameBoard;
	ArrayList<PersonalBoardDTO> personalBoards = new ArrayList<PersonalBoardDTO>();
	
	/**
	 * 
	 */
	
	public RestoreSituation(String player) {
		super(player, false);
	}

	
	private static final long serialVersionUID = 1968162658660556454L;

	@Override
	public void visit(VisitorMessages visitor) {
		visitor.visit(this);
	}

	@Override
	public void receive(VisitorServerMessages visitor) {
		visitor.receive(this);
	}

	public FirstBoardInfo getFirstInfo() {
		return firstInfo;
	}

	public void setFirstInfo(FirstBoardInfo firstInfo) {
		this.firstInfo = firstInfo;
	}

	public GameBoardDTO getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(GameBoardDTO gameBoard) {
		this.gameBoard = gameBoard;
	}

	public ArrayList<PersonalBoardDTO> getPersonalBoard() {
		return personalBoards;
	}

	public void setPersonalBoard(PersonalBoardDTO personalBoard) {
		personalBoards.add(personalBoard);
	}
	
	

}
