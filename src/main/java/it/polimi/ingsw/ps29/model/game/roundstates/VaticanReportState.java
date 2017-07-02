package it.polimi.ingsw.ps29.model.game.roundstates;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.messages.VaticanChoice;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class VaticanReportState implements RoundState {

	private final StateOfRoundIdentifier state = StateOfRoundIdentifier.VATICAN_REPORT;
    private ExcommunicationCard excommunication;
    
    public VaticanReportState ( Match match){
    	
    	excommunication = match.getBoard().getExcommunication(match.getRound());
    }

	@Override
	public RoundState doAction(int roundNumber, Match match) {
		
		return new EndOfTheRoundState();
	}
	
	public void excommunicatePlayer(Player player) {
		excommunication.getEffect().performEffect(player);
	}
	
	public void handleVaticanChoice( VaticanChoice choice, Match match){
		Player player = match.getCurrentPlayer();
		if (choice.isSustain()){
			ArrayList<Resource> vaticanBonus = match.getBoard().getVaticanBonus(match.getRound());
			for (Resource bonus : vaticanBonus)
				player.getPersonalBoard().getResources().updateResource(bonus);
			player.addSistoIVBonus();
			player.getPersonalBoard().getResources().removeResource(ResourceType.FAITH);
		}
		else
			excommunicatePlayer(player);
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
