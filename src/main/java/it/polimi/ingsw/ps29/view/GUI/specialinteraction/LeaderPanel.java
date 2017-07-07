package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.GUI.GUICore;

public class LeaderPanel extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3975683472238980794L;
	
	private GUICore gui;
	private ArrayList<ArrayList<Object>> leaderSituation;
	private ArrayList<JButton> possibilities = new ArrayList<JButton>();
	private int selectedLeader = -1;
	
	public LeaderPanel (GUICore gui) {
		this.gui = gui;
		leaderSituation = gui.getMessage().getLeaderSituation();
		
		setLayout(new GridLayout(1, 2));
		setTitle("");
	
		add(setLeftPanel());
		add(setRightPanel());
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	private JPanel setLeftPanel () {
		JPanel leftPanel = new JPanel(new GridLayout(3, leaderSituation.size()));
		
		//first row in the panel
		for(ArrayList<Object> leader: leaderSituation)
			if(new Integer (leader.get(2).toString()) == 0)
				leftPanel.add(new JLabel("IN HAND"));
			else
				leftPanel.add(new JLabel("IN BOARD"));
				
		//second row in the panel
		for(ArrayList<Object> leader: leaderSituation)
			leftPanel.add(createSingleLabel("images/leaders/leaders_f_c_"+leader.get(0)+".jpg", 140, 220));
		
		//third row in the panel
		for(int i=0; i<leaderSituation.size(); i++) {
			JButton button = new JButton("USE");
			leftPanel.add(button);
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
				
		return leftPanel;
	}
	
	public JPanel setRightPanel() {
		JPanel rightPanel = new JPanel(new GridLayout(4, 1));
		String[] command = {"DISCARD", "PLAY", "ACTIVATE"};
		
		for(int i=0; i<command.length; i++) {
			JButton button = new JButton(command[i]);
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
				gui.notifyInput(1);
		    	SwingUtilities.getWindowAncestor(rightPanel).dispose();
				
			}
		});
		rightPanel.add(confirm);
		return rightPanel;
		
	}
	
	
	public JLabel createSingleLabel (String path, int width, int height) {
		JLabel label = null;
		
		try {
			BufferedImage img = ImageIO.read(new File(path));
			Image imgScaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        ImageIcon icon = new ImageIcon(imgScaled);
	        label = new JLabel(icon);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return label;
	}
	
}
