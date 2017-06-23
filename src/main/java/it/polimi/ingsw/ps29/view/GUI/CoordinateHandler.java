package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Point;
import java.util.ArrayList;

public class CoordinateHandler {
	
	private ArrayList<CardCoordinates> cardCoords;

	public CoordinateHandler() {
		/*al termine della funzione:
		 *cardCoords.get(0) avrà le coordinate di territoryCard - floor 1
		 *cardCoords.get(1) avrà le coordinate di territoryCard - floor 2
		 *...
		 *cardCoords.get(4) avrà le coordinate di buildingCard - floor 1
		 *...
		 */
		
		int xStart = 16;
		int yBase = 20;
		int yStart = 20;
		int widthCard = 56;
		int heightCard = 81;
		int shiftWidth = 108;
		int shiftHeight = 90;
		
		cardCoords = new ArrayList<CardCoordinates>();
		for(int i=0; i<16; i++)
			cardCoords.add(null);
		for(int i=0; i<4; i++){
			for (int j=0; j<4; j++) {
				cardCoords.set((i+1)*4-(j+1), new CardCoordinates(xStart, yStart, widthCard, heightCard));
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
}
