package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.cards.Deck;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationDeck;
import it.polimi.ingsw.ps29.model.cards.LeaderCard;
import it.polimi.ingsw.ps29.model.cards.effects.Effect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.space.ActionSpace;
import it.polimi.ingsw.ps29.model.space.BonusInit;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;
import it.polimi.ingsw.ps29.model.space.FaithSpace;
import it.polimi.ingsw.ps29.model.space.HarvestArea;
import it.polimi.ingsw.ps29.model.space.MarketArea;
import it.polimi.ingsw.ps29.model.space.ProductionArea;
import it.polimi.ingsw.ps29.model.space.QueueActionSpace;
import it.polimi.ingsw.ps29.model.space.SingleSlotActionSpace;
import it.polimi.ingsw.ps29.model.space.TowerArea;

/**
 * Contains most of game's elements:
 * <ul>
 * 	<li> Dices
 * 	<li> ActionSpaces
 * 	<li> Players in Order
 * 	<li> LeaderCards played
 *	<li> Cards and ExcommunicationCards
 *	<li> Excommunications received
 *	<li> FaithTrack
 *</ul>
 * <br>
 * 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 *
 */
public class GameBoard{
	
	private ArrayList <Dice> dices;
	private HashMap <String, ActionSpace> spaces;
	private ArrayList<Player> playersOrder;
	private ArrayList<LeaderCard> leaderCards;
	private ArrayList<LeaderCard> playedLeaderCards;
	private ArrayList<Deck> decks;
	private ArrayList<ExcommunicationDeck> excommunicationDeck;
	private HashMap<Integer, ExcommunicationCard> excommunications;
	private HashMap<Integer, FaithSpace> faithTrack;
	private TowersDTO towersDTO;
	private ArrayList<Effect> LeaderEffectsActivated;
	
	
	
	/**
	 * Initializes all elements necessary for the game to begin.
	 * @param players list given from {@link Match}
	 */
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
	
	public PlayerColor getColorByName (String name) {
		for(Player player: playersOrder)
			if (player.getName().equals(name))
				return player.getColor();
		return null;
	}
	
	//init all spaces with certain bonuses
	public void initSpaces (BonusInit[] resources) {
				
		
		spaces = new HashMap <String, ActionSpace> ();
		if (playersOrder.size()==2) {
			spaces.put("Harvest", new HarvestArea (new SingleSlotActionSpace(1), new QueueActionSpace(1,true)));
			spaces.put("Production", new ProductionArea (new SingleSlotActionSpace(1), new QueueActionSpace(1, true)));
		}
		else {
			spaces.put("Harvest", new HarvestArea (new SingleSlotActionSpace(1), new QueueActionSpace(1,false)));
			spaces.put("Production", new ProductionArea (new SingleSlotActionSpace(1), new QueueActionSpace(1, false)));
		}
		spaces.put("territoryTower", new TowerArea ());
		((TowerArea)spaces.get("territoryTower")).setBonus(resources[0].getBonus(),3);
		((TowerArea)spaces.get("territoryTower")).setBonus(resources[1].getBonus(),4);
		spaces.put("buildingTower", new TowerArea ());
		((TowerArea)spaces.get("buildingTower")).setBonus(resources[4].getBonus(),3);
		((TowerArea)spaces.get("buildingTower")).setBonus(resources[5].getBonus(),4);
		spaces.put("characterTower", new TowerArea ());
		((TowerArea)spaces.get("characterTower")).setBonus(resources[2].getBonus(),3);
		((TowerArea)spaces.get("characterTower")).setBonus(resources[3].getBonus(),4);
		spaces.put("ventureTower", new TowerArea ());
		((TowerArea)spaces.get("ventureTower")).setBonus(resources[6].getBonus(),3);
		((TowerArea)spaces.get("ventureTower")).setBonus(resources[7].getBonus(),4);
		spaces.put("FirstMarket", new MarketArea(1, resources[8].getBonus(),false));
		spaces.put("SecondMarket", new MarketArea(1, resources[9].getBonus(),false));
		
		if (playersOrder.size()==4) {
			spaces.put("ThirdMarket", new MarketArea(1,resources[10].getBonus(),false));
			spaces.put("FourthMarket", new MarketArea(1, resources[11].getBonus(), false));
		}
		else {
			spaces.put("ThirdMarket", new MarketArea(1,resources[10].getBonus(),true));
			spaces.put("FourthMarket", new MarketArea(1, resources[11].getBonus(),true));
		}
	
		spaces.put("CouncilPalace", new CouncilPalaceArea(1));
		spaces.put("NoAction", new CouncilPalaceArea(0));
		
	}
	
	//gets bonuses from file faithTrack.json
	public void initFaithTrack(BonusInit[] bonuses){
		
		faithTrack = new HashMap<Integer, FaithSpace>();
		for (int i = 0 ; i < 16; i++)
			faithTrack.put(i, new FaithSpace(bonuses[i].getBonus()));
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
	
	public ArrayList<LeaderCard> getLeaderDeck(){
		return leaderCards;
	}
	
	/*public Player getCurrentPlayer (){
		return playersOrder.get(0);
	}*/
	
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
	
	public ArrayList<Resource> getVaticanBonus( int round){
		ArrayList<Resource> bonus = null;
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
			excommunications.put(2*(i+1), excommunicationDeck.get(i).getCard(rnd));
		}
	}
	
	public void assignPointsForMilitaryTrack(){
		ArrayList<Player> firstPlayers = new ArrayList<Player>();
		ArrayList<Player> secondPlayers = new ArrayList<Player>();
		firstPlayers.add(playersOrder.get(0));
		for (int i = 1; i < playersOrder.size(); i++){
			if (playersOrder.get(i).getPersonalBoard().getSpecificResource("military").getAmount() > firstPlayers.get(0).getPersonalBoard().getSpecificResource("military").getAmount()){
				secondPlayers = firstPlayers;
				firstPlayers.clear();
				firstPlayers.add(playersOrder.get(i));
			}
			else if(playersOrder.get(i).getPersonalBoard().getSpecificResource("military").getAmount() == firstPlayers.get(0).getPersonalBoard().getSpecificResource("military").getAmount())
				firstPlayers.add(playersOrder.get(i));
			else{
				if(secondPlayers.size() > 0){
					if(playersOrder.get(i).getPersonalBoard().getSpecificResource("military").getAmount() > secondPlayers.get(0).getPersonalBoard().getSpecificResource("military").getAmount()){
						secondPlayers.clear();
						secondPlayers.add(playersOrder.get(i));
					}
					else if(playersOrder.get(i).getPersonalBoard().getSpecificResource("military").getAmount() == secondPlayers.get(0).getPersonalBoard().getSpecificResource("military").getAmount())
							secondPlayers.add(playersOrder.get(i));
					}
				else
					secondPlayers.add(playersOrder.get(i));
			}
		}
		
		for (Player player : firstPlayers)
			player.getPersonalBoard().getResources().updateResource(new Resource("victory", 5));
		for (Player player : secondPlayers)
			player.getPersonalBoard().getResources().updateResource(new Resource("victory", 2));
	}
	
	
	
}

