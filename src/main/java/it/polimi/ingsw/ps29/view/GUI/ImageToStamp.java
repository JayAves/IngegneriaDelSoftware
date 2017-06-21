package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageToStamp extends JPanel {
	
		private BufferedImage image;
		
		public ImageToStamp(String path) {
			image = loadImage(path);
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
			g.drawImage(image, 0, 0, getSize().width, getSize().height, null);
			
		}
		

}