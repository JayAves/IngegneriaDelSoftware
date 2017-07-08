package it.polimi.ingsw.ps29.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.ExcommunicationCardDTO;
import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBonusTileDTO;
import it.polimi.ingsw.ps29.DTO.ResourceDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.RestoreSituation;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.PersonalBoard;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.ResourceInterface;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;
import it.polimi.ingsw.ps29.model.space.Floor;
import it.polimi.ingsw.ps29.model.space.HarvestArea;
import it.polimi.ingsw.ps29.model.space.MarketArea;
import it.polimi.ingsw.ps29.model.space.ProductionArea;
import it.polimi.ingsw.ps29.model.space.TowerArea;
import it.polimi.ingsw.ps29.server.ClientThread;

/**
 * Creates all kind of messages and DTO. Only info to be shown, nothing has to come back to server.
 * @author Pietro Melzi
 * @author Pietro Grotti
 *
 */
public class CreationMessagesSupporter {
	
	public static TowersDTO createTowersDTO (Match model) {
		TowersDTO msg = new TowersDTO();
		String [] towersName = {"territoryTower", "buildingTower", "characterTower", "ventureTower"};
		String[] towersType = {"territory", "building", "character", "venure"};
		int i=-1; 
		for (String towerName: towersName) {
			i++;
			for (Floor floor: ((TowerArea)model.getBoard().getSpace(towerName)).getFloors()) {
				Card cardOnTower = floor.getCard();
				if(cardOnTower==null)
					msg.addCard(new CardDTO(-1, towersType[i], null));
				else
					msg.addCard(new CardDTO (cardOnTower.getId(), cardOnTower.getType(), cardOnTower.toString()));
			}
		}
		return msg;
	}
	
	public static int[] createDicesDTO (Match model) {
		int[] dices = new int[3];
		for (int i=0; i<model.getBoard().getDices().size(); i++)
			dices[i] = model.getBoard().getDices().get(i).getValue();
		return dices;
	}
	
	public static void initRoundMessagesForView (Match model, Map<String, ClientThread> views) {
		//show towers and dices to users
		TowersDTO towersForView = createTowersDTO(model);
		int[] dices = createDicesDTO(model);
		
		for(HashMap.Entry <String, ClientThread> view: views.entrySet()) 
			view.getValue().startInteraction(new TowersAndDicesForView(view.getValue().getClientName(), towersForView, dices));
	}
	
