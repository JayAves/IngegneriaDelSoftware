package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Point;

public class CardCoordinates {
	
	private int coordX;
	private int coordY;
	private int width;
	private int height;
	
	public CardCoordinates(int coordX, int coordY, int width, int height) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.width = width;
		this.height = height;
	}
	
	public boolean isClickIntoCard (Point p) {
		return p.getX()>=coordX && p.getX()<=coordX+width && p.getY()>=coordY && p.getY()<=coordY+height;
	}

	@Override
	public String toString() {
		return "CardCoordinates [coordX=" + coordX + ", coordY=" + coordY + ", width=" + width + ", height=" + height
				+ "]";
	}
	
	

}
