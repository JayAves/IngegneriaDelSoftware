package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.BonusPlacementEffect;
import it.polimi.ingsw.ps29.view.GUI.GUICore;

public class BonusPanelCreator extends PanelCreator{
	BonusActionEffect effect;
	JComboBox<String> tower, floor;
	JSpinner servants;
	
	public BonusPanelCreator (GUICore gui, BonusChoice msg) {
		super(gui, msg);
		this.effect = msg.getBonus();
	}

	@Override
	public JPanel createLeftPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 1;
		JLabel type = new JLabel (effect.getType());
		panel.add(type, c);
		
		c.gridx = 2;
		JLabel value = new JLabel (effect.getValue()+"");
		panel.add(value, c);
		
		c.gridy = 2;
		if(effect instanceof BonusPlacementEffect)
			for(int i=0; i<((BonusPlacementEffect)effect).getDiscount().size(); i++) {
				c.gridx = i+1;
				JLabel discount = createSingleLabel("images/resources/"+
						((BonusPlacementEffect)effect).getDiscount().get(0).getType()+".png");
				panel.add(discount, c);	
			}
		
		
		return panel;
	}

	@Override
	public JPanel createRightPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel first = new JLabel("Insert fields to perform the action: ");
		c.gridx=1;
		c.gridy = 1;
		c.gridwidth = 2;
		panel.add(first,c );
		
		if (effect.getType().equals("all")) {
			c.gridy++;
			tower = new JComboBox<String>();
			tower.addItem("territory");
			tower.addItem("building");
			tower.addItem("character");
			tower.addItem("venture");
			panel.add(tower, c);
		}
		
		if (!effect.getType().equals("harvest") && !effect.getType().equals("production")) {
			c.gridy++;
			floor = new JComboBox<String>();
			floor.addItem("first");
			floor.addItem("second");
			floor.addItem("third");
			floor.addItem("fourth");
			panel.add(floor, c);
		}
		
		c.gridy++;
		SpinnerModel model = new SpinnerNumberModel(0, 0, 99, 1);     
		servants = new JSpinner(model);
		panel.add(servants, c);
		
		c.gridy++;
		c.gridwidth = 1;
		JButton noAction = new JButton("No Action");
		noAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		    	gui.notifyAboutInput(msg);
		    	SwingUtilities.getWindowAncestor(panel).dispose();
			}
			
		});
		panel.add(noAction, c);
		
		c.gridx = 2;
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createBonusMessage();
		    	gui.notifyAboutInput(msg);
		    	SwingUtilities.getWindowAncestor(panel).dispose();
			}
			
		});
		panel.add(confirm, c);
		
		return panel;
	}
	
	private void createBonusMessage () {
		if(tower!=null)
			((BonusChoice)msg).setSpace(getSpaceIndex(tower.getSelectedItem().toString()));
		if(floor!=null)
			((BonusChoice)msg).setFloor(((int)floor.getSelectedIndex())+1);
		((BonusChoice)msg).setServants(new Integer(servants.getValue().toString()));
		
	}
	
	private int getSpaceIndex (String name) {
		switch(name) {
		case "territory":
			return 1;
		case "building":
			return 2;
		case "character":
			return 3;
		case "venture":
			return 4;
		default:
			return 12;
		}
	}
	
}
