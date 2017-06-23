package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PrintTower extends JPanel {
	
		private BufferedImage tower;
		private ArrayList<BufferedImage> cards;
		
		public PrintTower(ArrayList<Integer> idCards, GUICore gui) {
			tower = loadImage("gameboard.png");
			setCards (idCards);
			addMouseListener(new MyMouseListener(gui));
			System.out.println(tower.getWidth()+" - "+tower.getHeight());
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
		public void paint(Graphics g) {
			g.drawImage(tower, 0, 0, getSize().width, getSize().height, null);
			
			int xStart = 16;
			int yBase = 20;
			int yStart = 20;
			int widthCard = 57;
			int heightCard = 86;
			int shiftWidth = 108;
			int shiftHeight = 92;
			
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					g.drawImage(cards.get((i+1)*4-(1+j)), xStart, yStart, widthCard, heightCard, null);
					yStart+=shiftHeight;
				}
				xStart+=shiftWidth;
				yStart = yBase;
			}
			
		}
		

}