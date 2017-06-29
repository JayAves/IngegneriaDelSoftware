package it.polimi.ingsw.ps29.model.game.roundstates;

import java.util.ArrayList;
import java.util.Random;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.cards.LeaderCard;
import it.polimi.ingsw.ps29.model.game.Dice;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.space.TowerArea;

public class RoundSetupState implements RoundState {

	private final StateOfRoundIdentifier state = StateOfRoundIdentifier.ROUND_SETUP;

	@Override
	public RoundState doAction(int roundNumber, Match match) {
		roundNumber++;
		match.setRound(roundNumber);
		initDecks (roundNumber, match.getBoard());
		flipLeaderCards(match);
			
		
		for(Dice dice: match.getBoard().getDices()) {
			dice.rollDice();
			for (Player player: match.getBoard().getPlayers()) {
				player.getFamiliarByColor(dice.getColor()).setPower(dice.getValue());
			}
		}
	
		
		return new ActionsState ();
		
	}
	
	private void initDecks (int roundNumber, GameBoard board) {
		Period currentPeriod= getPeriod(roundNumber);
		CardType[] types= CardType.values();
		
		for (int i=0;i<types.length;i++) { //per ogni tipo di carta
		
			if ((!types[i].getType().equalsIgnoreCase(CardType.ALL.getType()))
					&& (!types[i].getType().equalsIgnoreCase(CardType.EXCOMMUNICATION.getType()))){
			
				Deck deck= board.getSpecificDeck(types[i], currentPeriod);
				ArrayList<Card> newCards= new ArrayList<Card>();
				
				for (int j=0;j<4;j++) {	//scelgo 4 carte a caso dal deck
					
					int rnd= new Random().nextInt(deck.getSize());
					newCards.add(deck.getCard(rnd));
					deck.removeCard(rnd);
				}
				
				// fill della torre con il deck creato
				((TowerArea) board.getSpace(types[i].getType()+"Tower")).fill(newCards);
				
				
			}
		}
		
	}
	
	private void flipLeaderCards(Match match){
		for (Player player : match.getBoard().getPlayers()){
			if (!player.getPersonalBoard().getActivatedLeaderCards().isEmpty()){
				ArrayList<LeaderCard> toRemove = new ArrayList<LeaderCard>();
				for (LeaderCard card : player.getPersonalBoard().getActivatedLeaderCards()){
					if (!card.getIfPermanent())
						toRemove.add(card);
				}
				player.getPersonalBoard().getPlayedLeaderCards().addAll(toRemove);
				player.getPersonalBoard().getActivatedLeaderCards().removeAll(toRemove);
		}
		}
	}
	
	public Period getPeriod(int roundnumber) {
		
		switch (roundnumber) {
		case 1:
		case 2:	
			return Period.FIRST;
		case 3:
		case 4:
			return Period.SECOND;
		case 5:
		case 6:
			return Period.THIRD;
		default:
			throw new IllegalArgumentException();
		}
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
