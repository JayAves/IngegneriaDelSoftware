package it.polimi.ingsw.ps29.view.GUI.specialinteraction;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import it.polimi.ingsw.ps29.view.GUI.utilities.GUIUtilities;

public class OtherCardsPanel extends FromBoardPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1776486817438214292L;
	
	public OtherCardsPanel(String title, ArrayList<Integer> idCards) {
		super(title);
		setLayout(new GridLayout(2, 1));
		add(createPanel(idCards, 12));
		add(createPanel(idCards, 18));
		
		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	
	}
	
	private JPanel createPanel (ArrayList<Integer> idCards, int index) {
		JPanel panel = new JPanel(new GridLayout(1, 6));
		int count = 0;
		if(!idCards.isEmpty()) {
			for(int i=index; i<index+6; i++)
				if(idCards.get(i) != -1) {
					panel.add(GUIUtilities.createSingleLabel("images/cards/devcards_f_en_c_"+idCards.get(i)+".png", 140, 207));
					count++;
				}
			for(int i=count; i<6; i++)
				panel.add(GUIUtilities.createSingleLabel("images/leader.jpg", 140, 207));
		}
		return panel;
	}
	
	/*private int count(ArrayList<Integer> idCards, int index) {
		int count = 0;
		for(int i=index; i<index+6; i++)
			if(idCards.get(i)!=-1)
				count++;
		return count;
	}*/

}
