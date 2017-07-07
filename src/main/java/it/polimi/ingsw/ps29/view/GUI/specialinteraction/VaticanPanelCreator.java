package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.ps29.view.GUI.GUICore;

public class VaticanPanelCreator extends PanelCreator {

	VaticanPanelCreator(GUICore gui) {
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
		panel.add(yes);
		
		JButton no = new JButton("NO");
		panel.add(no);
		
		return panel;
	}

}
