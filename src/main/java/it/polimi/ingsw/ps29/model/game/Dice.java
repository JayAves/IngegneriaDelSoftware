package it.polimi.ingsw.ps29.model.game;

import java.util.Random;

public class Dice {
	private int value;
	private DiceColor color;
	
	public void rollDice(){
		
		Random random = new Random();
		value  = random.nextInt(5)+1;
	}

	public Dice(DiceColor color) {
		
		this.color = color;
	}

	public DiceColor getColor() {
		return color;
	}

	public int getValue() {
		return value;
	}

	

	


	
}
