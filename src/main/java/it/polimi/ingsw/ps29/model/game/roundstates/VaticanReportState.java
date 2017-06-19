package it.polimi.ingsw.ps29.model.game.roundstates;

import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;

public class VaticanReportState implements RoundState {

	private final StateOfRoundIdentifier state = StateOfRoundIdentifier.VATICAN_REPORT;

	@Override
	public RoundState doAction(int roundNumber, Match match) {
		//gestione del rapporto in vaticano
	    int tresHold = match.getBoard().getExcommunicationTreshold(roundNumber);
		ExcommunicationCard excommunication = match.getBoard().getExcommunication(roundNumber);
		for (Player player : match.getBoard().getPlayers()){
			if (player.getPersonalBoard().getSpecificResource("faith").getAmount() < tresHold){
				excommunication.effect.performEffect(player);
			}
			else{
				boolean vaticanChoice = false;
				if (vaticanChoice){
					player.getPersonalBoard().getResources().updateResource(match.getBoard().getVaticanBonus(roundNumber));
					player.getSistoIVBonus();
					player.getPersonalBoard().getResources().removeResource(ResourceType.FAITH);
				}
				excommunication.effect.performEffect(player);
			    }

		   }
		
		
		
		return new EndOfTheRoundState();
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
