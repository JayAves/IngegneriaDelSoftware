package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Effect;
import it.polimi.ingsw.ps29.model.cards.TerritoryCard;
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
	public boolean isForbidden() { //da verificare carte scomunica in arrayList di bonusAndMalusPlyer
		
		return false;
	}

	@Override
	public boolean isPlaceable() {
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getHarvestPower());
	}

	@Override
	public void performAction() {
		//placement
		if (space.isEmpty()) space.headPlacement (move.getFamiliar());
		else space.queuePlacement(move.getFamiliar());
		
		//per ogni elemento dell'arrayList territory
		//per ogni effetto di tale carta
		//se ho sufficiente potere attivo effetto permanente
		
		ArrayList<TerritoryCard> importedSlot= move.getPlayer().getPersonalBoard().getTerritorySlot();
		for(TerritoryCard card: importedSlot) {
			for(Effect effect: card.getPermanentEffects()) {
				if (move.getFamiliar().getHarvestPower()> card.getHarvestForce()) {
					//effect.performEffect(); da vedere effetti!
				}
			}
		}
		
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
