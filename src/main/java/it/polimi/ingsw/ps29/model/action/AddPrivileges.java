package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.resources.Coins;
import it.polimi.ingsw.ps29.model.game.resources.FaithPoints;
import it.polimi.ingsw.ps29.model.game.resources.MilitaryPoints;
import it.polimi.ingsw.ps29.model.game.resources.ResourceType;
import it.polimi.ingsw.ps29.model.game.resources.Servants;
import it.polimi.ingsw.ps29.model.game.resources.Stones;
import it.polimi.ingsw.ps29.model.game.resources.Woods;

public class AddPrivileges {
	
	public void handlePrivileges (Player player, ArrayList <ResourceType> res) {
		for(ResourceType resType: res) 
			switch(resType.toString()) {
				case "wood":
					player.getPersonalBoard().getResources().updateResource(new Woods (1));
					player.getPersonalBoard().getResources().updateResource(new Stones(1));
					break;
				case "servant":
					player.getPersonalBoard().getResources().updateResource(new Servants (2));
					break;
				case "coin":
					player.getPersonalBoard().getResources().updateResource(new Coins (2));
					break;
				case "military":
					player.getPersonalBoard().getResources().updateResource(new MilitaryPoints(2));
					break;
				case "faith":
					player.getPersonalBoard().getResources().updateResource(new FaithPoints(1));
					break;
			}
		
	}

}
