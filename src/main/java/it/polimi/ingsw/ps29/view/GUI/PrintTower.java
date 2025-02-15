package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.ps29.DTO.FamilyMemberDTO;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.view.GUI.coordinates.CoordinateHandlerSpaces;
import it.polimi.ingsw.ps29.view.GUI.coordinates.Coordinates;
import it.polimi.ingsw.ps29.view.GUI.coordinates.StartCoordinates;
import it.polimi.ingsw.ps29.view.GUI.utilities.GUIUtilities;

/**
 * Prints the tower filled with cards and calculates cards and spaces coords every time window is resized 
 * @author Pietro Melzi
 *
 */

public class PrintTower extends PrintPersonal {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3591511333427114163L;
	
	private transient CoordinateHandlerSpaces coordSpaces;
	private HashMap <Integer, ArrayList<FamilyMemberDTO>> coordFamiliar;
	private ArrayList<Integer> valueDices = new ArrayList<Integer>();
	private ArrayList<PlayerColor> playerOrder = new ArrayList<PlayerColor>();
	private int orW, orH;
	
	private int indexSpacePressed = -1;
	
	
	protected class TowerListener extends BoardListener {
		
		public TowerListener(GUICore gui) {
			super(gui);
		}
		
		@Override
		public void mouseClicked (MouseEvent event) {
			
			super.mouseClicked(event);
			Point p = event.getPoint();
			//index between 0 and 22 relative to the space clicked
			int indexSpace = coordSpaces.getIndexSpace(p);
			
			//save index pressed and show msg on the console 
			if(gui.getTowers() != null && indexSpace>-1 && indexSpace<23) {
				indexSpacePressed = indexSpace;
				GUIUtilities.writePlacement(indexSpace, gui.console);
			}
		}
			
	}
	
	public PrintTower(String path, GUICore gui, StartCoordinates startCoord, int orW, int orH) {
		super(path, gui, startCoord, orW, orH);
		this.orH = orH;
		this.orW = orW;
		addMouseListener(new TowerListener(gui));
		this.startCoord = startCoord;
		coordFamiliar = new HashMap<Integer, ArrayList<FamilyMemberDTO>>();	
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		printDices(g);
		printOrder(g);
		
		handleTower(startCoord.getX(), startCoord.getY(), startCoord.getWidth(), startCoord.getHeight(),
				startCoord.getShiftX(), startCoord.getShiftY(), g);
	}
	
	private void handleTower(int xStartAbs, int yBaseAbs, int widthAbs, int heightAbs, 
			int shiftWidthAbs, int shiftHeightAbs, Graphics g) {
		
		coordSpaces = new CoordinateHandlerSpaces(imageHeight, imageWidth, marginX, marginY);		
		printAllFam(g);
	}
	
	//add in coordFamiliar a FamilyMemberDTO using the index of the space
	//the familiar will be printed in paintComponent method
	public void showFamiliar(int index, FamilyMemberDTO fam) {
		if(fam.getFamiliarColor() != DiceColor.BONUS || (index>-1 && index<16))
			if(coordFamiliar.get(index)!= null)
				coordFamiliar.get(index).add(fam);
			
			else {
				ArrayList<FamilyMemberDTO> arrFam = new ArrayList<FamilyMemberDTO>();
				arrFam.add(fam);
				coordFamiliar.put(index, arrFam);
			}
		
	}
	
