package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps29.messages.PrivilegeChoice;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.GUI.GUICore;


public class PrivilegesPanelCreator extends PanelCreator {
	private ButtonGroup choice;
	
	public PrivilegesPanelCreator(GUICore gui, PrivilegeChoice msg) {
		super(gui, msg);
	}

	@Override
	public JPanel createLeftPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Chose the privilege!"), BorderLayout.PAGE_START);
		
		panel.add(createSingleLabel("images/resources/privilege.png"), BorderLayout.CENTER);
		
		return panel;
	}

	@Override
	public JPanel createRightPanel() {
		JPanel topPanel = new JPanel (new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(2, 5));
		
		createOptionLabel(panel);	
		createOptions(panel);
		
		JPanel end = new JPanel ();
		JButton confirm = new JButton("OK");
		confirm.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {
		    	ResourceType type = createPrivilegeMessage();
		    	gui.notifyPrivilege(type);
		    	SwingUtilities.getWindowAncestor(end).dispose();
		    }
		    
		});
		end.add(confirm);
		
		topPanel.add(panel, BorderLayout.CENTER);
		topPanel.add(end, BorderLayout.PAGE_END);
		return topPanel;
	}
	
	private void createOptions (JPanel panel) {
		JRadioButton first = new JRadioButton();
		first.setActionCommand("1");
		first.setSelected(true);
		panel.add(first);
		
		JRadioButton second = new JRadioButton();
		second.setActionCommand("2");
		panel.add(second);
		
		JRadioButton third = new JRadioButton();
		third.setActionCommand("3");
		panel.add(third);
		
		JRadioButton fourth = new JRadioButton();
		fourth.setActionCommand("4");
		panel.add(fourth);
		
		JRadioButton fifth = new JRadioButton();
		fifth.setActionCommand("5");
		panel.add(fifth);
		
		choice = new ButtonGroup();
		choice.add(first);
		choice.add(second);
		choice.add(third);
		choice.add(fourth);
		choice.add(fifth);
	}
	
	private ResourceType createPrivilegeMessage () {
		ResourceType type = ResourceType.WOOD;
		switch (new Integer (choice.getSelection().getActionCommand()) ) {
			case 1:
				type = ResourceType.WOOD;
				break;
			case 2:
				type = ResourceType.SERVANT;
				break;
			case 3:
				type = ResourceType.COIN;
				break;
			case 4:
				type = ResourceType.MILITARY;
				break;
			case 5:
				type = ResourceType.FAITH;
				break;
				
		}
		
		return type;
	}
	
	public void createOptionLabel (JPanel panel) {
		String[] types = {"wood","servant", "coin", "military", "faith"};
		
		//create the options
		for(int i=0; i<5; i++) 
			panel.add(createSingleLabel("images/resources/"+types[i]+"priv.png"));
		
	}
	
}
