package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.view.GUI.coordinates.CoordinateHandlerCards;
import it.polimi.ingsw.ps29.view.GUI.coordinates.StartCoordinates;

/**
 * This class extends ImageToPrint and it's used when cards must be printed over a background image
 * @author Pietro Melzi
 *
 */
public class PrintPersonal extends ImageToPrint {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4636622009288644544L;
	
	protected StartCoordinates startCoord;
	protected CoordinateHandlerCards coordCards;
	private ArrayList<BufferedImage> cards;
	private ArrayList<Integer> idCards = new ArrayList<Integer>();
	int orW, orH;
	
	public PrintPersonal(String path, GUICore gui, StartCoordinates startCoord, int width, int height) {
		super(path);
		orW = width;
		orH = height;
		addMouseListener(new BoardListener(gui));
		this.startCoord = startCoord;
	}
	
	protected class BoardListener extends MouseAdapter {
		
		protected GUICore gui;
		
		public BoardListener(GUICore gui) {
			this.gui = gui;
		}
		
		@Override
		public void mouseClicked (MouseEvent event) {
			
			Point p = event.getPoint();
			System.out.println(p.getX()+" - "+p.getY());
			
			//index of the card clicked
			int indexCard = coordCards.getIndexCard(p);
			
			//if towers aren't initialized or the click is out of cards space there's nothing to do
			if(!idCards.isEmpty() && indexCard>-1 && indexCard<coordCards.length()) 
				if(idCards.get(indexCard) != -1)
					gui.zoomImage(idCards.get(indexCard));
					
		}
	}

	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		handlePosition(startCoord.getX(), startCoord.getY(), startCoord.getWidth(), startCoord.getHeight(),
				startCoord.getShiftX(), startCoord.getShiftY(), g);
		
	}
	
	public void setCards (ArrayList<Integer> idCards, boolean repaint) {
		this.idCards = idCards;
		cards = new ArrayList<BufferedImage> ();
		for(int id: idCards)
			if(id!=-1)
				cards.add(loadImage("cards/devcards_f_en_c_"+id+".png"));
			else 
				cards.add(null);
		if(repaint)
			repaint();
	}
	
	private void handlePosition(int xStartAbs, int yBaseAbs, int widthAbs, int heightAbs, 
			int shiftWidthAbs, int shiftHeightAbs, Graphics g) {
		
		double xStart = marginX + ((double)xStartAbs/image.getWidth())*imageWidth;
		double yBase = marginY + ((double)yBaseAbs/image.getHeight())*imageHeight;
		double yStart = yBase;
		double widthCard = ((double)widthAbs/image.getWidth())*imageWidth;
		double heightCard = ((double)heightAbs/image.getHeight())*imageHeight;
		double shiftWidth = ((double)shiftWidthAbs/image.getWidth())*imageWidth;
		double shiftHeight = ((double)shiftHeightAbs/image.getHeight())*imageHeight;
		
		coordCards = new CoordinateHandlerCards(imageHeight, imageWidth, marginX, marginY, startCoord, orW, orH);
		
		if(cards!=null) {
			while(cards.size()<startCoord.getRows()*startCoord.getCols()) {
				//wait...
				try {
					Thread.sleep(100);
					
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			
			//print cards
			for (int i=0; i<startCoord.getCols(); i++) {
				for (int j=0; j<startCoord.getRows(); j++) {
					if(cards.get((i+1)*startCoord.getRows()-(1+j))!=null) 
						g.drawImage(cards.get((i+1)*startCoord.getRows()-(1+j)), (int) (xStart), (int) (yStart),
								(int) (widthCard), (int) (heightCard), null);
					
					yStart+=shiftHeight;
				}
				
				xStart+=shiftWidth;
				yStart = yBase;
			}
		}
	}
	

	public ArrayList<Integer> getIdCards () {
		return idCards;
	}
}
