package it.polimi.ingsw.ps29.view.GUI.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class GUIUtilities {
	
	public static void writePlacement (int index, JTextArea console) {
		switch (index) {
		case 0:
			console.append("\nPlacement in TERRITORY TOWER - FIRST FLOOR\n");
			break;
		case 1:
			console.append("\nPlacement in TERRITORY TOWER - SECOND FLOOR\n");
			break;
		case 2:
			console.append("\nPlacement in TERRITORY TOWER - THIRD FLOOR\n");
			break;
		case 3:
			console.append("\nPlacement in TERRITORY TOWER - FOURTH FLOOR\n");
			break;
		case 4:
			console.append("\nPlacement in CHARACTER TOWER - FIRST FLOOR\n");
			break;
		case 5:
			console.append("\nPlacement in CHARACTER TOWER - SECOND FLOOR\n");
			break;
		case 6:
			console.append("\nPlacement in CHARACTER TOWER - THIRD FLOOR\n");
			break;
		case 7:
			console.append("\nPlacement in CHARACTER TOWER - FOURTH FLOOR\n");
			break;
		case 8:
			console.append("\nPlacement in BUILDING TOWER - FIRST FLOOR\n");
			break;
		case 9:
			console.append("\nPlacement in BUILDING TOWER - SECOND FLOOR\n");
			break;
		case 10:
			console.append("\nPlacement in BUILDING TOWER - THIRD FLOOR\n");
			break;
		case 11:
			console.append("\nPlacement in BUILDING TOWER - FOURTH FLOOR\n");
			break;
		case 12:
			console.append("\nPlacement in VENTURE TOWER - FIRST FLOOR\n");
			break;
		case 13:
			console.append("\nPlacement in VENTURE TOWER - SECOND FLOOR\n");
			break;
		case 14:
			console.append("\nPlacement in VENTURE TOWER - THIRD FLOOR\n");
			break;
		case 15:
			console.append("\nPlacement in VENTURE TOWER - FOURTH FLOOR\n");
			break;
		case 16:
			console.append("\nPlacement in HARVEST SPACE\n");
			break;
		case 17:
			console.append("\nPlacement in PRODUCTION SPACE\n");
			break;
		case 18:
			console.append("\nPlacement in MARKET SPACE #1\n");
			break;
		case 19:
			console.append("\nPlacement in MARKET SPACE #2\n");
			break;
		case 20:
			console.append("\nPlacement in MARKET SPACE #3\n");
			break;
		case 21:
			console.append("\nPlacement in MARKET SPACE #4\n");
			break;
		case 22:
			console.append("\nPlacement in COUNCIL PALACE SPACE\n");
			break;
		}
		
		console.append("Choose familiar and number of servants and press Do Action!\n");
	}
	
	public static JLabel createSingleLabel (String path, int width, int height) {
		JLabel label = null;
		
		try {
			BufferedImage img = ImageIO.read(new File(path));
			Image imgScaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        ImageIcon icon = new ImageIcon(imgScaled);
	        label = new JLabel(icon);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Failed to read image from ImageIO");
			Thread.currentThread().interrupt();
		}
		
		return label;
	}
	
	public static JLabel createSingleLabelNoDim (String path) {
		JLabel label = null;
		
		try {
			BufferedImage img = ImageIO.read(new File(path));
	        ImageIcon icon = new ImageIcon(img);
	        label = new JLabel(icon);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Failed to read image from ImageIO");
			Thread.currentThread().interrupt();
		}
		
		return label;
	}


}
