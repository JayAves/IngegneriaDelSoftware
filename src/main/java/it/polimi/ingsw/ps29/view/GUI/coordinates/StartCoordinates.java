package it.polimi.ingsw.ps29.view.GUI.coordinates;

public class StartCoordinates {
	private int x, y, width, height, shiftX, shiftY, rows, cols;

	public StartCoordinates(int x, int y, int width, int height, int shiftX, int shiftY, int rows, int cols) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.shiftX = shiftX;
		this.shiftY = shiftY;
		this.rows = rows;
		this.cols = cols;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getShiftX() {
		return shiftX;
	}

	public int getShiftY() {
		return shiftY;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
	
	

}
