package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PrivilegesPanelCreator extends PanelCreator {
	
	public PrivilegesPanelCreator() {
		super();
	}

	@Override
	public JPanel createLeftPanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Chose the privilege!"));
		return panel;
	}

	@Override
	public JPanel createRightPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 5));
		
		for(int j=0; j<5; j++)
			panel.add(new JTextField("prova"));
		for(int j=0; j<5; j++)
			panel.add(new JTextField("ee"));
		
		
		return panel;
	}
}
