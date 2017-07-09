package it.polimi.ingsw.ps29.view.GUI.coordinates;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Container for spaces coordinates, calculated every time the tower image change its dimensions
 * @author Pietro Melzi
 *
 */
public class CoordinateHandlerSpaces {
	
	private final double WIDTH  = 413;
	private final double HEIGHT = 607;

	private double imageHeight;
	private double imageWidth;
	
	private ArrayList<Coordinates> spaceCoords;

	public CoordinateHandlerSpaces(double imageHeight, double imageWidth, double marginX, double marginY) {
		
		this.imageHeight = imageHeight;
		this.imageWidth  = imageWidth;
		
		/*
		 * after the first part of function:
		 *spaceCoords.get(0) will contain territorySpace - floor 1 coords
		 *spaceCoords.get(1) will contain territorySpace - floor 2 coords
		 *...
		 *spaceCoords.get(4) will contain characterSpace - floor 1 coords
		 *...
		 */
		
		double xStart = marginX + calculateValue (72, true);
		double yBase  = marginY + calculateValue(49, false);
		double yStart = yBase;
		double widthCard   = calculateValue (29, true);
		double heightCard  = calculateValue(24, false);
		double shiftWidth  = calculateValue (98, true);
		double shiftHeight = calculateValue(90, false);
		
		//calculate action space coords
		spaceCoords = new ArrayList<Coordinates>();
		for(int i=0; i<16; i++)
			spaceCoords.add(null);
		
		for(int i=0; i<4; i++){
			for (int j=0; j<4; j++) {
				spaceCoords.set((i+1)*4-(j+1), new Coordinates((int)xStart, (int)yStart, (int)widthCard, (int)heightCard));
				yStart+=shiftHeight;
			}
			xStart+=shiftWidth;
			yStart = yBase;
		}
		
		/*
		 * second part of function:
		 * spaceCoords.get(16) will contain harvest coords
		 * spaceCoords.get(17) will contain production coords
		 * spaceCoords.get(18) will contain market1 coords
		 * spaceCoords.get(19) will contain market2 coords
		 * spaceCoords.get(20) will contain market3 coords
		 * spaceCoords.get(21) will contain market4 coords
		 * spaceCoords.get(22) will contain council palace coords
		 */
		
		//adds the other action spaces coords
		createOtherSpaceCoord(marginX, marginY, 15,  557, 145, 32); //harvest
		createOtherSpaceCoord(marginX, marginY, 15,  495, 145, 32); //production
		createOtherSpaceCoord(marginX, marginY, 252, 487,  30, 30); //market1
		createOtherSpaceCoord(marginX, marginY, 299, 487,  30, 30); //market2
		createOtherSpaceCoord(marginX, marginY, 341, 499,  30, 30); //market3
		createOtherSpaceCoord(marginX, marginY, 374, 533,  30, 30); //market4
		createOtherSpaceCoord(marginX, marginY, 216, 386, 119, 43); //council
		
	}
	
	private void createOtherSpaceCoord (double marginX, double marginY, double x, double y, double w, double h) {
		double coordX = marginX + calculateValue (x, true);
		double coordY = marginY + calculateValue(y, false);
		double width  = calculateValue(w, true);
		double height = calculateValue(h, false);
		
		spaceCoords.add(new Coordinates((int)coordX, (int)coordY, (int)width, (int)height));
	}
	
	/**
	 * given a length of a specific element in the source image, calculate the length when the image is resized
	 * @param origin length in the original image
	 * @param width true if is an horizontal length
	 * @return
	 */
	private double calculateValue (double origin, boolean width) {
		return width ? (origin/WIDTH)*imageWidth : (origin/HEIGHT)*imageHeight;
	}

	public int getIndexSpace (Point point) {
		for(int i=0; i<spaceCoords.size(); i++) {
			if(spaceCoords.get(i).isClickIntoArea(point))
				return i;
		}
		return -1;
	}
	
	@Override
	public String toString () {
		String msg = "";
		for (Coordinates coord: spaceCoords)
			msg+=coord.toString()+"\n";
		return msg;
	}
	
	public Coordinates getSpaceCoord (int index) {
		return index>-1 && index<23 ? spaceCoords.get(index) : null;
	}
	
	//called for print familiar in queue spaces, return the coordinates of space between head and queue
	public double getSpaceHeadQueue () {
		return calculateValue(24, true);
	}
	
	public double getWidthSpace () {
		return calculateValue(29, true);
	}
	
	
}