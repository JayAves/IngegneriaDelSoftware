package it.polimi.ingsw.ps29.model.game;

import java.awt.Color;

public enum PlayerColor {
	/**
	 * @author Pietro Melzi
	 * @author Pietro Grotti
	 * @author Giovanni Mele
	 *
	 */
	
	GREEN (Color.GREEN), RED (Color.RED), YELLOW (Color.YELLOW), BLUE (Color.BLUE);
	
	private final Color color;
	
	PlayerColor (Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	
	
	

}
