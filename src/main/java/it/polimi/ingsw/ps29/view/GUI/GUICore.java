package it.polimi.ingsw.ps29.view.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;

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
import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.view.GUI.coordinates.StartCoordinates;

public class GUICore extends Observable{
	String playerName;
	
	JFrame frame; 
	TowersDTO towers;
	ActionChoice message; //used for leaders
	
	PrintTower tower;
	ImageToPrint tile;
	PrintPersonal personal;
	ButtonGroup family;
	JSpinner servants;
	JButton doAction;
	JButton noAction;
	ImageToPrint preview;
	
	JButton venture;
	JButton character;
	JButton prev;
	JButton next;
	
	JLabel coin;
	JLabel stone;
	JLabel wood;
	JLabel servant;
	JLabel military;
	JLabel faith;
	JLabel victory;
	JLabel privileges;
	
	JButton leaderButton;
	ImageToPrint excomm1;
	ImageToPrint excomm2;
	ImageToPrint excomm3;
	JTextArea console;

	PlayerListener listener;
	
	public GUICore (String name) {
		playerName = name;
		listener = new PlayerListener(this);
		
		EventQueue.invokeLater(new Runnable() {
		
			@Override
			public void run() {
				// TODO Auto-generated method stub
	
				frame = new JFrame ();
				frame.setVisible(true);
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
		StartCoordinates startCoord = new StartCoordinates(14,20,51,85,97,91,4,4);
		tower = new PrintTower("gameboard.png", this, startCoord, 413, 607);
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
		
		tile = new ImageToPrint("bonus_tiles/personalbonustile_1.png");
		c.gridheight = 2;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		panelNorth.add(tile, c);
		
		StartCoordinates startCoord = new StartCoordinates(6, 9, 59, 92, 67, 115, 2, 6);
		personal = new PrintPersonal("personalboard.jpg", this, startCoord, 400, 285);
		
		c.gridx = 1;
		c.weightx = 6;
		c.weighty = 2;
		
		panelNorth.add(personal, c);
		
		JPanel leaderPanel = new JPanel ();
		leaderPanel.setLayout(new BorderLayout());
		
		ImageToPrint leader = new ImageToPrint("leader.jpg");
		leaderPanel.add(leader, BorderLayout.CENTER);
		
		leaderButton = new JButton ("Leader");
		leaderButton.addMouseListener(listener);
		leaderPanel.add(leaderButton, BorderLayout.PAGE_END);
		
		c.gridx = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 1;
		panelNorth.add(leaderPanel, c);
		
		excomm1 = new ImageToPrint("excomm_back_1.png");
		c.gridx = 3;
		panelNorth.add(excomm1, c);
		
		excomm2 = new ImageToPrint("excomm_back_2.png");
		c.gridx = 2;
		c.gridy = 1;
		panelNorth.add(excomm2, c);
		
		excomm3 = new ImageToPrint("excomm_back_3.png");
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
		doActionPanel.setLayout(new GridLayout(2, 2));
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
		
		preview = new ImageToPrint("devcards_f_en_c_1.png");
		c.weightx = 2;
		c.fill = GridBagConstraints.BOTH;
		panelSouth.add(preview, c);
		
		c.gridx = 1;
		c.weightx = 3;
		c.weighty = 1;
		
		console = new JTextArea("console...");
		console.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(console);
		panelSouth.add(scrollPane, c);
		
		c.gridx = 2;
		c.weightx = 1;
		JPanel resourcesPanel = new JPanel();
		resourcesPanel.setLayout(new GridLayout(4, 1));
		
		coin = new JLabel("COINS: 0");
		resourcesPanel.add(coin);
		stone = new JLabel("STONES: 0");
		resourcesPanel.add(stone);
		wood = new JLabel("WOODS: 0");
		resourcesPanel.add(wood);
		servant = new JLabel("SERVANTS: 0");
		resourcesPanel.add(servant);
		
		panelSouth.add(resourcesPanel, c);
		
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
		JLabel labelServants = new JLabel("# of servants: ");
		doActionPanel.add(labelServants);
		
		SpinnerModel model = new SpinnerNumberModel(0, 0, 99, 1);     
		servants = new JSpinner(model);
		doActionPanel.add(servants);
		
		doAction = new JButton("Do Action!");
		doAction.addMouseListener(listener);
		doActionPanel.add(doAction);
		
		noAction = new JButton("NO Action");
		noAction.addMouseListener(listener);
		doActionPanel.add(noAction);
	}
	
	public void setPointsPanel (JPanel pointsPanel) {
		
		military = new JLabel("MILITARY: 0");
		pointsPanel.add(military);
		faith = new JLabel("FAITH: 0");
		pointsPanel.add(faith);
		victory = new JLabel("VICTORY: 0");
		pointsPanel.add(victory);
		privileges = new JLabel("PRIVILEGES: 0");
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
		new GUICore ("test");
	}
	
	public void notifyInput (Object msg) {
		setChanged();
		notifyObservers(msg);
	}
	
	public void setMessage (ActionChoice msg) {
		message = msg;
	}
	
	public ActionChoice getMessage () {
		return message;
	}
	
	public JLabel getCoin() {
		return coin;
	}

	public JLabel getStone() {
		return stone;
	}

	public JLabel getWood() {
		return wood;
	}

	public JLabel getServant() {
		return servant;
	}

	public JLabel getMilitary() {
		return military;
	}

	public JLabel getFaith() {
		return faith;
	}

	public JLabel getVictory() {
		return victory;
	}

	public JLabel getPrivileges() {
		return privileges;
	}
	
	public String getPlayerName () {
		return playerName;
	}

}
