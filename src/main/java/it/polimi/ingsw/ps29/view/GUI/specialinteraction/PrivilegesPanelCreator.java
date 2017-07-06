package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.view.GUI.GUICore;


public class PrivilegesPanelCreator extends PanelCreator {
	private ButtonGroup choice;
	
	public PrivilegesPanelCreator(GUICore gui) {
		super(gui);
	}

	@Override
	public JPanel createLeftPanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Chose the privilege!"));
		return panel;
	}

	@Override
	public JPanel createRightPanel() {
		JPanel topPanel = new JPanel (new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(2, 5));
		String[] types = {"wood","servant", "coin", "military", "faith"};
		
		//create the options
		for(int i=0; i<5; i++) {
			
			BufferedImage img;
			try {
				img = ImageIO.read(new File("images/resources/"+types[i]+".png"));
				Image imgScaled = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		        ImageIcon icon = new ImageIcon(imgScaled);
		        JLabel label = new JLabel(icon);
		        panel.add(label);
		        
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		createOptions(panel);
		
		JPanel end = new JPanel ();
		JButton confirm = new JButton("OK");
		confirm.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e)
		    {
		    	ResourceType type = createPrivilegeMessage();//(new Integer (choice.getSelection().getActionCommand()) ); 
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
}
