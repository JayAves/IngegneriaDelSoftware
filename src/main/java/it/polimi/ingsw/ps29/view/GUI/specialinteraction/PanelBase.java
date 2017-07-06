package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.GridLayout;

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
		
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

}
