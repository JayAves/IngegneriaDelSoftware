package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.polimi.ingsw.ps29.DTO.FamilyMemberDTO;
import it.polimi.ingsw.ps29.view.GUI.coordinates.CoordinateHandlerCards;
import it.polimi.ingsw.ps29.view.GUI.coordinates.CoordinateHandlerSpaces;
import it.polimi.ingsw.ps29.view.GUI.coordinates.Coordinates;
import it.polimi.ingsw.ps29.view.GUI.utilities.ConsoleMessages;

public class PrintTower extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3591511333427114163L;
	private BufferedImage tower;
	private ArrayList<BufferedImage> cards;
	double imageHeight, imageWidth, marginX, marginY, imageRatio;
	private CoordinateHandlerCards coordCards;
	private CoordinateHandlerSpaces coordSpaces;
	private HashMap <Integer, ArrayList<FamilyMemberDTO>> coordFamiliar;
	
	private int indexSpacePressed = -1;
	
	
	private class TowerListener extends MouseAdapter {
		
		private GUICore gui;
		
		
		public TowerListener(GUICore gui) {
			this.gui = gui;
		}
		
		
		@Override
		public void mouseClicked (MouseEvent event) {
			
			Point p = event.getPoint();
			System.out.println(p.getX()+" - "+p.getY());
			
			//indice da 0 a 15 che mi dice su quale carta ho cliccato
			int indexCard = coordCards.getIndexCard(p);
			
			//se non ho inizializzato le torri o se ho cliccato fuori dallo spazio delle carte non fa nulla
			if(gui.getTowers() != null && indexCard>-1 && indexCard<16) {
				int idCard = gui.getTowers().getIdCard(indexCard);
				gui.zoomImage(idCard);
			}
			
			//indice da 0 a 22 che mi dice su quale spazio ho cliccato
			int indexSpace = coordSpaces.getIndexSpace(p);
			
			//save index pressed and show msg on the console 
			if(gui.getTowers() != null && indexSpace>-1 && indexSpace<23) {
				indexSpacePressed = indexSpace;
				ConsoleMessages.writePlacement(indexSpace, gui.console);
			}
		}
			
	}
		
	public PrintTower(ArrayList<Integer> idCards, GUICore gui) {
		tower = loadImage("gameboard.png");
		setCards(idCards, true);
		addMouseListener(new TowerListener(gui));
		coordFamiliar = new HashMap<Integer, ArrayList<FamilyMemberDTO>>();				
	}
	
	public void setCards (ArrayList<Integer> idCards, boolean repaint) {
		cards = new ArrayList<BufferedImage> ();
		for(int id: idCards)
			if(id!=-1)
				cards.add(loadImage("cards/devcards_f_en_c_"+id+".png"));
			else 
				cards.add(loadImage("leader.jpg"));
		if(repaint)
			repaint();
	}
	
	private BufferedImage loadImage(String path){
		BufferedImage result = null;
		try {
			result = ImageIO.read(new File ("images/"+path));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public void paintComponent (Graphics g) {
		imageRatio = (double)tower.getHeight()/(double)tower.getWidth();
		imageHeight = imageRatio*getSize().getWidth();
		
		if(imageHeight>getSize().getHeight()) { //altezza occupa tutto il panel
			imageWidth = getSize().getHeight()/imageRatio;
			imageHeight = getSize().getHeight();
			marginX = (getSize().getWidth()-imageWidth)/2;
			marginY = 0;
			g.drawImage(tower, (int)marginX, 0, (int)imageWidth, (int)imageHeight, null);
		}
		
		else { //larghezza occupa tutto il panel
			marginY = (getSize().getHeight()-imageHeight)/2;
			marginX = 0;
			imageWidth = getSize().getWidth();
			g.drawImage(tower, 0, (int)marginY, (int)imageWidth, (int)imageHeight, null);
		}
		
		//dimensioni assolute utilizzate per piazzare le carte
		handlePosition(14,20,51,85,97,91, g);
	}
	
		
	
	public int getWidthImage () {
		return tower.getWidth();
	}
	
	public int getHeightImage () {
		return tower.getHeight();
	}
	
	private void handlePosition(int xStartAbs, int yBaseAbs, int widthAbs, int heightAbs, 
			int shiftWidthAbs, int shiftHeightAbs, Graphics g) {
		double xStart = marginX + ((double)xStartAbs/tower.getWidth())*imageWidth;
		double yBase = marginY + ((double)yBaseAbs/tower.getHeight())*imageHeight;
		double yStart = yBase;
		double widthCard = ((double)widthAbs/tower.getWidth())*imageWidth;
		double heightCard = ((double)heightAbs/tower.getHeight())*imageHeight;
		double shiftWidth = ((double)shiftWidthAbs/tower.getWidth())*imageWidth;
		double shiftHeight = ((double)shiftHeightAbs/tower.getHeight())*imageHeight;
		
		coordCards = new CoordinateHandlerCards(imageHeight, imageWidth, marginX, marginY);
		coordSpaces = new CoordinateHandlerSpaces(imageHeight, imageWidth, marginX, marginY);
		
		while(cards.size()<16) {
			//wait...
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				g.drawImage(cards.get((i+1)*4-(1+j)), (int) (xStart), (int) (yStart),
						(int) (widthCard), (int) (heightCard), null);
				yStart+=shiftHeight;
			}
			xStart+=shiftWidth;
			yStart = yBase;
		}
		
		printAllFam(g);
	}
	
	//add in coordFamiliar a FamilyMemberDTO using the index of the space
	//the familiar will be printed in paintComponent method
	public void showFamiliar(int index, FamilyMemberDTO fam) {
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
				//else TO_DO: show familiar used for no actions
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
		
		int coordX = space.getCoordX() + space.getWidth() *3/8;
		int coordY = space.getCoordY() + space.getWidth()*3/8;
		int width  = space.getWidth() *1/4;
		int height = space.getHeight()*1/4;
		
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
	
	public int getIndexSpacePressed () {
		return indexSpacePressed;
	}

}