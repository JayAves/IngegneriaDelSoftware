package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import javax.swing.JPanel;

import it.polimi.ingsw.ps29.messages.InteractionMessage;

public abstract class PanelCreator {
	
	protected InteractionMessage msg;
	
	PanelCreator (InteractionMessage msg) {
		this.msg = msg;
	}
	
	PanelCreator () {
	}
	
	public InteractionMessage getMessage () {
		return msg;
	}
	
	abstract public JPanel createLeftPanel();
	abstract public JPanel createRightPanel();
}
