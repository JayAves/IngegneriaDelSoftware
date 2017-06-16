package it.polimi.ingsw.ps29.model.game.roundstates;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;

public class EndOfTheRoundState implements RoundState {

	private final StateOfRoundIdentifier state = StateOfRoundIdentifier.END_OF_THE_ROUND;

	@Override
	public RoundState doAction(int roundNumber, Match match) {
		
		//pulisco il flag che indica che la richiesta di supporto alla Chiesa è stata performata
		for (Player player: match.getBoard().getPlayers())
			player.setVaticanReportPerformed(false);
		
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
		
		RoundState state = new RoundSetupState();
		
		if(roundNumber==2) 
			match.setEndOfMatch();
			//nel gameEngine chiamata a funzione per calcolo del punteggio
		else 
			state = state.doAction(roundNumber, match);
		
		return state; //se non è finito il gioco ha già svolto RoundSetup e tornerà ActionsState
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


	@Override
	public StateOfRoundIdentifier getState() {
		return state;
	}

	@Override
	public int getStateNumber() {
		return state.getStateNumber();
	}
}
