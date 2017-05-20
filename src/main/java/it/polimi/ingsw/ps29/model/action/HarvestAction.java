package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.view.Move;

public class HarvestAction extends Action {
	
	private Player player;
	private HarvestSpace space;
	private GameBoard board;
	
	public HarvestAction (String player, GameBoard gameBoard, Move move) {
		super(move);
		ArrayList <Player> tempPlayers = gameBoard.getPlayers();
		for (Player play: tempPlayers) {
			if (play.getName().equals(player)) {
				this.player = play;
				break;
			}
		}
		this.board = gameBoard;
		space = gameBoard.getHarvestSpace();
	}

	@Override
	boolean isEnoughPowerful() {
		int valueFamiliar = move.getFamiliar().getHarvestPower()+move.getServants();
		int valueSpace = space.getValue();
		
		return false;
	}


}
