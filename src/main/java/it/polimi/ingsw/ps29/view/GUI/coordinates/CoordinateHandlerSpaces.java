package it.polimi.ingsw.ps29.view.GUI.coordinates;

import java.awt.Point;
import java.util.ArrayList;

public class CoordinateHandlerSpaces {
	
	private ArrayList<Coordinates> spaceCoords;

	public CoordinateHandlerSpaces(double imageHeight, double imageWidth, double marginX, double marginY) {
		/*al termine della funzione:
		 *spaceCoords.get(0) avrà le coordinate di territorySpace - floor 1
		 *spaceCoords.get(1) avrà le coordinate di territorySpace - floor 2
		 *...
		 *spaceCoords.get(4) avrà le coordinate di buildingSpace - floor 1
		 *...
		 */
		
		double xStart = marginX + ((double)72/413)*imageWidth;
		double yBase = marginY + ((double)49/607)*imageHeight;
		double yStart = yBase;
		double widthCard = ((double)29/413)*imageWidth;
		double heightCard = ((double)24/607)*imageHeight;
		double shiftWidth = ((double)98/413)*imageWidth;
		double shiftHeight = ((double)90/607)*imageHeight;
		
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
}