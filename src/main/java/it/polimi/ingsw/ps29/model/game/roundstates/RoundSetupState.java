package it.polimi.ingsw.ps29.model.game.roundstates;

import java.util.ArrayList;
import java.util.Random;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.game.Dice;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Period;
import it.polimi.ingsw.ps29.model.space.tower.TowerArea;

public class RoundSetupState implements RoundState {

	@Override
	public RoundState doAction(int roundNumber, GameBoard board) {
		
		
		Period currentPeriod= getPeriod(roundNumber);
		
		CardType[] types= CardType.values();
		
		for (int i=0;i<types.length;i++) { //per ogni tipo di carta
		
		if ((!types[i].getType().equalsIgnoreCase(CardType.ALL.getType()))
				&& (!types[i].getType().equalsIgnoreCase(CardType.EXCOMMUNICATION.getType()))){
		
		Deck deck= board.getSpecificDeck(types[i], currentPeriod);
		
		ArrayList<Card> newCards= new ArrayList<>();
		
		for (int j=0;j<4;j++) {	//scelgo 4 carte a caso dal deck
			
			int rnd= new Random().nextInt(deck.getSize());
			
			newCards.add(deck.getCard(rnd));
			deck.removeCard(rnd);
			}
		
		((TowerArea) board.getSpace(types[i].name()+"Tower")).fill(newCards);
		
			}
		}
		
		for(Dice dice: board.getDices()) {
			dice.rollDice();
		}
	
		
		return new ActionsState ();
		
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
}