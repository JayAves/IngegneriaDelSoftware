package it.polimi.ingsw.ps29.controller;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.Servants;
import it.polimi.ingsw.ps29.view.UserChoice;

public class ChoiceToMove {
	private GameBoard board;
	
	
	
	public ChoiceToMove(GameBoard board) {
		super();
		this.board = board;
	}

	public Move createMove (UserChoice choice) {
		return new Move (getPlayer(choice.getName()), choice.getChoices()[0]+"", choice.getChoices()[1], 
				choice.getChoices()[2], getFamiliar(choice.getName(), choice.getChoices()[3]));
	}
	
	public Player getPlayer (String player) {
		for (Player p: board.getPlayers()) 
			if(p.getName().equals(player))
				return p;
		return null;	
	}
	
	public Servants getServants (int number) {
		return new Servants (-number);
	}
	
	
	
	public FamilyMember getFamiliar (String player, int i) {
		DiceColor color = DiceColor.NEUTRAL;
		
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
			case 4:
				color = DiceColor.NEUTRAL;
				break;
			default:
				break;
		}
		return getPlayer(player).getFamiliarByColor(color);
	}


}
