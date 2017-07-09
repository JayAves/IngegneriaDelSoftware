package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps29.view.GUI.GUICore;

/**
 * Creates the panel used in vatican interactions
 * @author Pietro Melzi
 *
 */
public class VaticanPanelCreator extends PanelCreator {

	public VaticanPanelCreator(GUICore gui) {
		super(gui, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel createLeftPanel() {
		JPanel panel = new JPanel ();
		panel.add(new JLabel("Do you want to substain Vatican?"));
		return panel;
	}

	@Override
	public JPanel createRightPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		
		JButton yes = new JButton("YES");
		yes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.notifyInput(1);
		    	SwingUtilities.getWindowAncestor(panel).dispose();
				
			}
		});
		panel.add(yes);
		
		JButton no = new JButton("NO");
		no.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.notifyInput(0);
		    	SwingUtilities.getWindowAncestor(panel).dispose();
				
			}
		});
		panel.add(no);
		
		return panel;
	}

}
