package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.TerritoryCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.ResourcesArray;
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
		
		ArrayList<ResourcesArray> bonusFromTile= move.getPlayer().getPersonalBoard().getPersonalBonusTile().getHarvestBonus();	
		//ciclo lettura bonus della bonus tile
		for(ResourcesArray effect: bonusFromTile) {
			effect.performEffect(move.getPlayer());
		}
		
		ArrayList<Card> importedSlot= move.getPlayer().getPersonalBoard().getCards("Territory");
		for(Card card: importedSlot) {
			for(Effect effect: card.getPermanentEffects()) {
				if (move.getFamiliar().getHarvestPower()> ((TerritoryCard)card).getHarvestForce()) {
					effect.performEffect(move.getPlayer());
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

	
	
	/*
	 * 
	private Model model;
    private Move move;
    private FamilyMember member;

    public HarvestAction(Model model, Move move) {
        this.model = model;
        this.move = move;
    }

    member = model.currentPlayer.getSelectedFamilyMember(move.member);

    model.currentPlayer.HarvestState.checkPlayerFamiliarsInThisSpace(member);

    ////tutta la sbatta


    model.currentPlayer.HarvestState.setNewState();


	 */

}
