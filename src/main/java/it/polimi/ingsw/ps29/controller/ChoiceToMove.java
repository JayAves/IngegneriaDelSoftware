package it.polimi.ingsw.ps29.controller;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;

/**
 * Is responsible for conversion from client's ActionChoice or BonusChoice to Move.
 * 
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @see Move
 * 
 * 
 */

public class ChoiceToMove {

	public static Move createMove (ActionChoice choice, GameBoard board) {
		
		return new Move (getPlayer(choice.getName(), board), getSpace(choice.getChoice(0)), choice.getChoice(1), 
				choice.getChoice(2), getFamiliar(choice.getName(), board, choice.getChoice(3)));
	}
	
	private static Player getPlayer (String player, GameBoard board) {
		for (Player p: board.getPlayers()) {
			if(p.getName().equals(player))
				return p;
		}
		return null;
	}	
	
	
	private static FamilyMemberInterface getFamiliar (String player, GameBoard board, int i) {
		DiceColor color;
		
		if(i>0) {
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
			return getPlayer(player, board).getFamiliarByColor(color);
		}
		
		return new FamilyMember(DiceColor.BONUS, getPlayer(player, board).getColor());
	}

	private static String getSpace(int choice) {
		
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
