package it.polimi.ingsw.ps29.model.action;

import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.space.ActivityArea;

public class HarvestAction implements Action {
	
	private Move move; 
	private ActivityArea space;
	
	public HarvestAction (Move move) {
		this.move = move;
		space = (ActivityArea) move.getSpace();
	}
	
	@Override
	public boolean isForbidden(Move move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPlaceable(Move move) {
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getHarvestPower());
	}

	@Override
	public void performAction(Move move) {
		//placement
		if (space.isEmpty()) space.headPlacement (move.getFamiliar());
		else space.queuePlacement(move.getFamiliar());
		
	}
	
	/*private Player player;
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
	}*/


}
