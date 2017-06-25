package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageToPrint extends JPanel {
	double imageHeight, imageWidth, marginX, marginY, imageRatio;
	
		private BufferedImage image;
		
		public ImageToPrint(String path, GUICore gui) {
			image = loadImage(path);
			addMouseListener(new PlayerListener(gui));
		}
		
		public void setImage (String path) {
			image = loadImage(path);
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
		protected void paintComponent(Graphics g) {
			/*super.paintComponent(g);
			g.drawImage(image, 0, 0, getSize().width, getSize().height, null);*/
			
			imageRatio = (double)image.getHeight()/(double)image.getWidth();
			imageHeight = imageRatio*getSize().getWidth();
			
			if(imageHeight>getSize().getHeight()) { //altezza occupa tutto il panel
				imageWidth = getSize().getHeight()/imageRatio;
				marginX = (getSize().getWidth()-imageWidth)/2;
				g.drawImage(image, (int)marginX, 0, (int)imageWidth, (int)getSize().getHeight(), null);
			}
			
			else { //larghezza occupa tutto il panel
				marginY = (getSize().getHeight()-imageHeight)/2;
				g.drawImage(image, 0, (int)marginY, (int)getSize().getWidth(), (int)imageHeight, null);
			}
			
		}
		
		public int getWidthImage () {
			return image.getWidth();
		}
		
		public int getHeightImage () {
			return image.getHeight();
		}
		

}