	private void printAllFam(Graphics g) {
		for(Map.Entry<Integer, ArrayList<FamilyMemberDTO>> famInSpace: coordFamiliar.entrySet()) {
			
			if(famInSpace.getKey()!=16 && famInSpace.getKey()!=17 && famInSpace.getKey()!=22) {
				//function to print in single spaces
				
				Coordinates space = coordSpaces.getSpaceCoord(famInSpace.getKey());
				if(space!=null)
					//print each familiar on its position
					printSingleFamiliar(space, famInSpace.getValue().get(0), g);
				
				else {
					//TO_DO: show familiar used for no actions
				}
			}
				
			else {
				//function to print in harvest, production or council spaces
				int widthSingleSlot = (int)coordSpaces.getWidthSpace();
				
				Coordinates space = coordSpaces.getSpaceCoord(famInSpace.getKey());
				
				if(!famInSpace.getValue().isEmpty()) {
					//first familiar in queue
					space = new Coordinates(space.getCoordX(), space.getCoordY(), 
							widthSingleSlot, widthSingleSlot);
					printSingleFamiliar(space, famInSpace.getValue().get(0), g);
					
					if(famInSpace.getValue().size()>1)
						printQueue(famInSpace.getValue(), space, g, famInSpace.getKey()==22 ? 1 : 2);
					
				}
				
			}
		}
		
	}
	
	
	private void printSingleFamiliar (Coordinates space, FamilyMemberDTO fam, Graphics g) {
		//print outer circle with player color
		g.setColor(fam.getPlayerColor().getColor());
		g.fillOval(space.getCoordX(), space.getCoordY(), space.getWidth(), space.getWidth());
		
		//print inner circle with familiar color
		g.setColor(fam.getFamiliarColor().getColor());
		
		int coordX = space.getCoordX() + space.getWidth() *5/16;
		int coordY = space.getCoordY() + space.getWidth()*5/16;
		int width  = space.getWidth() *3/8;
		
		g.fillOval(coordX, coordY, width, width);
	}
	
	/*
	 * valueToStart is the value used to do subList
	 * 
	 * valueToStart = 1 for council palace
	 * valueToStart = 2 for harvest and production
	 */
	private void printQueue(ArrayList<FamilyMemberDTO> famInSpace, Coordinates space, Graphics g, int valueToStart ) {
		int offset = (int)coordSpaces.getSpaceHeadQueue();
		int widthSingleSlot = (int)coordSpaces.getWidthSpace();
		
		if (valueToStart==2) {
			space = new Coordinates(space.getCoordX()+widthSingleSlot+offset, space.getCoordY(),
					widthSingleSlot, widthSingleSlot);
			printSingleFamiliar(space, famInSpace.get(1), g);
		}
		
		ArrayList<FamilyMemberDTO> supportList = new ArrayList<FamilyMemberDTO>();
		for (int i = valueToStart; i<famInSpace.size(); i++)
			supportList.add(famInSpace.get(i));
		
		for (FamilyMemberDTO famQueue: supportList) {
			space = new Coordinates(space.getCoordX()+widthSingleSlot, space.getCoordY(), 
					widthSingleSlot, widthSingleSlot);
			printSingleFamiliar(space, famQueue, g);
		}
	}
	
	private void printDices (Graphics g) {
		double x =  marginX + (254*imageWidth)/orW;
		double y =  marginY + (582*imageHeight)/orH;
		int shift = (int) (47*imageWidth)/orW;
		
		for (int i=0; i<valueDices.size(); i++) {
			if(i==0)
				g.setColor(Color.WHITE);
			else
				g.setColor(Color.BLACK);
			
			g.drawString(valueDices.get(i)+"",(int)x, (int)y);
			x+=shift;
		}		
	}
	
	private void printOrder (Graphics g) {
		double x =  marginX + (368*imageWidth)/orW;
		double y =  marginY + (367*imageHeight)/orH;
		int shift = (int) (28*imageHeight)/orH;
		int width = (int) (22*imageWidth)/orW;
		
		for(PlayerColor color: playerOrder) {
			g.setColor(color.getColor());
			g.fillOval((int)x, (int)y, width, width);
			y+=shift;
		}
		
	}
	
	public void cleanCoordFamiliar () {
		coordFamiliar = new HashMap<Integer, ArrayList<FamilyMemberDTO>>();	
	}
	
	public void setValueDices (ArrayList<Integer> values) {
		valueDices = values;
	}
	
	public void setPlayerOrder (ArrayList<PlayerColor> order) {
		playerOrder = order;
	}
	
	public int getIndexSpacePressed () {
		return indexSpacePressed;
	}
	
	public void deleteIndexSpacePressed () {
		indexSpacePressed = -1;
	}

}