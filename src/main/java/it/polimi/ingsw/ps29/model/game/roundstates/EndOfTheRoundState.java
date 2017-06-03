package it.polimi.ingsw.ps29.model.game.roundstates;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;

public class EndOfTheRoundState implements RoundState {

	@Override
	public RoundState doAction(int roundNumber, GameBoard board) {
		//modifica ordine di turno
		ArrayList<Color> colorOrder = ((CouncilPalaceArea)board.getSpace("CouncilPalace")).playersOrder();
		ArrayList<Player> playerOrder = convertOrder (colorOrder, board);
		playerOrder = createOrder (playerOrder, board);
		
		//imposta tutti i busy su familiare a false
		for(Player player: board.getPlayers()) {
			for (int i=0; i<player.getFamily().length; i++)
				player.getFamily()[i].setBusy(false);
		}
		
		//svuotare gli spazi azione
		for (ActionSpace space: board.getSpaces().values()) 
			space.cleanSpace();
		
		if(roundNumber == 6) {
			//var di fine partita in match = true
			return null;
		} else
			return new RoundSetupState();
	}
	
	private ArrayList<Player> convertOrder (ArrayList<Color> colorOrder, GameBoard board) {
		ArrayList<Player> playerOrderFinal = new ArrayList<Player> ();
		ArrayList<Player> playerOrderInitial = board.getPlayers();
		for(Color color: colorOrder) {
			for(Player player: playerOrderInitial)
				if(player.getColor() == color) {
					playerOrderFinal.add(player);
					break;
				}
		}
		return playerOrderFinal;
	}
	
	private ArrayList<Player> createOrder (ArrayList <Player> playerOrder, GameBoard board) {
		ArrayList <Player> playerOrderInitial = board.getPlayers();
		for(Player player: playerOrder)
			playerOrderInitial.remove(player);
		for (Player player: playerOrderInitial)
			playerOrder.add(player);
		return playerOrder;
	}

}
