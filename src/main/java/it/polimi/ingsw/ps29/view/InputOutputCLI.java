package it.polimi.ingsw.ps29.view;

import java.util.Scanner;

import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.model.game.GameBoard;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.view.usermessages.UserExchange;

public class InputOutputCLI implements InputOutput {
	
	private Scanner scanner;
	
	public InputOutputCLI () {
		scanner = new Scanner(System.in);
	}
	
	@Override
	public void showUpdatedSituation(GameBoard board) {
		// TODO Auto-generated method stub
		
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
	
	public UserExchange askExchange (ExchangeResourcesEffect er) {
		int i;
		UserExchange choice = new UserExchange();
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
	

}
