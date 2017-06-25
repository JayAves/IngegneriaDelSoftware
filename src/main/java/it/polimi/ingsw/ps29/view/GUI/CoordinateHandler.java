package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Point;
import java.util.ArrayList;

public class CoordinateHandler {
	
	private ArrayList<CardCoordinates> cardCoords;

	public CoordinateHandler(double imageHeight, double imageWidth, double marginX, double marginY) {
		/*al termine della funzione:
		 *cardCoords.get(0) avrà le coordinate di territoryCard - floor 1
		 *cardCoords.get(1) avrà le coordinate di territoryCard - floor 2
		 *...
		 *cardCoords.get(4) avrà le coordinate di buildingCard - floor 1
		 *...
		 */
		
		double xStart = marginX + ((double)14/413)*imageWidth;
		double yBase = marginY + ((double)21/607)*imageHeight;
		double yStart = yBase;
		double widthCard = ((double)51/413)*imageWidth;
		double heightCard = ((double)91/607)*imageHeight;
		double shiftWidth = ((double)97/413)*imageWidth;
		double shiftHeight = ((double)97/607)*imageHeight;
		
		cardCoords = new ArrayList<CardCoordinates>();
		for(int i=0; i<16; i++)
			cardCoords.add(null);
		for(int i=0; i<4; i++){
			for (int j=0; j<4; j++) {
				cardCoords.set((i+1)*4-(j+1), new CardCoordinates((int)xStart, (int)yStart, (int)widthCard, (int)heightCard));
				yStart+=shiftHeight;
			}
			xStart+=shiftWidth;
			yStart = yBase;
		}
	}



	public int getIndexCard (Point point) {
		for(int i=0; i<cardCoords.size(); i++) {
			if(cardCoords.get(i).isClickIntoCard(point))
				return i;
		}
		return -1;
	}
	
	@Override
	public String toString () {
		String msg = "";
		for (CardCoordinates coord: cardCoords)
			msg+=coord.toString()+"\n";
		return msg;
	}
}
