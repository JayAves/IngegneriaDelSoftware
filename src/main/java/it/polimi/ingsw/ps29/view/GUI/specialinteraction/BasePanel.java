package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The model of panels used to handle interaction between server and user.
 * It is composed by a left panel where info are shown and a right panel for the interaction 
 * @author Pietro Melzi
 *
 */
public class BasePanel extends JFrame {

	
	private static final long serialVersionUID = 430086813582761130L;
	
	protected JPanel leftPanel;
	protected JPanel rightPanel;
	
	public BasePanel (JPanel leftPanel, JPanel rightPanel, String title) {
		setLayout(new GridLayout(1, 2));
		setTitle(title);
		
		this.leftPanel = leftPanel;
		this.rightPanel = rightPanel;
		
		add(leftPanel);
		add(rightPanel);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	

}
