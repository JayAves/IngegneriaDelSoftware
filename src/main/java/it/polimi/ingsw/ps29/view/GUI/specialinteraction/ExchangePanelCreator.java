package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.view.GUI.GUICore;

public class ExchangePanelCreator extends PanelCreator {

	public ExchangePanelCreator(GUICore gui, Exchange msg) {
		super(gui, msg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel createLeftPanel() {
		JPanel panel = new JPanel(new GridLayout(((Exchange)msg).getExchange().getChoices().size(), 1));
		
		for(ExchangeResourceHandler erh: ((Exchange)msg).getExchange().getChoices()) {
			JPanel singleOption = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 1;
			c.gridy = 1;
			singleOption.add(new JLabel("Res OUT:"),c);

			for (Resource res: erh.getResOut()) {
				c.gridy = 2;
				singleOption.add(createSingleLabel("images/resources/"+res.getType()+".png"),c);
				c.gridy = 3;
				singleOption.add(new JLabel(res.getAmount()+""));
				//add
				c.gridx++;
			}
			
			c.gridx = 1;
			c.gridy = 4;
			singleOption.add(new JLabel("Res IN:"),c);

			for (Resource res: erh.getResIn()) {
				c.gridy = 5;
				singleOption.add(createSingleLabel("images/resources/"+res.getType()+".png"),c);
				c.gridy = 6;
				singleOption.add(new JLabel(res.getAmount()+""));
				//add
				c.gridx++;
			}
			
			panel.add(singleOption);
		}
		
		return panel;
	}

	@Override
	public JPanel createRightPanel() {
		JPanel panel = new JPanel();
		return panel;
	}

}
