package it.polimi.ingsw.ps29.model.game.roundstates;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;

/**
 * Round status that comes after all actions are performed and Vatican reports are done.
 * @author Pietro Melzi
 * @author Pietro Grotti
 *
 */
public class EndOfTheRoundState implements RoundState {

	private final StateOfRoundIdentifier state = StateOfRoundIdentifier.END_OF_THE_ROUND;

	@Override
	public RoundState doAction(int roundNumber, Match match) {
		
		
		//all vatican reports are set to not performed
		for (Player player: match.getBoard().getPlayers())
			player.setVaticanReportPerformed(false);
		
		//modifica ordine di turno
		ArrayList<PlayerColor> colorOrder = ((CouncilPalaceArea)match.getBoard().getSpace("CouncilPalace")).playersOrder();
		ArrayList<Player> playerOrder = convertOrder (colorOrder, match.getBoard());
		match.getBoard().setPlayers(createOrder (playerOrder, match.getBoard()));
		
		//sets not busy all FamilyMembers
		for(Player player: match.getBoard().getPlayers()) {
			for (int i=0; i<player.getFamily().length; i++)
				player.getFamily()[i].setBusy(false);
		}
		
		//empties all ActionSpaces
		for (ActionSpace space: match.getBoard().getSpaces().values()) 
			space.cleanSpace();
		
		RoundState state = new RoundSetupState();
		
		if(roundNumber==6) 
			match.setEndOfMatch();
			//conclusion in controller
		else 
			state = state.doAction(roundNumber, match);
		
		return state; //if game is not over state will be  ActionsState
	}
	
	
	
	private ArrayList<Player> convertOrder (ArrayList<PlayerColor> colorOrder, GameBoard board) {
		ArrayList<Player> playerOrderFinal = new ArrayList<Player> ();
		for(PlayerColor color: colorOrder) 
			for(Player player: board.getPlayers())
				if(player.getColor() == color) 
					playerOrderFinal.add(player);
				
		return playerOrderFinal;
	}
	
	
	//creates a new turn order
	private ArrayList<Player> createOrder (ArrayList <Player> playerOrder, GameBoard board) {
		ArrayList <Player> playerOrderInitial = board.getPlayers();
		for(Player player: playerOrder)
			playerOrderInitial.remove(player);
		for (Player player: playerOrderInitial)
			playerOrder.add(player);
		return playerOrder;
	}


	@Override
	public StateOfRoundIdentifier getState() {
		return state;
	}

	@Override
	public int getStateNumber() {
		return state.getStateNumber();
	}
}
