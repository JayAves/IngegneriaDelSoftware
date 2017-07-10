package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.GUI.GUICore;
import it.polimi.ingsw.ps29.view.GUI.utilities.GUIUtilities;

/**
 * Show leader cards of a player and allows the user to make an action with them
 * @author Pietro Melzi
 *
 */
public class LeaderPanel extends FromBoardPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3975683472238980794L;
	
	private transient GUICore gui;
	private ArrayList<ArrayList<Object>> leaderSituation;
	private ArrayList<JButton> possibilities = new ArrayList<JButton>();
	private int selectedLeader = -1;
	
	public LeaderPanel (GUICore gui, String title) {
		super(title);
		this.gui = gui;
		leaderSituation = gui.getMessage().getLeaderSituation();
		
		setLayout(new BorderLayout());
	
		add(setLeftPanel(), BorderLayout.CENTER);
		add(setRightPanel(), BorderLayout.LINE_END);
		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	private JPanel setLeftPanel () {
		JPanel panel = new JPanel(new BorderLayout());
		
		//first row in the panel
		JPanel northPanel = new JPanel (new GridLayout(1, leaderSituation.size()));
		for(ArrayList<Object> leader: leaderSituation)
			if(new Integer (leader.get(2).toString()) == 0)
				northPanel.add(new JLabel("IN HAND"));
			else
				northPanel.add(new JLabel("IN BOARD"));
		panel.add(northPanel, BorderLayout.NORTH);
				
		//second row in the panel
		JPanel centerPanel = new JPanel (new GridLayout(1, leaderSituation.size()));
		for(ArrayList<Object> leader: leaderSituation)
			centerPanel.add(GUIUtilities.createSingleLabel("images/leaders/leaders_f_c_"+leader.get(0)+".jpg", 140, 207));
		panel.add(centerPanel, BorderLayout.CENTER);
		
		//third row in the panel
		JPanel southPanel = new JPanel(new GridLayout(1, leaderSituation.size()));
		for(int i=0; i<leaderSituation.size(); i++) {
			JButton button = new JButton("USE");
			southPanel.add(button);
			final int index = i;
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedLeader = index;
					
					if((int) leaderSituation.get(index).get(2)==1) {
						possibilities.get(0).setEnabled(false);
						possibilities.get(1).setEnabled(false);
						possibilities.get(2).setEnabled(true);
					}
					
					else {
						possibilities.get(0).setEnabled(true);
						if ((boolean)leaderSituation.get(index).get(3)) {
							possibilities.get(1).setEnabled(true);
							possibilities.get(2).setEnabled(true);
						}
						else {
							possibilities.get(1).setEnabled(false);
							possibilities.get(2).setEnabled(false);
						}
							
					}
					
				}
				
			});
		}
		panel.add(southPanel, BorderLayout.SOUTH);
				
		return panel;
	}
	
	public JPanel setRightPanel() {
		JPanel rightPanel = new JPanel(new GridLayout(4, 1));
		String[] command = {"DISCARD", "PLAY", "ACTIVATE"};
		
		for(int i=0; i<command.length; i++) {
			JButton button = new JButton(command[i]);
			button.setEnabled(false);
			possibilities.add(button);
			final int index = i;
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(index==0) 
						leaderSituation.get(selectedLeader).add("DISCARD");
					
					else if (index==1) 
						leaderSituation.get(selectedLeader).add("PLAY");
					
					else 
						leaderSituation.get(selectedLeader).add("ACTIVATE");
						
				}
			});
			rightPanel.add(button);
		}
		
		JButton confirm = new JButton("OK");
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionChoice msg = new ActionChoice(gui.getPlayerName());
				msg.setChoice(0, 13);
				msg.setLeaderSituation(leaderSituation);
				gui.notifyInput(msg);
		    	SwingUtilities.getWindowAncestor(rightPanel).dispose();
				
			}
		});
		rightPanel.add(confirm);
		return rightPanel;
		
	}
	
	
}
