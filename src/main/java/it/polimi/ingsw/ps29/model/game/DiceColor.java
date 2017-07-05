package it.polimi.ingsw.ps29.model.game;

import java.awt.Color;

public enum DiceColor {	
	ORANGE (Color.ORANGE), BLACK (Color.BLACK), WHITE (Color.WHITE), NEUTRAL (Color.PINK), BONUS (Color.BLUE);
	
	private Color color;
	
	DiceColor (Color color) {
		this.color = color;
	}
	
	public Color getColor () {
		return color;
	}

}
