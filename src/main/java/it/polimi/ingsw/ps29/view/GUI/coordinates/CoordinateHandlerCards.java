package it.polimi.ingsw.ps29.view.GUI.coordinates;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Container for coordinates, calculated every time the panel where card must be printed changes its dimensions.
 * Coordinates are used to print cards in the same position over a background image every time the image is resized.
 * @author Pietro Melzi
 *
 */
public class CoordinateHandlerCards {
	
	private ArrayList<Coordinates> cardCoords;

	/**
	 * 
	 * @param imageHeight height of the content where cards are printed, changes when the window is resized
	 * @param imageWidth width of the content where cards are printed, changes when the window is resized
	 * @param marginX horizontal margin between content and image 
	 * @param marginY vertical margin
	 * @param startCoord @see StartCoordinates
	 * @param originW width of the file used in background
	 * @param originH height of the file used in background
	 */
	public CoordinateHandlerCards(double imageHeight, double imageWidth, double marginX, double marginY, 
			StartCoordinates startCoord, int originW, int originH) {
		/*
		 * in the end of function:
		 *cardCoords.get(0) will contain territoryCard - floor 1 coords
		 *cardCoords.get(1) will contain di territoryCard coords
		 *...
		 *cardCoords.get(4) will contain di characterCard - floor 1 coords
		 *...
		 */
		
		double xStart = marginX + ((double)startCoord.getX()/originW)*imageWidth;
		double yBase = marginY + ((double)startCoord.getY()/originH)*imageHeight;
		double yStart = yBase;
		double widthCard = ((double)startCoord.getWidth()/originW)*imageWidth;
		double heightCard = ((double)startCoord.getHeight()/originH)*imageHeight;
		double shiftWidth = ((double)startCoord.getShiftX()/originW)*imageWidth;
		double shiftHeight = ((double)startCoord.getShiftY()/originH)*imageHeight;
		
		cardCoords = new ArrayList<Coordinates>();
		for(int i=0; i<startCoord.getRows()*startCoord.getCols(); i++)
			cardCoords.add(null);
		for(int i=0; i<startCoord.getCols(); i++){
			for (int j=0; j<startCoord.getRows(); j++) {
				cardCoords.set((i+1)*startCoord.getRows()-(j+1), new Coordinates((int)xStart, (int)yStart, (int)widthCard, (int)heightCard));
				yStart+=shiftHeight;
			}
			xStart+=shiftWidth;
			yStart = yBase;
		}
	}



	public int getIndexCard (Point point) {
		for(int i=0; i<cardCoords.size(); i++) {
			if(cardCoords.get(i).isClickIntoArea(point))
				return i;
		}
		return -1;
	}
	
	@Override
	public String toString () {
		String msg = "";
		for (Coordinates coord: cardCoords)
			msg+=coord.toString()+"\n";
		return msg;
	}
	
	public int length() {
		return cardCoords.size();
	}
}
