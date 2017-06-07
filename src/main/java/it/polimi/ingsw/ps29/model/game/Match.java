package it.polimi.ingsw.ps29.model.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps29.model.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.model.DTO.InfoDTO;
import it.polimi.ingsw.ps29.model.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.cards.customadapters.CardAdapter;
import it.polimi.ingsw.ps29.model.cards.customadapters.EffectAdapter;
import it.polimi.ingsw.ps29.model.cards.customadapters.ResourceAdapter;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.roundstates.ActionsState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundSetupState;
import it.polimi.ingsw.ps29.model.game.roundstates.RoundState;

public class Match extends Observable{
	
	private static final int NUMBER_OF_FAMILIARS = 4;
	private static int id = 1;
	private GameBoard board;
	private int round;
	public boolean endOfMatch= false;
	private RoundState state;
	public InfoDTO infoForView;
	private ArrayList<Player> players; //per ora tengo i players anche i match, poi si può pensare come evitarlo [pie ha un'idea]
	
	
	public Match (ArrayList<String> playerNames) throws FileNotFoundException {
		id++;
		players = initPlayers(playerNames);
		board = new GameBoard(players);
		state= new RoundSetupState();
		round = 0;
		initDecks();
		createDTO (players);
	}
	
	private void createDTO (ArrayList<Player> players) {
		ArrayList <PersonalBoardDTO> playerBoardDTO = new ArrayList<PersonalBoardDTO> ();
		for (Player player: players){
			playerBoardDTO.add(player.boardDTO);
		}
		infoForView = new InfoDTO (new GameBoardDTO (),playerBoardDTO);
	}
	
	private void initDecks () throws FileNotFoundException {
		BufferedReader cards = new BufferedReader(new FileReader("src/main/java/cards.json"));
	    GsonBuilder gcards = new GsonBuilder();
	    gcards.registerTypeAdapter(Card.class, new CardAdapter());
	    gcards.registerTypeAdapter(Effect.class, new EffectAdapter());
	    gcards.registerTypeAdapter(Resource.class, new ResourceAdapter());
	    
	    Card[] cardz = gcards.create().fromJson(cards, Card[].class);
	    
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
    		int rnd= new Random().nextInt(tilez.length);
    		players.get(i).setPersonalBonusTile(tilez[rnd]); // assegno una bonusTile casuale
    	}
    	
    	int startingCoins=5; //distribuisco le monete iniziali
    	
    	for (int i=0; i<players.size(); i++) {
    		players.get(i).getPersonalBoard().getResources().updateResource(new Resource("coin",startingCoins));
    		startingCoins++;
    	}
    	
    	return players;

	}
	
	
	public void gameEngine() {
		
		while(!endOfMatch ) {
			//viene gestita qui sotto la fase di azione, per comodità con il pattern observer observable
			//con il match che per richiedere una nuova azione notifica il controller
			if(state instanceof ActionsState){
				
				String firstPlayer = board.getCurrentPlayer().getName();
				
				//in questo for avviene la fase Actions del gioco
				for (int i=0; i<NUMBER_OF_FAMILIARS; i++){
					board.setPlayersOrderMoved(false);
					//conclude il while quando tutti i giocatori hanno portato a termine una mossa
					while(!board.getCurrentPlayer().getName().equals(firstPlayer)|| !board.isPlayersOrderMoved()) {
						setChanged();
						notifyObservers();
					}
				}
			}
			
			//aggiorna lo stato del turno, permette di gestire tutte le altre fasi di turno
			state= state.doAction(round, this);
		}
		
		//ciclo di calcolo punti vittoria
		//stampa punteggi finali
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
