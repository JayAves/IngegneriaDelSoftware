package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FromBoardPanel extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1317355242851086223L;

	public FromBoardPanel (String title) {
		setTitle(title);
		setResizable(false);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public JLabel createSingleLabel (String path, int width, int height) {
		JLabel label = null;
		
		try {
			BufferedImage img = ImageIO.read(new File(path));
			Image imgScaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        ImageIcon icon = new ImageIcon(imgScaled);
	        label = new JLabel(icon);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return label;
	}

}
