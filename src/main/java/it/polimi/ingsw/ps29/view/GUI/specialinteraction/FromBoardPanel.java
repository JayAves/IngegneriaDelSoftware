package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import javax.swing.JFrame;

/**
 * Standard for panels that are visible with a click on a button of the GUI
 * @author Pietro Melzi
 *
 */

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

}
