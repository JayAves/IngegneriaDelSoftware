package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import it.polimi.ingsw.ps29.model.cards.effects.BonusActionEffect;
import it.polimi.ingsw.ps29.model.cards.effects.BonusPlacementEffect;
import it.polimi.ingsw.ps29.view.GUI.GUICore;

public class BonusPanelCreator extends PanelCreator{
	private BonusActionEffect effect;
	
	public BonusPanelCreator (GUICore gui, BonusActionEffect effect) {
		super(gui);
		this.effect = effect;
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
		
		JLabel first = new JLabel("Bonus Action?");
		c.gridx=1;
		c.gridy = 1;
		c.gridwidth = 2;
		panel.add(first,c );
		
		if (effect.getType().equals("all")) {
			c.gridy++;
			JComboBox<String> tower = new JComboBox<String>();
			tower.addItem("territory");
			tower.addItem("building");
			tower.addItem("character");
			tower.addItem("venture");
			panel.add(tower, c);
		}
		
		if (!effect.getType().equals("harvest") && !effect.getType().equals("production")) {
			c.gridy++;
			JComboBox<String> floor = new JComboBox<String>();
			floor.addItem("first");
			floor.addItem("second");
			floor.addItem("third");
			floor.addItem("fourth");
			panel.add(floor, c);
		}
		
		c.gridy++;
		SpinnerModel model = new SpinnerNumberModel(0, 0, 99, 1);     
		JSpinner servants = new JSpinner(model);
		panel.add(servants, c);
		
		c.gridy++;
		c.gridwidth = 1;
		JButton noAction = new JButton("No Action");
		panel.add(noAction, c);
		
		c.gridx = 2;
		JButton confirm = new JButton("Confirm");
		panel.add(confirm, c);
		
		return panel;
	}
	
}
