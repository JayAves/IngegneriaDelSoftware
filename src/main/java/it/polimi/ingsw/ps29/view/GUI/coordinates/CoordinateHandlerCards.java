package it.polimi.ingsw.ps29.view.GUI.coordinates;

import java.awt.Point;
import java.util.ArrayList;

public class CoordinateHandlerCards {
	
	private ArrayList<Coordinates> cardCoords;

	public CoordinateHandlerCards(double imageHeight, double imageWidth, double marginX, double marginY, 
			StartCoordinates startCoord, int originW, int originH) {
		/*
		 * al termine della funzione:
		 *cardCoords.get(0) avrà le coordinate di territoryCard - floor 1
		 *cardCoords.get(1) avrà le coordinate di territoryCard - floor 2
		 *...
		 *cardCoords.get(4) avrà le coordinate di characterCard - floor 1
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
