package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationDeck;
import it.polimi.ingsw.ps29.model.cards.LeaderCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;
import it.polimi.ingsw.ps29.model.space.FaithSpace;
import it.polimi.ingsw.ps29.model.space.HarvestArea;
import it.polimi.ingsw.ps29.model.space.MarketArea;
import it.polimi.ingsw.ps29.model.space.ProductionArea;
import it.polimi.ingsw.ps29.model.space.QueueActionSpace;
import it.polimi.ingsw.ps29.model.space.SingleSlotActionSpace;
import it.polimi.ingsw.ps29.model.space.TowerArea;

public class GameBoard{
	
	private ArrayList <Dice> dices;
	private HashMap <String, ActionSpace> spaces;
	private ArrayList<Player> playersOrder;
	private ArrayList<LeaderCard> leaderCards;
	private ArrayList<LeaderCard> playedLeaderCards;
	//variabile utilizzata nel metodo GameEngine della classe Match
	private ArrayList<Deck> decks;
	private ArrayList<ExcommunicationDeck> excommunicationDeck;
	private HashMap<Integer, ExcommunicationCard> excommunications;
	private HashMap<Integer, FaithSpace> faithTrack;
	private TowersDTO towersDTO;
	private ArrayList<Effect> LeaderEffectsActivated;
	
	
	
	public GameBoard (ArrayList<Player> players) {
		dices = new ArrayList<Dice> ();
		dices.add(new Dice(DiceColor.BLACK));
		dices.add(new Dice(DiceColor.WHITE));
		dices.add(new Dice(DiceColor.ORANGE));
		playersOrder = players;
		decks= new ArrayList<Deck>();
		towersDTO = new TowersDTO();
		leaderCards = new ArrayList<LeaderCard>();
		playedLeaderCards = new ArrayList<LeaderCard>();
		excommunicationDeck= new ArrayList<ExcommunicationDeck>();
		excommunications = new HashMap<Integer, ExcommunicationCard>();
		
	}
	
