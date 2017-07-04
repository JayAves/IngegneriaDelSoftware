package it.polimi.ingsw.ps29.messages;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.controller.Controller.VisitorMessages;
import it.polimi.ingsw.ps29.viewclient.Client.VisitorServerMessages;

/**
 * Message sent from ClientThreads to Controller with the purpose of making it send game updated situation 
 * to the new View of a player recently reconnected
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.server.SocketClientThread
 * @see it.polimi.ingsw.ps29.server.RMIClientThread
 * @see it.polimi.ingsw.ps29.server.RoomCreator
 * 
 *
 */
public class RestoreSituation extends InteractionMessage {
	
	//contains info about dices, towers, tiles and excommunication cards
	FirstBoardInfo firstInfo; 
	GameBoardDTO gameBoard;
	ArrayList<PersonalBoardDTO> personalBoards = new ArrayList<PersonalBoardDTO>();
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = 1968162658660556454L;
	
	public RestoreSituation(String player) {
		super(player, false);
	}

	
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
