package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import javax.swing.JPanel;

import it.polimi.ingsw.ps29.messages.InteractionMessage;
import it.polimi.ingsw.ps29.view.GUI.GUICore;

public abstract class PanelCreator {
	
	protected InteractionMessage msg;
	protected GUICore gui;
	
	PanelCreator (InteractionMessage msg) {
		this.msg = msg;
	}
	
	PanelCreator (GUICore gui) {
		this.gui = gui;
	}
	
	public InteractionMessage getMessage () {
		return msg;
	}
	
	abstract public JPanel createLeftPanel();
	abstract public JPanel createRightPanel();
}
