package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Prints on a panel an image. Used simply to show an image on the GUI but also like background image for other images that will be printed over
 * @author Pietro Melzi
 *
 */

public class ImageToPrint extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8468475933709036985L;

	double imageHeight, imageWidth, marginX, marginY, imageRatio;
	protected transient BufferedImage image;
	
	public ImageToPrint(String path) {
		image = loadImage(path);
	}
	
	public void setImage (String path) {
		image = loadImage(path);
		repaint();
	}
	
	
	protected BufferedImage loadImage(String path){
		BufferedImage result = null;
		try {
			result = ImageIO.read(new File ("images/"+path));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Could not load images");
			Thread.currentThread().interrupt();
		}
		return result;
	}
	
	/**
	 * Image height is calculated holding the ratio, as if the image fills completely the available width
	 * If the height calculated is greater than the available height the same operation is made calculating the width
	 */
	
	@Override
	protected void paintComponent(Graphics g) {
		
		imageRatio = (double)image.getHeight()/(double)image.getWidth();
		imageHeight = imageRatio*getSize().getWidth();
		
		if(imageHeight>getSize().getHeight()) { //altezza occupa tutto il panel
			imageWidth = getSize().getHeight()/imageRatio;
			imageHeight = getSize().getHeight();
			marginX = (getSize().getWidth()-imageWidth)/2;
			marginY = 0;
		}
		
		else { //larghezza occupa tutto il panel
			marginY = (getSize().getHeight()-imageHeight)/2;
			marginX = 0;
			imageWidth = getSize().getWidth();
		}
		
		g.clearRect(0, 0, 1000, 1000);
		g.drawImage(image, (int)marginX, (int)marginY, (int)imageWidth, (int)imageHeight, null);
		
	}
	
	public int getWidthImage () {
		return image.getWidth();
	}
	
	public int getHeightImage () {
		return image.getHeight();
	}
		

}