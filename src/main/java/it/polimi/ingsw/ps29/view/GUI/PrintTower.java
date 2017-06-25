package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PrintTower extends JPanel {
	
		private BufferedImage tower;
		private ArrayList<BufferedImage> cards;
		double imageHeight, imageWidth, marginX, marginY, imageRatio;
		private CoordinateHandler coord;
		
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
				int index = coord.getIndexCard(p);
				
				//se non ho inizializzato le torri o se ho cliccato fuori dallo spazio delle carte non fa nulla
				if(gui.getTowers() != null && index>-1 && index<16) {
					int idCard = gui.getTowers().getIdCard(index);
					gui.zoomImage(idCard);
				}
			}
			
		}
		
		public PrintTower(ArrayList<Integer> idCards, GUICore gui) {
			
			tower = loadImage("gameboard.png");
			setCards(idCards);
			addMouseListener(new TowerListener(gui));				
		}
		
		public void setCards (ArrayList<Integer> idCards) {
			cards = new ArrayList<BufferedImage> ();
			for(int id: idCards)
				cards.add(loadImage("cards/devcards_f_en_c_"+id+".png"));
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
			
			
			//System.out.println(xStart +" - "+yStart+" - "+widthCard+" - "+heightCard);
			/*g.drawImage(cards.get(0), (int) (xStart), (int) (yStart),
					(int) (widthCard), (int) (heightCard), null);*/
			
			double xStart = marginX + ((double)14/tower.getWidth())*imageWidth;
			double yBase = marginY + ((double)21/tower.getHeight())*imageHeight;
			double yStart = yBase;
			double widthCard = ((double)51/tower.getWidth())*imageWidth;
			double heightCard = ((double)91/tower.getHeight())*imageHeight;
			double shiftWidth = ((double)97/tower.getWidth())*imageWidth;
			double shiftHeight = ((double)97/tower.getHeight())*imageHeight;
			
			coord = new CoordinateHandler(imageHeight, imageWidth, marginX, marginY);
			
			
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					g.drawImage(cards.get((i+1)*4-(1+j)), (int) (xStart), (int) (yStart),
							(int) (widthCard), (int) (heightCard), null);
					yStart+=shiftHeight;
				}
				xStart+=shiftWidth;
				yStart = yBase;
			}
		}
		
			
		
		public int getWidthImage () {
			return tower.getWidth();
		}
		
		public int getHeightImage () {
			return tower.getHeight();
		}
		
		

}