package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import it.polimi.ingsw.ps29.view.GUI.coordinates.CoordinateHandlerCards;
import it.polimi.ingsw.ps29.view.GUI.coordinates.StartCoordinates;

public class PrintPersonal extends ImageToPrint {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4636622009288644544L;
	
	protected StartCoordinates startCoord;
	protected CoordinateHandlerCards coordCards;
	private ArrayList<BufferedImage> cards;
	
	public PrintPersonal(String path, GUICore gui, StartCoordinates startCoord) {
		super(path);
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
			
			//indice che mi dice su quale carta ho cliccato
			int indexCard = coordCards.getIndexCard(p);
			
			//se non ho inizializzato le torri o se ho cliccato fuori dallo spazio delle carte non fa nulla
			if(gui.getTowers() != null && indexCard>-1 && indexCard<16) {
				int idCard = gui.getTowers().getIdCard(indexCard);
				gui.zoomImage(idCard);
			}
			
		}
	}
	
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		//dimensioni assolute utilizzate per piazzare le carte
		handlePosition(startCoord.getX(), startCoord.getY(), startCoord.getWidth(), startCoord.getHeight(),
				startCoord.getShiftX(), startCoord.getShiftY(), g);
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
	
	private void handlePosition(int xStartAbs, int yBaseAbs, int widthAbs, int heightAbs, 
			int shiftWidthAbs, int shiftHeightAbs, Graphics g) {
		
		double xStart = marginX + ((double)xStartAbs/image.getWidth())*imageWidth;
		double yBase = marginY + ((double)yBaseAbs/image.getHeight())*imageHeight;
		double yStart = yBase;
		double widthCard = ((double)widthAbs/image.getWidth())*imageWidth;
		double heightCard = ((double)heightAbs/image.getHeight())*imageHeight;
		double shiftWidth = ((double)shiftWidthAbs/image.getWidth())*imageWidth;
		double shiftHeight = ((double)shiftHeightAbs/image.getHeight())*imageHeight;
		
		coordCards = new CoordinateHandlerCards(imageHeight, imageWidth, marginX, marginY, 
				startCoord.getRows(), startCoord.getCols());
		
		if(cards!=null) {
			while(cards.size()<startCoord.getRows()*startCoord.getCols()) {
				//wait...
				try {
					Thread.sleep(100);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//print cards
			for (int i=0; i<startCoord.getCols(); i++) {
				for (int j=0; j<startCoord.getRows(); j++) {
					g.drawImage(cards.get((i+1)*startCoord.getRows()-(1+j)), (int) (xStart), (int) (yStart),
							(int) (widthCard), (int) (heightCard), null);
					yStart+=shiftHeight;
				}
				xStart+=shiftWidth;
				yStart = yBase;
			}
		}
	}
	

}
