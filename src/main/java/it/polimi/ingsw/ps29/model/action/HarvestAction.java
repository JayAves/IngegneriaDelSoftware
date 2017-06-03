package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.TerritoryCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.cards.effects.GainResourcesEffect;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.ActivityArea;
import it.polimi.ingsw.ps29.model.space.HarvestArea;

public class HarvestAction extends Action {
	
	private ActivityArea space;
	
	public HarvestAction(Match model, Move move) {
		super(model, move);
		this.space = (ActivityArea) model.getBoard().getSpace(move.getSpace());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isForbidden() { //da verificare carte scomunica in arrayList di bonusAndMalusPlyer
		
		return false;
	}

	@Override
	public boolean isPlaceable() {
		return !space.familiarHere(move.getFamiliar().getPlayerColor()) && space.isEnoughPowerful(move.getFamiliar().getHarvestPower()+move.getServants());
	}

	@Override
	public void performAction() {
		//placement
		if (space.isEmpty()) space.headPlacement (move.getFamiliar());
		else space.queuePlacement(move.getFamiliar());
		
		//gestione bonus della tile
		ArrayList<Resource> bonusFromTile= move.getPlayer().getPersonalBoard().getPersonalBonusTile().getHarvestBonus();	
		GainResourcesEffect bonusHarvestTile = new GainResourcesEffect(bonusFromTile);
		bonusHarvestTile.performEffect(move.getPlayer());
		
		
		ArrayList<Card> importedSlot= move.getPlayer().getPersonalBoard().getCards("territory");
		//ciclo lettura effetti da personalBoard
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
