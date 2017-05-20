package it.polimi.ingsw.ps29.model.game;

import java.util.Random;

public class Dice {
	private int value;
	private DiceColor colour;
	
	public void rollDice(){
		
		Random random = new Random();
		value=random.nextInt(5)+1;
	}

	public Dice(DiceColor colour) {
		
		this.colour = colour;
	}


	
}
