package it.polimi.ingsw.ps29.view;

import java.util.Scanner;

import it.polimi.ingsw.ps29.model.game.GameBoard;

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
				System.out.println("\nInsert floor: ");
				choice[1] = scanner.nextInt();
			} while (choice[1]<1 || choice[1]>4);
		}
		return choice;
			
	}
	
	public int askNumberOfServants () {
		int number;
		System.out.println("Insert the number of servant you want to use: ");
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

}
