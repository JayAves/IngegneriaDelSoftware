package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import javax.swing.JPanel;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.GUI.InputOutputGUI;

public abstract class PanelCreator {
	
	protected InteractionMessage msg;
	protected InputOutputGUI ioGUI;
	
	PanelCreator (InteractionMessage msg) {
		this.msg = msg;
	}
	
	PanelCreator (InputOutputGUI ioGUI) {
		this.ioGUI = ioGUI;
	}
	
	public InteractionMessage getMessage () {
		return msg;
	}
	
	abstract public JPanel createLeftPanel();
	abstract public JPanel createRightPanel();
}
