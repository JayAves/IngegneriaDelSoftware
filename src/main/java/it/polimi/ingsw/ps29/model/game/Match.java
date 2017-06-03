package it.polimi.ingsw.ps29.model.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;

import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps29.model.action.actionstates.ActionState;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.cards.customadapters.CardAdapter;
import it.polimi.ingsw.ps29.model.cards.customadapters.EffectAdapter;
import it.polimi.ingsw.ps29.model.cards.customadapters.ResourceAdapter;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundSetupState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundState;

public class Match extends Observable{
	
	private static final int NUMBER_OF_FAMILIARS = 4;
	private static int id = 1;
	private GameBoard board;
	private Period period;
	private int round;
	private boolean endOfMatch= false;
	private RoundState state;
	
	
	public Match (ArrayList<Player> players) throws FileNotFoundException {
		id++;
		board = new GameBoard(players);
		state= new RoundSetupState();
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
	    		
	    		for (int i=0; i< cardz.length;i++) {
	    		
	    			if ((cardz[i].getPeriod().equals(period)) && (cardz[i].getType().equals(type.getType()))) {
	    				deck.addCard(cardz[i]);
	    			}
	    		}
	    		
	    		board.getDecks().add(deck);
	    	}
	    }
	    
	}
	
	public void gameEngine() {
		
		//sistemare state == null (sarebbe da assegnare correttamente endOfMatch)
		while(!endOfMatch || state == null) {
			if(state instanceof ActionState){
				String firstPlayer = board.getPlayers().get(0).getName();
				board.setPlayersOrderMoved(false);
				for (int i=0; i<NUMBER_OF_FAMILIARS; i++)
					while(!board.getPlayers().get(0).getName().equals(firstPlayer)|| !board.isPlayersOrderMoved()){
						notify();
					}
			}
			state= state.doAction(round, board);
			
		}
		
		//ciclo di calcolo punti vittoria
		//stampa punteggi finali
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
