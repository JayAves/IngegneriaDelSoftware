package it.polimi.ingsw.ps29.view.inputCLI;

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
import it.polimi.ingsw.ps29.messages.InfoForView;
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
import it.polimi.ingsw.ps29.view.InputOutput;

/**
 *
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @see it.polimi.ingsw.ps29.view.InputOutput
 *
 */

public class InputOutputCLI implements InputOutput {
	
	//Scanner scanner;
	private FakeScanner scanner;
	private int turnTimer; //tempo per completare l'azione
	private long timeStart; //memorizzo il tempo di inizio dell'azione
	private final static int DEFAULT_TIMER= 19000;

	
	public InputOutputCLI () {
		setTimer(DEFAULT_TIMER);
		
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
	
	
	private int[] askTypeOfAction () throws ExpiredTimeException {
		int[] choice = new int [2];
		choice[1]=0;
		System.out.println("\nInsert the number of the action you want to perform.");
		do {
			System.out.println(
				"\n1.Harvest" +
				"\n2.Production" +
				"\n3.Placement on TerritoriesTower" +
				"\n4.Placement on BuildingsTower" +
				"\n5.Placement on CharactersTower" +
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
	
	private int askNumberOfServants () throws ExpiredTimeException {
		int number;
		do {
			System.out.println("\nInsert the number of servant you want to use: ");
			number = scanner.nextInt();
		} while (number<0);
		
		return number;
	}
	
	private int askFloor () throws ExpiredTimeException {
		int number;
		System.out.println("\nInsert the number of floor where you want to place: ");
		number = scanner.nextInt();
		return number;
	}
	
	private int askFamiliarColor () throws ExpiredTimeException {
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
	
	public Exchange askExchange (Exchange choice) throws ExpiredTimeException{
		timeStart = System.currentTimeMillis();
		int i;
		ExchangeResourcesEffect er = choice.getExchange();
		do {
			for(i=0; i<er.getChoices().size(); i++) { //mostra le possibili scelte di un'azione di scambio
				System.out.println("\n"+(i+1)+")");
				showExchangeOption(er.getChoices().get(i));
			}
			System.out.println("\n"+(i+1)+") No exchange");
			System.out.println("\nInsert choice: ");
			
			choice.setChoice(0, scanner.nextInt());
		
		} while (choice.getChoice(0)<0||choice.getChoice(0)>i+1);
		
		if(choice.getChoice(0)<er.getChoices().size())
			if(er.getChoices().get(choice.getChoice(0)).getBooleanOut()) { //la scelta dell'utente prevede alternative tra le risorse da scambiare
				do {		
					for(i=0; i<er.getChoices().get(choice.getChoice(0)).getResOut().size(); i++) {
						System.out.println("\n"+(i+1)+")");
						System.out.println((er.getChoices().get(choice.getChoice(0)).getResOut().get(i)));
					}
				
					System.out.println("\nInsert choice OUT: ");
					choice.setChoice(1, scanner.nextInt());
				
				} while(choice.getChoice(1)<0 || choice.getChoice(1)>i);
			}
			
		if(choice.getChoice(0)<er.getChoices().size())
			if( er.getChoices().get(choice.getChoice(0)).getBooleanIn()) { //la scelta dell'utente prevede alternative tra le risorse da scambiare
				do {
					for(i=0; i<er.getChoices().get(choice.getChoice(0)).getResIn().size(); i++) {
						System.out.println("\n"+(i+1)+")");
						System.out.println((er.getChoices().get(choice.getChoice(0)).getResIn().get(i)));
					}
					System.out.println("\nInsert choice IN: ");
					choice.setChoice(2, scanner.nextInt());
					
				} while(choice.getChoice(2)<0 || choice.getChoice(2)>i);	
			}
		
		return choice;
		
	}
	
	public void showExchangeOption (ExchangeResourceHandler option) {
		System.out.println("OUT: ");
		for(Resource res: option.getResOut())
			System.out.println(res);
		if(option.getBooleanOut())
			System.out.println("[optional]\n");
		System.out.println("IN: ");
		for(Resource res: option.getResIn())
			System.out.println(res);
		if(option.getBooleanIn())
			System.out.println("[optional]\n");
	}

	public void printBonusAction(BonusActionEffect effect) {
		System.out.println("Bonus action value: "+effect.getValue());
		System.out.println("Type of action: "+effect.getType());
		if(effect instanceof BonusPlacementEffect){
			System.out.println("Discount:");
			for (Resource res: ((BonusPlacementEffect) effect).getDiscount())
				System.out.println(res);
		}
		
	}

	@Override
	public ResourceType askSpecificPrivilege(boolean different) throws ExpiredTimeException {
		timeStart = System.currentTimeMillis();
		int choice;
		do{
			System.out.println("1) 1 wood - 1 stone\n");
			System.out.println("2) 2 servants\n");
			System.out.println("3) 2 coins\n");
			System.out.println("4) 2 military points\n");
			System.out.println("5) 1 faith point\n");
			if (different)
				System.out.println("Each choice must be different from the others made in this round: ");
			
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
	public int askAboutExcommunication () throws ExpiredTimeException {
		timeStart = System.currentTimeMillis();
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
	public void showInfo(InfoForView info, GameBoardDTO gameBoardDTO, TowersDTO towersDTO, HashMap<String, PersonalBoardDTO> personalBoardsDTO) {
		System.out.println("Updated situation of the game: ");
		System.out.println(gameBoardDTO.toString());
		//showTower(towersDTO);
		for (Entry<String, PersonalBoardDTO> personalBoardDTO: personalBoardsDTO.entrySet())
			System.out.println(personalBoardDTO.toString());
		
	}

	@Override
	public void showTowerAndDices(TowersAndDicesForView msg) {
		String[] color = {"Black", "White", "Orange"};
		System.out.println("\nDICES ROLLED: ");
		for(int i=0; i<color.length; i++)
			System.out.println("- "+color[i]+" dice: "+msg.getDices()[i]);
		for(HashMap.Entry <String, ArrayList<CardDTO>> tower: msg.getTowers().getTowers().entrySet()) {
			System.out.println("\n"+tower.getKey().toUpperCase()+" Tower:\n");
			int counter=1;
			for(CardDTO card: tower.getValue()) {
				System.out.println("Floor "+counter+": "+card.toString());
				counter++;
			}
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
					System.out.println( " " + (i + 1) + ") " + leaderSituation.get(i).get(1));
				
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
					for (int i=0; i < printCorrectOptions(leaderSituation.get(choice-1)).size() ; i++)
						System.out.println(" " + (i + 1) + " " + printCorrectOptions(leaderSituation.get(choice -1)).get(i));
					try {
						secondChoice = scanner.nextInt();
					} catch (Exception e) {
						System.err.println(e.getMessage());
						secondChoice = 0;
					}
				} while (secondChoice < 1 || secondChoice > printCorrectOptions(leaderSituation.get(choice-1)).size());
				
				// l'utente ha scelto cosa fare con la carta
				leaderSituation.get(choice -1).add(printCorrectOptions(leaderSituation.get(choice-1)).get(secondChoice -1));
			}
		}
		return leaderSituation;
	}
	
	private ArrayList<String> printCorrectOptions(ArrayList<Object> card){
		ArrayList<String> toShow = new ArrayList<String>();
		toShow.add("DO NOTHING");
		
		if (card.get(2).equals(0)){
			toShow.add("DISCARD");
			if ((boolean) card.get(3))
				toShow.add("PLAY");
		}
		
		if (card.get(2).equals(1))
				toShow.add("ACTIVATE");
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
		timeStart = System.currentTimeMillis();
		
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
	
	public int askTower () throws ExpiredTimeException {
		int choice;
		System.out.println("\nInsert the number of the action you want to perform :");
		do {
			System.out.println(
				"\n1.Placement on TerritoriesTower" +
				"\n2.Placement on BuildingsTower" +
				"\n3.Placement on CharactersTower" +
				"\n4.Placement in VenturesTower" +
				"\n5.No Action" +
				"\nChoice: ");
			
				choice= scanner.nextInt();
		
		} while (choice<1 || choice>5);
		
		return choice;
		
	}

	@Override
	public BonusChoice handleBonusAction(BonusChoice msg) throws ExpiredTimeException {
		timeStart = System.currentTimeMillis();
		BonusActionEffect effect = msg.getBonus();
		int choice = 0;
		printBonusAction (effect);
		
		do {
			System.out.println("Do you want to use Bonus Action? \n1)Yes\n2)No\nChoice:");
			choice = scanner.nextInt();
		} while(choice<1||choice>2);
		
		if (choice == 1) {
			if(effect.getType().equals("all")) {
				msg.setSpace(askTower());
				msg.setFloor(askFloor());
			}
				
			if(effect.getType().equals("territory")||effect.getType().equals("building")||
					effect.getType().equals("character")||effect.getType().equals("venture"))
				msg.setFloor(askFloor());
		
			msg.setServants(askNumberOfServants());
		} 
		
		return msg;
	}
	

	@Override
	public void setTimer(int timer) {
		turnTimer= timer;
		scanner = new FakeScanner(turnTimer, this);
	
	}
	
	public int getTimer() {
		return turnTimer;
	}

	@Override
	public long getTimeStart() {
		return timeStart;
	}
}
