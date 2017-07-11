package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps29.messages.Exchange;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourceHandler;
import it.polimi.ingsw.ps29.model.game.resources.Resource;
import it.polimi.ingsw.ps29.view.GUI.GUICore;
import it.polimi.ingsw.ps29.view.GUI.utilities.GUIUtilities;

/**
 * Creates the panel used in exchange interactions
 * @author Pietro Melzi
 *
 */
public class ExchangePanelCreator extends PanelCreator {
	private ButtonGroup choice;

	public ExchangePanelCreator(GUICore gui, Exchange msg) {
		super(gui, msg);

	}

	@Override
	public JPanel createLeftPanel() {
		JPanel panel = new JPanel(new GridLayout(((Exchange)msg).getExchange().getChoices().size(), 1));
		
		for(ExchangeResourceHandler erh: ((Exchange)msg).getExchange().getChoices()) {
			JPanel singleOption = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			singleOption.add(new JLabel("Res OUT:"),c);

			for (Resource res: erh.getResOut()) {
				c.gridy = 1;
				singleOption.add(GUIUtilities.createSingleLabel("images/resources/"+res.getType()+".png", 40, 40),c);
				c.gridy = 2;
				singleOption.add(new JLabel(res.getAmount()+""),c);
				
				c.gridx++;
			}
			
			c.gridx = 0;
			c.gridy = 3;
			singleOption.add(new JLabel("Res IN:"),c);

			for (Resource res: erh.getResIn()) {
				c.gridy = 4;
				singleOption.add(GUIUtilities.createSingleLabel("images/resources/"+res.getType()+".png", 40, 40),c);
				c.gridy = 5;
				singleOption.add(new JLabel(res.getAmount()+""),c);

				c.gridx++;
			}
			
			panel.add(singleOption);
		}
		
		return panel;
	}

	@Override
	public JPanel createRightPanel() {
		JPanel panel = new JPanel(new GridLayout(((Exchange)msg).getExchange().getChoices().size()+3, 1));
		choice = new ButtonGroup();
		
		panel.add(new JLabel("Make your choice: "));
		int i;
		for(i=1; i<=((Exchange)msg).getExchange().getChoices().size(); i++) {
			JRadioButton option = new JRadioButton("OPTION: "+i);
			option.setActionCommand(i+"");
			choice.add(option);
			panel.add(option);
		}
		
		JRadioButton option = new JRadioButton();
		option.setActionCommand(i+"");
		option.setText("No Exchange");
		option.setSelected(true);
		choice.add(option);
		panel.add(option);
		
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((Exchange)msg).setChoice(0, new Integer(choice.getSelection().getActionCommand()) );
		    	gui.notifyInput(msg);
		    	SwingUtilities.getWindowAncestor(panel).dispose();
				
			}
		});
		panel.add(confirm);
		
		return panel;
	}

}
