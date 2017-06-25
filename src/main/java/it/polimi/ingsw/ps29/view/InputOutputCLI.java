package it.polimi.ingsw.ps29.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import it.polimi.ingsw.ps29.DTO.CardDTO;
import it.polimi.ingsw.ps29.DTO.ExcommunicationCardDTO;
import it.polimi.ingsw.ps29.DTO.GameBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBoardDTO;
import it.polimi.ingsw.ps29.DTO.PersonalBonusTileDTO;
import it.polimi.ingsw.ps29.DTO.TowersDTO;
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.messages.FirstBoardInfo;
import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.messages.PlayerInfoMessage;
import it.polimi.ingsw.ps29.messages.RejectMessage;
import it.polimi.ingsw.ps29.messages.TowersAndDicesForView;
import it.polimi.ingsw.ps29.messages.exception.ExpiredTimeException;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.BonusPlacementEffect;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.inputCLI.FakeScanner;

public class InputOutputCLI implements InputOutput {
	
	//Scanner scanner;
	private FakeScanner scanner;
	private int turnTimer;
	
	public InputOutputCLI () {
		setTimer(19000);
		
	}

	@Override
	public void showMessage(InteractionMessage message) {
		
		if (message instanceof RejectMessage)
			System.out.println(((RejectMessage)message).getException().getMessage());
		
		if (message instanceof PlayerInfoMessage) {
			
				if (!((PlayerInfoMessage) message).getTimeExpired())
					System.out.println( "\nPlayer "+ ((PlayerInfoMessage) message).getName().toUpperCase()+" has left the Game!\n\n");
				
			else
				
				System.out.println("\nPlayer "+ ((PlayerInfoMessage) message).getName().toUpperCase()+"'s time for action expired! he's now suspended from game\n\n");
			
			
				}
		
	}
	
	
	public int[] askTypeOfAction () throws ExpiredTimeException {
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
				"\n13.LeaderAction"+
				"\nChoice: ");
			
