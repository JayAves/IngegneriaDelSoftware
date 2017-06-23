package it.polimi.ingsw.ps29.controller;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;

public class ChoiceToMove {
	
	private GameBoard board;
	
	
	
	public ChoiceToMove(GameBoard board) {
		super();
		this.board = board;
	}

	public Move createMove (ActionChoice choice) {
		
		return new Move (getPlayer(choice.getName()), getSpace(choice.getChoice(0) ), choice.getChoice(1), 
				choice.getChoice(2), getFamiliar(choice.getName(), choice.getChoice(3)));
	}
	
	private Player getPlayer (String player) {
		for (Player p: board.getPlayers()) {
			if(p.getName().equals(player))
				return p;
		}
		return null;
	}	
	
	
	private FamilyMemberInterface getFamiliar (String player, int i) {
		DiceColor color;
		
		switch (i) {
			case 1:
				color = DiceColor.BLACK;
				break;
			case 2:
				color = DiceColor.WHITE;
				break;
			case 3:
				color = DiceColor.ORANGE;
				break;
			default:
				color = DiceColor.NEUTRAL;
				break;
		}
		return getPlayer(player).getFamiliarByColor(color);
	}

	private String getSpace(int choice) {
		
		String space= "";
		
		switch (choice) {
		
		case 1:
			space= "Harvest";
			break;
		case 2:
			space= "Production";
			break;
		case 3:
			space= "territoryTower";
			break;
		case 4:
			space= "buildingTower";
			break;
		case 5:
			space= "characterTower";
			break;
		case 6:
			space= "ventureTower";
			break;
		case 7:
			space= "FirstMarket";
			break;
		case 8:
			space= "SecondMarket";
			break;
		case 9:
			space= "ThirdMarket";
			break;
		case 10:
			space= "FourthMarket";
			break;
		case 11:
			space= "CouncilPalace";
			break;
		default:
			break;
		}
		return space;
	}
}
