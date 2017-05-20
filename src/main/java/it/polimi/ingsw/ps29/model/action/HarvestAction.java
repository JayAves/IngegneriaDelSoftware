package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import org.omg.PortableServer.Servant;

import it.polimi.ingsw.ps29.model.game.FamilyMember;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Player;

public class HarvestAction implements Action {
	
	private Player player;
	private HarvestSpace space;
	private GameBoard board;
	
	public HarvestAction (String player, GameBoard gameBoard) {
		ArrayList <Player> tempPlayers = gameBoard.getPlayers();
		for (Player play: tempPlayers) {
			if (play.getName().equals(player)) {
				this.player = play;
				break;
			}
		}
		this.board = gameBoard;
	}

	@Override
	public void standardPlacement(FamilyMember familyMember,int servants) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPlaceable(FamilyMember familyMember, int servants) {
		space = board.getHarvestSpace();
		return !space.familiarHere(familyMember.getPlayerColor()); 
	}

	@Override
	public void performAction() {
		System.out.println("Azione di harvest");

	}

}
