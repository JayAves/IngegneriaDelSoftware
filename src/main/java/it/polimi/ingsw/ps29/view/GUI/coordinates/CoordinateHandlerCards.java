package it.polimi.ingsw.ps29.view.GUI.coordinates;

import java.awt.Point;
import java.util.ArrayList;

public class CoordinateHandlerCards {
	
	private ArrayList<Coordinates> cardCoords;

	public CoordinateHandlerCards(double imageHeight, double imageWidth, double marginX, double marginY, int rows, int cols) {
		/*
		 * al termine della funzione:
		 *cardCoords.get(0) avrà le coordinate di territoryCard - floor 1
		 *cardCoords.get(1) avrà le coordinate di territoryCard - floor 2
		 *...
		 *cardCoords.get(4) avrà le coordinate di characterCard - floor 1
		 *...
		 */
		
		double xStart = marginX + ((double)14/413)*imageWidth;
		double yBase = marginY + ((double)21/607)*imageHeight;
		double yStart = yBase;
		double widthCard = ((double)51/413)*imageWidth;
		double heightCard = ((double)85/607)*imageHeight;
		double shiftWidth = ((double)97/413)*imageWidth;
		double shiftHeight = ((double)91/607)*imageHeight;
		
		cardCoords = new ArrayList<Coordinates>();
		for(int i=0; i<rows*cols; i++)
			cardCoords.add(null);
		for(int i=0; i<cols; i++){
			for (int j=0; j<rows; j++) {
				cardCoords.set((i+1)*rows-(j+1), new Coordinates((int)xStart, (int)yStart, (int)widthCard, (int)heightCard));
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
}
