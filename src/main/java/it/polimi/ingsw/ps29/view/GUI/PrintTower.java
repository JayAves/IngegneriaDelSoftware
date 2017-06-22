package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PrintTower extends JPanel {
	
		private BufferedImage tower;
		private BufferedImage card;
		
		public PrintTower() {
			tower = loadImage("gameboard.png");
			card = loadImage("devcards_f_en_c_1.png");
			addMouseListener(new MyMouseListener());
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
			g.drawImage(card, 16, 20, 56, 86, null);
			
		}
		

}