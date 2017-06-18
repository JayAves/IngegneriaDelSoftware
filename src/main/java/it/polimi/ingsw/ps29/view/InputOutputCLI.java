package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.BonusPlacementEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.messages.Exchange;
import it.polimi.ingsw.ps29.view.messages.TowersForView;

public class InputOutputCLI implements InputOutput {
	
	private Scanner scanner;
	
	public InputOutputCLI () {
		scanner = new Scanner(System.in);
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
		
	}
	
	public int[] askTypeOfAction () {
		int[] choice = new int [2];
		choice[1]=0;
		System.out.println("\nInsert the number of the action you want to perform.");
		do {
			System.out.println(
				"\n1.Harvest" +
				"\n2.Production" +
				"\n3.Placement on TerritoriesTower" +
				"\n4.Placement on BuildingsTower" +
				"\n5.Placement on CharcatersTower" +
				"\n6.Placement in VenturesTower" +
				"\n7.Market space 1" +
				"\n8.Market space 2" +
				"\n9.Market space 3" +
				"\n10.Market space 4" +
				"\n11.Council palace" +
				"\n12.No placement" +
				"\nChoice: ");
			choice[0] = scanner.nextInt();
		} while (choice[0]<1 || choice[0]>12);
		
		if(choice[0]>=3 && choice[0]<=6) {
			do{
				choice[1] = askFloor();
			} while (choice[1]<1 || choice[1]>4);
		}
		return choice;
			
	}
	
	public int askNumberOfServants () {
		int number;
		System.out.println("\nInsert the number of servant you want to use: ");
		number = scanner.nextInt();
		return number;
	}
	
	public int askFloor () {
		int number;
		System.out.println("\nInsert the number of floor where you want to place: ");
		number = scanner.nextInt();
		return number;
	}
	
	public int askFamiliarColor () {
		int choice;
		System.out.println("\nInsert the color of the familiar member you want to use.");
		do {
			System.out.println(
				"\n1.Black" +
				"\n2.White" +
				"\n3.Orange" +
				"\n4.Neutral" +
				"\nChoice: ");
			choice = scanner.nextInt();
		} while (choice<1 || choice>4);
		return choice;
		
	}
	
	public Exchange askExchange (Exchange choice) {
		int i;
		ExchangeResourcesEffect er = choice.getExchange();
		do {
			for(i=0; i<er.getChoices().size(); i++) { //mostra le possibili scelte
				System.out.println("\n"+i+")");
				showExchangeOption(er.getChoices().get(i));
			}
			System.out.println("\n"+(i+1)+") No exchange");
			System.out.println("\nInsert choice: ");
			choice.setChoice(0, scanner.nextInt());
		} while (choice.getChoice(0)<0||choice.getChoice(0)>(i+1));
		
		if(er.getChoices().get(choice.getChoice(0)).getBooleanOut()) { //la scelta dell'utente prevede alternative tra le risorse da scambiare
			do {		
				for(i=0; i<er.getChoices().get(choice.getChoice(0)).getResOut().size(); i++) {
					System.out.println("\n"+i+")");
					System.out.println((er.getChoices().get(choice.getChoice(0)).getResOut().get(i)));
				}
				System.out.println("\nInsert choice OUT: ");
				choice.setChoice(1, scanner.nextInt());
			} while(choice.getChoice(1)<0 || choice.getChoice(1)>i);
		}
			
		if(er.getChoices().get(choice.getChoice(0)).getBooleanIn()) { //la scelta dell'utente prevede alternative tra le risorse da scambiare
			do {
				for(i=0; i<er.getChoices().get(choice.getChoice(0)).getResIn().size(); i++) {
					System.out.println("\n"+i+")");
					System.out.println((er.getChoices().get(choice.getChoice(0)).getResIn().get(i)));
				}
				System.out.println("\nInsert choice IN: ");
				choice.setChoice(2, scanner.nextInt());
			} while(choice.getChoice(2)<0 || choice.getChoice(2)>i);	
		}
		
		return choice;
		
	}
	
	public void showExchangeOption (ExchangeResourceHandler option) {
		System.out.println("OUT: \n");
		for(Resource res: option.getResOut())
			System.out.println(res);
		if(option.getBooleanOut())
			System.out.println("[optional]\n");
		System.out.println("IN: \n");
		for(Resource res: option.getResIn())
			System.out.println(res);
		if(option.getBooleanIn())
			System.out.println("[optional]\n");
	}

	@Override
	public void printBonusAction(BonusActionEffect effect) {
		System.out.println("\nBonus action value: "+effect.getValue());
		System.out.println("\nType of action: "+effect.getType());
		if(effect instanceof BonusPlacementEffect){
			System.out.println("\nDicount:\n");
			for (Resource res: ((BonusPlacementEffect) effect).getDiscount())
				System.out.println(res);
		}
		
	}

	@Override
	public ResourceType askSpecificPrivilege() {
		int choice;
		do{
			System.out.println("1) 1 wood - 1 stone\n");
			System.out.println("2) 2 servants\n");
			System.out.println("3) 2 coins\n");
			System.out.println("4) 2 military points\n");
			System.out.println("5) 1 faith point\n");
			System.out.println("Choice must be different by previous in this turn: ");
			choice = scanner.nextInt();
		} while (choice<1 || choice>5);
		
		ResourceType type = ResourceType.WOOD;
		switch (choice) {
			case 1:
				type = ResourceType.WOOD;
				break;
			case 2:
				type = ResourceType.SERVANT;
				break;
			case 3:
				type = ResourceType.COIN;
				break;
			case 4:
				type = ResourceType.MILITARY;
				break;
			case 5:
				type = ResourceType.FAITH;
				break;
		}
		
		return type; 
			
	}
	
	@Override
	public int askAboutExcommunication () {
		int choice;
		do {
			System.out.println("\nDo you want to support Vatican?");
			System.out.println("1) Yes");
			System.out.println("2) No");
			choice = scanner.nextInt();
		} while(choice<1 || choice>2);
		
		return choice;
		
		
	}

	@Override
	public void showInfo(GameBoardDTO gameBoardDTO, TowersDTO towersDTO, HashMap<String, PersonalBoardDTO> personalBoardsDTO) {
		System.out.println("Updated situation of the game: ");
		System.out.println(gameBoardDTO.toString());
		showTower(towersDTO);
		for (Entry<String, PersonalBoardDTO> personalBoardDTO: personalBoardsDTO.entrySet())
			System.out.println(personalBoardDTO.toString());
		
	}

	@Override
	public void showTower(TowersDTO msg) {
		for(HashMap.Entry <String, ArrayList<CardDTO>> tower: msg.getTowers().entrySet()) {
			System.out.println(tower.getKey());
			for(CardDTO card: tower.getValue())
				System.out.println(card.toString());
		}
		
	}

	
	

}