	public Color getColorByName (String name) {
		for(Player player: playersOrder)
			if (player.getName().equals(name))
				return player.getColor();
		return null;
	}
	
	
	public void initSpaces (Resource[] resources) {
		
		ArrayList<Resource> temporaryBonus1= new ArrayList<Resource>();
		temporaryBonus1.add(resources[0]);
		ArrayList<Resource> temporaryBonus2= new ArrayList<Resource>();
		temporaryBonus2.add(resources[1]);
		ArrayList<Resource> temporaryBonus3= new ArrayList<Resource>();
		temporaryBonus3.add(resources[2]);
		ArrayList<Resource> temporaryBonus4= new ArrayList<Resource>();
		temporaryBonus4.add(resources[3]);
		ArrayList<Resource> temporaryBonus5= new ArrayList<Resource>();
		temporaryBonus1.add(resources[4]);
		ArrayList<Resource> temporaryBonus6= new ArrayList<Resource>();
		temporaryBonus2.add(resources[5]);
		ArrayList<Resource> temporaryBonus7= new ArrayList<Resource>();
		temporaryBonus3.add(resources[6]);
		ArrayList<Resource> temporaryBonus8= new ArrayList<Resource>();
		temporaryBonus4.add(resources[7]);
		ArrayList<Resource> temporaryBonus9= new ArrayList<Resource>();
		temporaryBonus1.add(resources[8]);
		ArrayList<Resource> temporaryBonus10= new ArrayList<Resource>();
		temporaryBonus2.add(resources[9]);
		ArrayList<Resource> temporaryBonus11= new ArrayList<Resource>();
		temporaryBonus3.add(resources[10]);
		temporaryBonus3.add(resources[11]);
		ArrayList<Resource> temporaryBonus12= new ArrayList<Resource>();
		temporaryBonus4.add(resources[12]);
		temporaryBonus4.add(resources[13]);
		
		//devo leggere ed assegnare bonus da file
		spaces = new HashMap <String, ActionSpace> ();
		spaces.put("Harvest", new HarvestArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("Production", new ProductionArea (new SingleSlotActionSpace(1), new QueueActionSpace(1)));
		spaces.put("territoryTower", new TowerArea ());
		((TowerArea)spaces.get("territoryTower")).setBonus(temporaryBonus1,3);
		((TowerArea)spaces.get("territoryTower")).setBonus(temporaryBonus2,4);
		spaces.put("buildingTower", new TowerArea ());
		((TowerArea)spaces.get("buildingTower")).setBonus(temporaryBonus3,3);
		((TowerArea)spaces.get("buildingTower")).setBonus(temporaryBonus4,4);
		spaces.put("characterTower", new TowerArea ());
		((TowerArea)spaces.get("characterTower")).setBonus(temporaryBonus5,3);
		((TowerArea)spaces.get("characterTower")).setBonus(temporaryBonus6,4);
		spaces.put("ventureTower", new TowerArea ());
		((TowerArea)spaces.get("ventureTower")).setBonus(temporaryBonus7,3);
		((TowerArea)spaces.get("ventureTower")).setBonus(temporaryBonus8,4);
		spaces.put("FirstMarket", new MarketArea(1, temporaryBonus9));
		spaces.put("SecondMarket", new MarketArea(1, temporaryBonus10));
		spaces.put("ThirdMarket", new MarketArea(1,temporaryBonus11));
		spaces.put("FourthMarket", new MarketArea(1, temporaryBonus12));
		spaces.put("CouncilPalace", new CouncilPalaceArea(1));
		
	}
	
	public void initFaithTrack(Resource[] bonuses){
		
		faithTrack = new HashMap<Integer, FaithSpace>();
		for (int i = 0 ; i < 16; i++)
			faithTrack.put(i, new FaithSpace(bonuses[i]));
	}
	
	public ArrayList <Player> getPlayers () {
		return playersOrder;
	}

	public Player getPlayerByName (String playerName) {
		for (Player player: playersOrder)
			if(player.getName().equals(playerName))
				return player;
		return null;
	}
	
	public void setPlayers (ArrayList<Player> playersOrder) {
		this.playersOrder = playersOrder;	
	}

	public ActionSpace getSpace (String space) {
		return spaces.get(space);
	}
	
	public HashMap <String, ActionSpace> getSpaces () {
		return spaces;
	}
	
	public void changePlayerOrder () {
		Player temp = playersOrder.remove(0);
		playersOrder.add(temp);
		
	}

	public Deck getSpecificDeck(CardType type, Period period) {
		for (Deck deck: decks) {
			if ((deck.getType()==type)&&(deck.getPeriod().equals(period))) {
				return deck;
			}
	
		}
		return null;
	}

	public ArrayList<Dice> getDices(){
		return this.dices;
	}
	
	public ArrayList<Deck> getDecks(){
		return decks;
	}
	
	public ArrayList<ExcommunicationDeck> getExDecks(){
		return excommunicationDeck;
	}
	
	public ArrayList<LeaderCard> getLeaaderDeck(){
		return leaderCards;
	}
	
	public Player getCurrentPlayer (){
		return playersOrder.get(0);
	}
	
	public ExcommunicationCard getExcommunication(int round){
		return excommunications.get(round);
	}

	public int getExcommunicationTreshold( int round ){
		int tresHold = 0;
		switch (round) {
		case 2: tresHold = 3;
		       break;
		case 4 : tresHold = 4;
		       break;
		case 6 : tresHold = 5;
		       break;
		}
		return tresHold;
	}
	
	public Resource getVaticanBonus( int round){
		Resource bonus = null;
		switch (round) {
		case 2: bonus = faithTrack.get(3).getBonus();
		       break;
		case 4 : bonus = faithTrack.get(4).getBonus();
		       break;
		case 6 : bonus = faithTrack.get(5).getBonus();
		       break;
		}
		return bonus ;
	}
	
	public void randomExcommunication () {
		for(int i=0; i<excommunicationDeck.size(); i++) {
			int rnd= new Random().nextInt(excommunicationDeck.get(i).getDeck().size());
			excommunications.put(i+1, excommunicationDeck.get(i).getCard(rnd));
		}
	}
	
	
}