				choice[0] = scanner.nextInt();
		
		} while (choice[0]<1 || choice[0]>13);
		
		if(choice[0]>=3 && choice[0]<=6) {
			do{
				choice[1] = askFloor();
			} while (choice[1]<1 || choice[1]>4);
		}
			
		return choice;
			
	}
	
	public int askNumberOfServants () throws ExpiredTimeException {
		int number;
		System.out.println("\nInsert the number of servant you want to use: ");
		number = scanner.nextInt();
		
		return number;
	}
	
	public int askFloor () throws ExpiredTimeException {
		int number;
		System.out.println("\nInsert the number of floor where you want to place: ");
		number = scanner.nextInt();
		return number;
	}
	
	public int askFamiliarColor () throws ExpiredTimeException {
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
			try {
				choice.setChoice(0, scanner.nextInt());
			} catch (Exception e) {
				System.err.println(e.getMessage());
				choice.setChoice(0, i+1);
			}
		} while (choice.getChoice(0)<0||choice.getChoice(0)>(i+1));
		
		if(er.getChoices().get(choice.getChoice(0)).getBooleanOut()) { //la scelta dell'utente prevede alternative tra le risorse da scambiare
			do {		
				for(i=0; i<er.getChoices().get(choice.getChoice(0)).getResOut().size(); i++) {
					System.out.println("\n"+i+")");
					System.out.println((er.getChoices().get(choice.getChoice(0)).getResOut().get(i)));
				}
				System.out.println("\nInsert choice OUT: ");
				try {
					choice.setChoice(1, scanner.nextInt());
				} catch (Exception e) {
					System.err.println(e.getMessage());
					choice.setChoice(1, 1);
				}
			} while(choice.getChoice(1)<0 || choice.getChoice(1)>i);
		}
			
		if(er.getChoices().get(choice.getChoice(0)).getBooleanIn()) { //la scelta dell'utente prevede alternative tra le risorse da scambiare
			do {
				for(i=0; i<er.getChoices().get(choice.getChoice(0)).getResIn().size(); i++) {
					System.out.println("\n"+i+")");
					System.out.println((er.getChoices().get(choice.getChoice(0)).getResIn().get(i)));
				}
				System.out.println("\nInsert choice IN: ");
				try {
					choice.setChoice(2, scanner.nextInt());
				} catch (Exception e) {
					System.err.println(e.getMessage());
					choice.setChoice(2, 0);
				}
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
			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				choice = 1;
			}
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
			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				choice = 2;
			}
		} while(choice<1 || choice>2);
		
		return choice;
		
		
	}

	@Override
	public void showInfo(GameBoardDTO gameBoardDTO, TowersDTO towersDTO, HashMap<String, PersonalBoardDTO> personalBoardsDTO) {
		System.out.println("Updated situation of the game: ");
		System.out.println(gameBoardDTO.toString());
		//showTower(towersDTO);
		for (Entry<String, PersonalBoardDTO> personalBoardDTO: personalBoardsDTO.entrySet())
			System.out.println(personalBoardDTO.toString());
		
	}

	@Override
	public void showTowerAndDices(TowersAndDicesForView msg) {
		String[] color = {"Black", "White", "Orange"};
		for(int i=0; i<color.length; i++)
			System.out.println("Value of "+color[i]+" dice: "+msg.getDices()[i]);
		for(HashMap.Entry <String, ArrayList<CardDTO>> tower: msg.getTowers().getTowers().entrySet()) {
			System.out.println("\n"+tower.getKey().toUpperCase()+"\n");
			for(CardDTO card: tower.getValue())
				System.out.println(card.toString());
		}
		
	}

	@Override
	public ArrayList<ArrayList<Object>> askLeader(ArrayList<ArrayList<Object>> leaderSituation) {
		// TODO Auto-generated method stub
		int choice = 0;
		int secondChoice = 0;
		
		while (choice != 5){
			do{
				for ( int i = 0 ; i < leaderSituation.size(); i++)
					System.out.println( " " + (i + 1) + " " + leaderSituation.get(i).get(1));
				
				System.out.println((" 5 No azione Leader"));
				try {
					choice = scanner.nextInt();
				} catch (Exception e) {
					System.err.println(e.getMessage());
					choice = 5;
				}
			}while( choice < 1 || choice > 5);
			
			if(choice!=5) {
				//l'utente ha scelto il leader
				do {
					for (int i=0; i < printCorrectOptions(leaderSituation.get(choice-1)).size(); i++)
						System.out.println(" " + (i + 1) + " " + printCorrectOptions(leaderSituation.get(choice -1)).get(i));
					try {
						secondChoice = scanner.nextInt();
					} catch (Exception e) {
						System.err.println(e.getMessage());
						secondChoice = 0;
					}
				} while (secondChoice < 1 || secondChoice > printCorrectOptions(leaderSituation.get(choice-1)).size());
				
				// l'utente ha scelto cosa fare con la carta
				
				leaderSituation.get(choice -1).add(4, printCorrectOptions(leaderSituation.get(choice-1)).get(secondChoice -1));
			}
		}
		return leaderSituation;
	}
	
	private ArrayList<String> printCorrectOptions(ArrayList<Object> card){
		ArrayList<String> toShow = new ArrayList<String>();
		if ((int)card.get(2) == 0){
			toShow.add(" DISCARD");
			if ((boolean) card.get(3))
				toShow.add("PLAY");
		}
		if ((boolean) card.get(3))
				toShow.add(" ACTIVATE");
		return toShow;
		}

	@Override
	public void showFirstInfo(FirstBoardInfo msg) {
		for(HashMap.Entry<String, PersonalBonusTileDTO> tile: msg.getTiles().entrySet())
			System.out.println(tile.getValue().toString());
		for(ExcommunicationCardDTO exCard: msg.getExCards())
			System.out.println(exCard.toString());
		
	}

	@Override
	public ActionChoice handleAskNextAction(ActionChoice msg) throws ExpiredTimeException {
		System.out.println("\n"+msg.getName().toUpperCase()+", It's your turn!\n");
		
		int[] temp = askTypeOfAction();
		msg.setChoice(0, temp[0]);
		msg.setChoice(1, temp[1]);
		if (temp[0] < 12)
			msg.setChoice(2, askNumberOfServants());
		if (temp[0] < 13)
			msg.setChoice(3, askFamiliarColor());
		if (temp[0] == 13)
			msg.setLeaderSituation(askLeader(msg.getLeaderSituation()));
		
		
		
		return msg;
		
	}

	@Override
	public BonusChoice handleBonusAction(BonusChoice msg) {
		BonusActionEffect effect = msg.getBonus();
		
		printBonusAction (effect);
		
		try {
			if(effect.getType().equals("territory")||effect.getType().equals("building")||
					effect.getType().equals("character")||effect.getType().equals("venture"))
				msg.setFloor(askFloor());
		
			msg.setServants(askNumberOfServants());
		
		} catch (ExpiredTimeException e) {
			System.out.println(e.getMessage()+"\n");
			msg.setFloor(1);
		}
		
		return msg;
	}
	

	@Override
	public void setTimer(int timer) {
		turnTimer= timer;
		scanner = new FakeScanner(turnTimer);
		System.out.println("Timer: "+turnTimer);
	}
	public int getTimer() {
		return turnTimer;
	}
}
