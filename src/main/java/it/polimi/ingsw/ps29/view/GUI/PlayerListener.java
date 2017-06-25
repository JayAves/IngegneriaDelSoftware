package it.polimi.ingsw.ps29.view.GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class PlayerListener extends MouseAdapter {
	
	GUICore gui;

	public PlayerListener(GUICore gui) {
		this.gui = gui;
	}
	
	@Override
	public void mouseClicked (MouseEvent event) {
		if(((JButton) event.getSource()).getText().equals("Do Action!")) {
			gui.console.setText("Familiar: "+gui.family.getSelection().getActionCommand()+
					"\n# Servants: "+gui.servants.getValue());
		}
	}
	
	

}
