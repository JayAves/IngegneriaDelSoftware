package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelBase extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 430086813582761130L;
	
	protected JPanel leftPanel;
	protected JPanel rightPanel;
	
	public PanelBase (JPanel leftPanel, JPanel rightPanel) {
		setLayout(new GridLayout(1, 2));
		
		this.leftPanel = leftPanel;
		this.rightPanel = rightPanel;
		
		add(leftPanel);
		add(rightPanel);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
		pack();
	}
	
	

}
