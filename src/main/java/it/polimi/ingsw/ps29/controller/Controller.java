package it.polimi.ingsw.ps29.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.game.resources.Servants;
import it.polimi.ingsw.ps29.view.View;

public class Controller implements Observer{
	
	private Match model;
	private Map<String, View> views = new HashMap <String, View> ();
	
	public Controller (Match model) {
		this.model = model;
	}
	
	public void addView (View view, String playerName) {
		if(!views.containsKey(playerName))
			views.put(playerName, view);
	}
	
	public void callCorrectView () {
		views.get(model.getBoard().getPlayers().get(0).getName()).askNextAction();
	}
	
	//quando richiama l'action dovrà passare il model e l'oggetto move 

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(!(o instanceof View)) {
			throw new IllegalArgumentException();
		}
	
	}
	
	
	public Player getPlayer (String player) {
		for (Player p: model.getBoard().getPlayers()) 
			if(p.getName().equals(player))
				return p;
		return null;	
	}
	
	public Servants getServants (int number) {
		return new Servants (-number);
	}
	
	
	
	public FamilyMember getFamiliar (String player, int i) {
		DiceColor color = DiceColor.NEUTRAL;
		
		switch (i) {
			case 1:
				color = DiceColor.BLACK;
				break;
			case 2:
				color = DiceColor.WHITE;
				break;
			case 3:
				color = DiceColor.ORANGE;
				break;
			case 4:
				color = DiceColor.NEUTRAL;
				break;
			default:
				break;
		}
		return getPlayer(player).getFamiliarByColor(color);
	}

}
