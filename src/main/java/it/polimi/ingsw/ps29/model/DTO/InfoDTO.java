package it.polimi.ingsw.ps29.model.DTO;

import java.util.ArrayList;

public class InfoDTO {
	public GameBoardDTO gameBoard;
	public ArrayList<PersonalBoardDTO> playerBoard;
	
	public InfoDTO(GameBoardDTO gameBoard, ArrayList<PersonalBoardDTO> playerBoard) {
		super();
		this.gameBoard = gameBoard;
		this.playerBoard = playerBoard;
	}
	
	public PersonalBoardDTO getBoard (String playerName) {
		for(PersonalBoardDTO board: playerBoard)
			if (board.name.equals(playerName))
				return board;
		return null;
	}

}
