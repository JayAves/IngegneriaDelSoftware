package it.polimi.ingsw.ps29.view.GUI.coordinates;

import java.awt.Point;
import java.util.ArrayList;

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
		 * al termine della prima parte di funzione:
		 *spaceCoords.get(0) avrà le coordinate di territorySpace - floor 1
		 *spaceCoords.get(1) avrà le coordinate di territorySpace - floor 2
		 *...
		 *spaceCoords.get(4) avrà le coordinate di buildingSpace - floor 1
		 *...
		 */
		
		double xStart = marginX + calculateValue (72, true);
		double yBase  = marginY + calculateValue(49, false);
		double yStart = yBase;
		double widthCard   = calculateValue (29, true);
		double heightCard  = calculateValue(24, false);
		double shiftWidth  = calculateValue (98, true);
		double shiftHeight = calculateValue(90, false);
		
		//aggiungo le coordinate degli spazi azione sulle torri
		spaceCoords = new ArrayList<Coordinates>();
		for(int i=0; i<16; i++)
			//mi permette di settare nell'ordine che voglio le coordinate nel ArrayList al ciclo successivo
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
		 * seconda parte della funzione:
		 * spaceCoords.get(16) avrà le coordinate di harvest
		 * spaceCoords.get(17) avrà le coordinate di production
		 * spaceCoords.get(18) avrà le coordinate di market1
		 * spaceCoords.get(19) avrà le coordinate di market2
		 * spaceCoords.get(20) avrà le coordinate di market3
		 * spaceCoords.get(21) avrà le coordinate di market4
		 * spaceCoords.get(22) avrà le coordinate di council palace
		 */
		
		//aggiungo le coordinate degli altri spazi azione
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
		return index<23 ? spaceCoords.get(index) : null;
	}
}