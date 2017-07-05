package it.polimi.ingsw.ps29.view.GUI.coordinates;

import java.awt.Point;

public class Coordinates {
	
	private int coordX;
	private int coordY;
	private int width;
	private int height;
	
	public Coordinates(int coordX, int coordY, int width, int height) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.width = width;
		this.height = height;
	}
	
	public boolean isClickIntoArea (Point p) {
		return p.getX()>=coordX && p.getX()<=coordX+width && p.getY()>=coordY && p.getY()<=coordY+height;
	}

	@Override
	public String toString() {
		return "Coordinates [coordX=" + coordX + ", coordY=" + coordY + ", width=" + width + ", height=" + height
				+ "]";
	}

	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
	

}
