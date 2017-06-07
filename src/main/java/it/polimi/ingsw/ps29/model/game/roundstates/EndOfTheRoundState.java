package it.polimi.ingsw.ps29.model.game.roundstates;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;

public class EndOfTheRoundState implements RoundState {

	@Override
	public RoundState doAction(int roundNumber, Match match) {
		
		//modifica ordine di turno
		ArrayList<Color> colorOrder = ((CouncilPalaceArea)match.getBoard().getSpace("CouncilPalace")).playersOrder();
		ArrayList<Player> playerOrder = convertOrder (colorOrder, match.getBoard());
		match.getBoard().setPlayers(createOrder (playerOrder, match.getBoard()));
		
		//imposta tutti i busy su familiare a false
		for(Player player: match.getBoard().getPlayers()) {
			for (int i=0; i<player.getFamily().length; i++)
				player.getFamily()[i].setBusy(false);
		}
		
		//svuotare gli spazi azione
		for (ActionSpace space: match.getBoard().getSpaces().values()) 
			space.cleanSpace();
		match.infoForView.cleanBoardDTO();
		
		if(roundNumber==6)
			match.setEndOfMatch();
		
		return new RoundSetupState();
	}
	
	
	
	private ArrayList<Player> convertOrder (ArrayList<Color> colorOrder, GameBoard board) {
		ArrayList<Player> playerOrderFinal = new ArrayList<Player> ();
		for(Color color: colorOrder) 
			for(Player player: board.getPlayers())
				if(player.getColor() == color) 
					playerOrderFinal.add(player);
				
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
