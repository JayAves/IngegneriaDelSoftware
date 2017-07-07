package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.GUI.GUICore;

public abstract class PanelCreator {
	
	protected InteractionMessage msg;
	protected GUICore gui;
	
	PanelCreator (GUICore gui, InteractionMessage msg) {
		this.gui = gui;
		this.msg = msg;
	}
	
	public InteractionMessage getMessage () {
		return msg;
	}
	
	abstract public JPanel createLeftPanel();
	abstract public JPanel createRightPanel();
	
	public JLabel createSingleLabel (String path) {
		JLabel label = null;
		
		try {
			BufferedImage img = ImageIO.read(new File(path));
			Image imgScaled = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	        ImageIcon icon = new ImageIcon(imgScaled);
	        label = new JLabel(icon);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return label;
	}
}
