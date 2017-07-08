package it.polimi.ingsw.ps29.view.GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.LeaderPanel;
import it.polimi.ingsw.ps29.view.GUI.specialinteraction.OtherCardsPanel;
import it.polimi.ingsw.ps29.view.GUI.utilities.InteractionMessagesFunctions;

public class PlayerListener extends MouseAdapter {
	
	GUICore gui;

	public PlayerListener(GUICore gui) {
		this.gui = gui;
	}
	
	@Override
	public void mouseClicked (MouseEvent event) {
		
		//handle click on DO ACTION
		if(((JButton) event.getSource()).getText().equals("Do Action!")) {
			
			if(gui.tower.getIndexSpacePressed() != -1) {
				
				//notifico all'utente la scelta appena effettuata
				gui.console.append("\nFamiliar selected: "+gui.family.getSelection().getActionCommand()+
						"\nNumber of Servants: "+gui.servants.getValue()+"\n\n");
				
				//costruisco il pacchetto da inviare al server
				ActionChoice msg = new ActionChoice(gui.playerName);
				msg.setChoice(0, InteractionMessagesFunctions.translateSpace(gui.tower.getIndexSpacePressed()));
				msg.setChoice(1, InteractionMessagesFunctions.translateFloor(gui.tower.getIndexSpacePressed()));
				msg.setChoice(2, new Integer(gui.servants.getValue().toString()));
				msg.setChoice(3, InteractionMessagesFunctions.translateFamiliar(gui.family.getSelection().getActionCommand()));
				
				//chiamo la funzione che notifica alla classe "InputOutputGUI" la scelta fatta
				gui.notifyInput(msg);
			}
			
		}
		
		
		//handle click on NO ACTION
		else if(((JButton) event.getSource()).getText().equals("NO Action")) {
			gui.console.append("NO action!");
			
			//costruisco il pacchetto da inviare al server
			ActionChoice msg = new ActionChoice(gui.playerName);
			msg.setChoice(0, 12);
			msg.setChoice(3, InteractionMessagesFunctions.translateFamiliar(gui.family.getSelection().getActionCommand()));
			
			//chiamo la funzione che notifica alla classe "InputOutputGUI" la scelta fatta
			gui.notifyInput(msg);
		}
		
		
		//handle click on LEADER ACTION
		else if(((JButton) event.getSource()).getText().equals("LEADER")) {
			gui.console.append("Leader action!");
			LeaderPanel leaderPanel;
			if(gui.getMessage() != null)
				leaderPanel = new LeaderPanel(gui, "LEADER PANEL");
		}
		
		
		//handle click on OTHER CARDS
		else if(((JButton) event.getSource()).getText().equals("OTHER CARDS")) {
			OtherCardsPanel otherPanel;
			if(gui.personal.getIdCards() != null)
				otherPanel = new OtherCardsPanel("OTHER CARDS PANEL", gui.personal.getIdCards());
		}
		
		
	}
	
	

}
