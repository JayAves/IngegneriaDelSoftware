package it.polimi.ingsw.ps29.view.GUI;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import it.polimi.ingsw.ps29.DTO.TowersDTO;

public class GUICore {
	JFrame frame; 
	TowersDTO towers;
	
	PrintTower tower;
	ImageToPrint tile;
	ImageToPrint personal;
	ButtonGroup family;
	JSpinner servants;
	JButton doAction;
	ImageToPrint preview;
	JButton venture;
	JButton character;
	JButton prev;
	JButton next;
	JLabel military;
	JLabel faith;
	JLabel victory;
	JLabel privileges;
	ImageToPrint leader;
	ImageToPrint excomm1;
	ImageToPrint excomm2;
	ImageToPrint excomm3;
	JTextArea console;
	
	
	public GUICore () {
		EventQueue.invokeLater(new Runnable() {
		
			@Override
			public void run() {
				// TODO Auto-generated method stub
	
				frame = new JFrame ();
				frame.setVisible(true);
				//frame.setResizable(false);
				setFrame (frame);
				Toolkit tk = Toolkit.getDefaultToolkit();
				int xSize = ((int) tk.getScreenSize().getWidth());
				int ySize = ((int) tk.getScreenSize().getHeight());
				frame.setSize(xSize,ySize);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
			}
		});
	}
	
	public void setFrame (JFrame frame) {
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			
		c.weighty = 1;
		c.weightx = 4;
		c.fill = GridBagConstraints.BOTH;
		ArrayList<Integer> id = new ArrayList<Integer> ();
		for(int i=0; i<16; i++)
			id.add(i+1);
		tower = new PrintTower(id, this);
		frame.add(tower, c);
		
		c.gridx = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.VERTICAL;
		JPanel centralPanel = new JPanel();
		setCentralPanel(centralPanel);
		frame.add(centralPanel, c);
	}
	
	public void setCentralPanel (JPanel centralPanel) {
		centralPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelNorth = new JPanel ();
		JPanel panelCenter = new JPanel ();
		JPanel panelSouth = new JPanel ();
		
		setPanelNorth(panelNorth);
		setPanelCenter(panelCenter);
		setPanelSouth(panelSouth);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 6;
		centralPanel.add(panelNorth, c);
		
		c.gridy = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.NONE;
		centralPanel.add(panelCenter, c);
		
		c.gridy = 2;
		c.weighty = 4;
		c.fill = GridBagConstraints.BOTH;
		centralPanel.add(panelSouth,  c);
				
	}
	
	public void setPanelNorth (JPanel panelNorth) {
		panelNorth.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		tile = new ImageToPrint("bonus_tiles/personalbonustile_1.png", this);
		c.gridheight = 2;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		panelNorth.add(tile, c);
		
		
		personal = new ImageToPrint("personalboard.jpg", this);
		c.gridx = 1;
		c.weightx = 6;
		c.weighty = 2;
		
		panelNorth.add(personal, c);
		
		leader = new ImageToPrint("leader.jpg", this);
		c.gridx = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 1;
		panelNorth.add(leader, c);
		
		excomm1 = new ImageToPrint("excomm_back_1.png", this);
		c.gridx = 3;
		panelNorth.add(excomm1, c);
		
		excomm2 = new ImageToPrint("excomm_back_2.png", this);
		c.gridx = 2;
		c.gridy = 1;
		panelNorth.add(excomm2, c);
		
		excomm3 = new ImageToPrint("excomm_back_3.png", this);
		c.gridx = 3;
		panelNorth.add(excomm3, c);
	}
	
	public void setPanelCenter (JPanel panelCenter) {
		panelCenter.setLayout(new GridLayout(1, 4));
		
		JPanel buttonsPanel = new JPanel ();
		JPanel familiarPanel = new JPanel();
		JPanel doActionPanel = new JPanel();
		JPanel pointsPanel = new JPanel();
		
		buttonsPanel.setLayout(new GridLayout(2, 2));
		familiarPanel.setLayout(new GridLayout(2, 2));
		doActionPanel.setLayout(new GridLayout(2, 1));
		pointsPanel.setLayout(new GridLayout(2, 2));
		
		setButtonsPanel(buttonsPanel);
		setFamiliarPanel(familiarPanel);
		setDoActionPanel(doActionPanel);
		setPointsPanel(pointsPanel);
		
		panelCenter.add(buttonsPanel);
		panelCenter.add(familiarPanel);
		panelCenter.add(doActionPanel);
		panelCenter.add(pointsPanel);
	}
	
	public void setPanelSouth (JPanel panelSouth) {
		panelSouth.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		preview = new ImageToPrint("devcards_f_en_c_1.png", this);
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		panelSouth.add(preview, c);
		
		c.gridx = 1;
		c.weightx = 3;
		c.weighty = 1;
		
		console = new JTextArea("console...");
		console.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(console);
		panelSouth.add(scrollPane, c);
		
	}
	
	public void setButtonsPanel (JPanel buttonsPanel) {
		venture = new JButton("Venture");
		buttonsPanel.add(venture);
		character = new JButton("Character");
		buttonsPanel.add(character);
		prev = new JButton("Prev");
		buttonsPanel.add(prev);
		next = new JButton("Next");
		buttonsPanel.add(next);
	}
	
	public void setFamiliarPanel (JPanel familiarPanel) {
		
		JRadioButton black = new JRadioButton("Black");
		black.setActionCommand("Black");
		black.setSelected(true);
		familiarPanel.add(black);
		
		JRadioButton white = new JRadioButton("White");
		white.setActionCommand("White");
		familiarPanel.add(white);
		
		JRadioButton orange = new JRadioButton("Orange");
		orange.setActionCommand("Orange");
		familiarPanel.add(orange);
		
		JRadioButton neutral = new JRadioButton("Neutral");
		neutral.setActionCommand("Neutral");
		familiarPanel.add(neutral);
		
		family = new ButtonGroup();
		family.add(black);
		family.add(white);
		family.add(orange);
		family.add(neutral);
	}
	
	public void setDoActionPanel (JPanel doActionPanel) {
		
		SpinnerModel model = new SpinnerNumberModel(0, 0, 99, 1);     
		servants = new JSpinner(model);
		doActionPanel.add(servants);
		
		doAction = new JButton("Do Action!");
		doAction.addMouseListener(new PlayerListener(this));
		doActionPanel.add(doAction);
	}
	
	public void setPointsPanel (JPanel pointsPanel) {
		
		military = new JLabel("Military: 0");
		pointsPanel.add(military);
		faith = new JLabel("Faith: 0");
		pointsPanel.add(faith);
		victory = new JLabel("Victory: 0");
		pointsPanel.add(victory);
		privileges = new JLabel("Privileges: 0");
		pointsPanel.add(privileges);
	}	
	
	public TowersDTO getTowers () {
		return towers;
	}
	
	public void setTowers (TowersDTO towers) {
		this.towers = towers;
	}
	
	public void zoomImage (int idCard) {
		preview.setImage("cards/devcards_f_en_c_"+idCard+".png");
	}
	
	public static void main(String[] args) {
		new GUICore ();
	}

}
