package it.polimi.ingsw.ps29.view.GUI.coordinates;

/**
 * Container of useful values like the original coords and the original distances between cards 
 * @author Pietro Melzi
 *
 */
public class StartCoordinates {
	private int x, y, width, height, shiftX, shiftY, rows, cols;

	/**
	 * 
	 * @param x first horizontal point of the first card to be printed
	 * @param y first vertical point of the first card to be printed
	 * @param width width of the space where a card must be shown
	 * @param height height of the space where a card must be shown
	 * @param shiftX horizontal distance between the initial point of two consecutive cards 
	 * @param shiftY vertical distance between the initial point of two consecutive cards
	 * @param rows number of rows of cards that must be printed
	 * @param cols number of rows of cards that must be printed
	 */
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