	private static GameBoardDTO createGameBoardDTO (GameBoard board) {
		GameBoardDTO boardDTO = new GameBoardDTO();
		
		boardDTO.insertSingleFamiliar("harvest", "head", 
				SupportGameBoardDTO.singleSpaceOccupant(((HarvestArea)board.getSpace("Harvest")).getHead()));
		boardDTO.insertQueueFamiliar("harvest", "queue", 
				SupportGameBoardDTO.queueSpaceOccupants(((HarvestArea)board.getSpace("Harvest")).getQueue()));
		
		boardDTO.insertSingleFamiliar("production", "head", 
				SupportGameBoardDTO.singleSpaceOccupant(((ProductionArea)board.getSpace("Production")).getHead()));
		boardDTO.insertQueueFamiliar("production", "queue", 
				SupportGameBoardDTO.queueSpaceOccupants(((ProductionArea)board.getSpace("Production")).getQueue()));
		
		boardDTO.insertSingleFamiliar("territoryTower", "first", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("territoryTower")).getFloors().get(0).getSpace()));
		boardDTO.insertSingleFamiliar("territoryTower", "second", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("territoryTower")).getFloors().get(1).getSpace()));
		boardDTO.insertSingleFamiliar("territoryTower", "third", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("territoryTower")).getFloors().get(2).getSpace()));
		boardDTO.insertSingleFamiliar("territoryTower", "fourth", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("territoryTower")).getFloors().get(3).getSpace()));
		
		boardDTO.insertSingleFamiliar("buildingTower", "first", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("buildingTower")).getFloors().get(0).getSpace()));
		boardDTO.insertSingleFamiliar("buildingTower", "second", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("buildingTower")).getFloors().get(1).getSpace()));
		boardDTO.insertSingleFamiliar("buildingTower", "third", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("buildingTower")).getFloors().get(2).getSpace()));
		boardDTO.insertSingleFamiliar("buildingTower", "fourth", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("buildingTower")).getFloors().get(3).getSpace()));
		
		boardDTO.insertSingleFamiliar("characterTower", "first", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("characterTower")).getFloors().get(0).getSpace()));
		boardDTO.insertSingleFamiliar("characterTower", "second", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("characterTower")).getFloors().get(1).getSpace()));
		boardDTO.insertSingleFamiliar("characterTower", "third", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("characterTower")).getFloors().get(2).getSpace()));
		boardDTO.insertSingleFamiliar("characterTower", "fourth", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("characterTower")).getFloors().get(3).getSpace()));

		boardDTO.insertSingleFamiliar("ventureTower", "first", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("ventureTower")).getFloors().get(0).getSpace()));
		boardDTO.insertSingleFamiliar("ventureTower", "second", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("ventureTower")).getFloors().get(1).getSpace()));
		boardDTO.insertSingleFamiliar("ventureTower", "third", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("ventureTower")).getFloors().get(2).getSpace()));
		boardDTO.insertSingleFamiliar("ventureTower", "fourth", 
				SupportGameBoardDTO.singleSpaceOccupant(((TowerArea)board.getSpace("ventureTower")).getFloors().get(3).getSpace()));

		boardDTO.insertSingleFamiliar("market", "first", 
				SupportGameBoardDTO.singleSpaceOccupant(((MarketArea)board.getSpace("FirstMarket")).getSlot()));
		boardDTO.insertSingleFamiliar("market", "second", 
				SupportGameBoardDTO.singleSpaceOccupant(((MarketArea)board.getSpace("SecondMarket")).getSlot()));
		boardDTO.insertSingleFamiliar("market", "third", 
				SupportGameBoardDTO.singleSpaceOccupant(((MarketArea)board.getSpace("ThirdMarket")).getSlot()));
		boardDTO.insertSingleFamiliar("market", "fourth", 
				SupportGameBoardDTO.singleSpaceOccupant(((MarketArea)board.getSpace("FourthMarket")).getSlot()));
		
		boardDTO.insertQueueFamiliar("council", "single", 
				SupportGameBoardDTO.queueSpaceOccupants(((CouncilPalaceArea)board.getSpace("CouncilPalace")).getQueue()));
		boardDTO.insertQueueFamiliar("noaction", "noaction", 
				SupportGameBoardDTO.queueSpaceOccupants(((CouncilPalaceArea)board.getSpace("NoAction")).getQueue()));
		
		return boardDTO;

	}
	
	private static PersonalBoardDTO createPersonalBoardDTO (PersonalBoard personalBoard, String name) {
		PersonalBoardDTO boardDTO = new PersonalBoardDTO(name, 
				new PersonalBonusTileDTO(personalBoard.getPersonalBonusTile().getId(), 
						personalBoard.getPersonalBonusTile().toString()));
		String[] types = {"territory", "building", "character", "venture"};
		for(String type: types)
			for(Card card: personalBoard.getCards(type))
				boardDTO.insertCard(new CardDTO(card.getId(), card.getType(), card.toString()));
		
		ArrayList<ResourceInterface> res = personalBoard.getResources().hashMapToArrayListResources();
		ArrayList<ResourceDTO> resDTO = new ArrayList<ResourceDTO>();
		for(ResourceInterface resInt: res)
			resDTO.add(new ResourceDTO(resInt.getType(), resInt.getAmount()));
		boardDTO.setResources(resDTO);
		
		return boardDTO;
	}
	
	public static void initGameMessagesForView (Match model, Map<String, ClientThread> views) {
		//build tile object
		HashMap <String, PersonalBonusTileDTO> tiles = new HashMap <String, PersonalBonusTileDTO> ();
		for (Player player: model.getBoard().getPlayers())
			tiles.put(player.getName(), new PersonalBonusTileDTO(
					player.getPersonalBoard().getPersonalBonusTile().getId(), 
					player.getPersonalBoard().getPersonalBonusTile().toString()));
		
		//build excommunication object
		ArrayList<ExcommunicationCardDTO> exCards = new ArrayList<ExcommunicationCardDTO>();
		for (int i=0; i<3; i++) {
			ExcommunicationCard exCard = model.getBoard().getExcommunication(2*(i+1));
			exCards.add(new ExcommunicationCardDTO(exCard.getId(), exCard.getPeriod(), exCard.toString()));
		}
		
		//build objects for towers and dices
		TowersDTO towersForView = createTowersDTO(model);
		int[] dices = createDicesDTO(model);
		
		
		
		//send message to users
		for(HashMap.Entry <String, ClientThread> view: views.entrySet()) {
			view.getValue().startInteraction(new FirstBoardInfo(view.getValue().getClientName(), tiles, exCards,
					new TowersAndDicesForView(view.getValue().getClientName(), towersForView, dices), 
					buildInitialResourcesState(model)));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
	}
	
	public static RestoreSituation restoreSituation (Match model, RestoreSituation msg) {
		msg.setGameBoard(createGameBoardDTO (model.getBoard()));
		
		//build tile object
		HashMap <String, PersonalBonusTileDTO> tiles = new HashMap <String, PersonalBonusTileDTO> ();
		for (Player player: model.getBoard().getPlayers())
			tiles.put(player.getName(), new PersonalBonusTileDTO(
					player.getPersonalBoard().getPersonalBonusTile().getId(), 
					player.getPersonalBoard().getPersonalBonusTile().toString()));
		
		//build excommunication object
		ArrayList<ExcommunicationCardDTO> exCards = new ArrayList<ExcommunicationCardDTO>();
		for (int i=0; i<3; i++) {
			ExcommunicationCard exCard = model.getBoard().getExcommunication(2*(i+1));
			exCards.add(new ExcommunicationCardDTO(exCard.getId(), exCard.getPeriod(), exCard.toString()));
		}
		
		//build objects for towers and dices
		TowersDTO towersForView = createTowersDTO(model);
		int[] dices = createDicesDTO(model);
		
		msg.setFirstInfo(new FirstBoardInfo(msg.getName(), tiles, exCards, 
				new TowersAndDicesForView(msg.getName(), towersForView, dices), buildInitialResourcesState(model)));
		
		for(Player player: model.getBoard().getPlayers())
			msg.setPersonalBoard(createPersonalBoardDTO(player.getPersonalBoard(), player.getName()));
		
		return msg;
	}
	
	private static HashMap<String, ArrayList<ResourceDTO>> buildInitialResourcesState (Match model) {
		//build initial resources state
		HashMap<String, ArrayList<ResourceDTO>> initialResources = new HashMap <String, ArrayList<ResourceDTO>> ();
		for(Player player: model.getBoard().getPlayers())
			initialResources.put(player.getName(), new ArrayList<ResourceDTO>());
		
		for(Player player: model.getBoard().getPlayers())
			for(ResourceInterface res: player.getPersonalBoard().getResources().getResources().values())
				initialResources.get(player.getName()).add(new ResourceDTO(res.getType(), res.getAmount()));
		
		return initialResources;
	}

}
