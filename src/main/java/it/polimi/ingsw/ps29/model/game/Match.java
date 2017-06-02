package it.polimi.ingsw.ps29.model.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;

import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.cards.customadapters.CardAdapter;
import it.polimi.ingsw.ps29.model.cards.customadapters.EffectAdapter;
import it.polimi.ingsw.ps29.model.cards.customadapters.ResourceAdapter;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class Match extends Observable{
	
	private static int id = 1;
	private GameBoard board;
	private Period period;
	private int round;
	
	public Match (ArrayList<Player> players) throws FileNotFoundException {
		id++;
		board = new GameBoard(players);
		setPeriod(Period.FIRST);
		setRound(1);
		BufferedReader cards = new BufferedReader(new FileReader("src/main/java/cards.json"));
	    GsonBuilder gcards = new GsonBuilder();
	    gcards.registerTypeAdapter(Card.class, new CardAdapter());
	    gcards.registerTypeAdapter(Effect.class, new EffectAdapter());
	    gcards.registerTypeAdapter(Resource.class, new ResourceAdapter());
	    
	    Card[] cardz = gcards.create().fromJson(cards, Card[].class);
	    
	    Period[] periods= Period.values();
	    
	    for (Period period : periods) {		//creo deck separati per periodo
	    	
	    	for (CardType type: CardType.values()) {
	    		
	    		Deck deck = new Deck(period, type);
	    		board.decks.add(deck);
	    		
	    		for (int i=0; i< cardz.length;i++) {
	    		
	    			if ((cardz[i].getPeriod().equals(period)) && (cardz[i].getType().equals(type.getType()))) {
	    				deck.addCard(cardz[i]);
	    			}
	    		}
	    		
	    	}
	    }
	    
	}

	public GameBoard getBoard() {
		return board;
	}
	
	/*
	public GameBoard getBoardForView () {
		return board.clone ();
	}
	*/
	public void informView () {
		setChanged();
		notifyObservers(/*getBoardForView()*/);
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public static int getId() {
		return id;
	}

	

	
	
}
