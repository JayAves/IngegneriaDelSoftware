package it.polimi.ingsw.ps29.model.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationDeck;
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
	private int round;
	public boolean endOfMatch= false;
	private RoundState state;
	private ArrayList<Player> players; //per ora tengo i players anche i match, poi si può pensare come evitarlo [pie ha un'idea]
	
	
	public Match (ArrayList<String> playerNames) throws FileNotFoundException {
		id++;
		players = initPlayers(playerNames);
		board = new GameBoard(players);
		state= new RoundSetupState();
		round = 0;
		initDecks();
	}
	
	
	private void initDecks () throws FileNotFoundException {
		BufferedReader cards = new BufferedReader(new FileReader("src/main/java/cards.json"));
	    GsonBuilder gcards = new GsonBuilder();
	    gcards.registerTypeAdapter(Card.class, new CardAdapter());
	    gcards.registerTypeAdapter(Effect.class, new EffectAdapter());
	    gcards.registerTypeAdapter(Resource.class, new ResourceAdapter());
	    
	    BufferedReader eCards = new BufferedReader(new FileReader ("src/main/java/ExcommunicationsCards.json"));
	    GsonBuilder geCards = new GsonBuilder();
	    geCards.registerTypeAdapter(Effect.class, new EffectAdapter());
	    geCards.registerTypeAdapter(Resource.class, new ResourceAdapter());
	    
	    Card[] cardz = gcards.create().fromJson(cards, Card[].class);
	    ExcommunicationCard[] eCardz = geCards.create().fromJson(eCards, ExcommunicationCard[].class);
	    
	    
	    
	    Period[] periods= Period.values();
	    CardType [] types = CardType.values();
	    
	    
	    for (Period period : periods) {		//creo deck separati per periodo
	    	
	    	for (int index=0; index<4; index++) {
	    		
	    		Deck deck = new Deck(period, types[index]);
	    		for (int i=0; i< cardz.length;i++) {
	    		
	    			if ((cardz[i].getPeriod().equals(period)) && (cardz[i].getType().equals(types[index].getType()))) 
	    				deck.addCard(cardz[i]);
	    			
	    		}
	    		
	    		board.getDecks().add(deck);
	    		
	    		
	    	}
	    	
	    	ArrayList<ExcommunicationCard> tempDeck = new ArrayList<ExcommunicationCard>();
	    	
	    	for (int i = 0; i < eCardz.length; i++){
	    		if (eCardz[i].getPeriod().equals(period))
	    			tempDeck.add(eCardz[i]);
	    	}
	    	
	        ExcommunicationDeck eDeck = new ExcommunicationDeck(tempDeck);
	    	
	        board.getExDecks().add(eDeck);
	    }
	    
	    
	}
	
	
	
	
	private ArrayList<Player> initPlayers (ArrayList<String> playersNames) throws FileNotFoundException {

		ArrayList<Player> players = new ArrayList<Player> ();
    	Color[] colors = Color.values();
    	
    	for(int i=0; i<playersNames.size(); i++) 
    		players.add(new Player(playersNames.get(i), colors[i], new PersonalBonusTile(new ArrayList<Resource> (), new ArrayList <Resource> ())));
    	
    	BufferedReader tiles = new BufferedReader(new FileReader("src/main/java/personalbonustile.json"));
	    GsonBuilder gtiles = new GsonBuilder();
	    
	    gtiles.registerTypeAdapter(Resource.class, new ResourceAdapter());
	    PersonalBonusTile[] tilez = gtiles.create().fromJson(tiles, PersonalBonusTile[].class);
 
	    
    	for(int i=0; i<players.size(); i++) {
    		/*int rnd= new Random().nextInt(tilez.length);
    		players.get(i).setPersonalBonusTile(tilez[rnd]); // assegno una bonusTile casuale
    		*/
    		players.get(i).setPersonalBonusTile(tilez[0]);
    	}
    	
    	int startingCoins=5; //distribuisco le monete iniziali
    	
    	for (int i=0; i<players.size(); i++) {
    		players.get(i).getPersonalBoard().getResources().updateResource(new Resource("coin",startingCoins));
    		startingCoins++;
    	}
    	
    	return players;

	}
	
	
	public void gameEngine() {
		
		System.out.println("Server: turno del giocatore "+board.getCurrentPlayer().getName());
		
		for (Player player : board.getPlayers()){
			player.passPersonalBoard();
			player.getFinalPoints();
			if (player.getVentureCardPenalty() == false){
				ArrayList<Card> ventures = player.getPersonalBoard().getCards("ventures");
				for (Card card : ventures){
					ArrayList<Effect> finalEffects = card.getPermanentEffects();
					for (Effect effect : finalEffects){
						effect.performEffect(player);
					}
				}
			}
		}
		
		
	}
	
	public GameBoard getBoard() {
		return board;
	}


	public int getRound() {
		return round;
	}

	public static int getId() {
		return id;
	}

	public void setRound (int round) {
		this.round = round;
	}
	
	public void setEndOfMatch () {
		endOfMatch = true;
	}

}
