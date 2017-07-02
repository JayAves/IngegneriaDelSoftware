package it.polimi.ingsw.ps29.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.ExcommunicationCardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBonusTileDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.model.cards.Card;
import it.polimi.ingsw.ps29.model.cards.ExcommunicationCard;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.space.Floor;
import it.polimi.ingsw.ps29.model.space.TowerArea;
import it.polimi.ingsw.ps29.server.ClientThread;

public class CreationMessagesSupporter {
	
	public static TowersDTO createTowersDTO (Match model) {
		TowersDTO msg = new TowersDTO();
		String [] towersName = {"territoryTower", "buildingTower", "characterTower", "ventureTower"};
		for (String towerName: towersName)
			for (Floor floor: ((TowerArea)model.getBoard().getSpace(towerName)).getFloors()) {
				Card cardOnTower = floor.getCard();
				msg.addCard(new CardDTO (cardOnTower.getId(), cardOnTower.getType(), cardOnTower.toString()));
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
					new TowersAndDicesForView(view.getValue().getClientName(), towersForView, dices)));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
		}
		
	}
	

}
