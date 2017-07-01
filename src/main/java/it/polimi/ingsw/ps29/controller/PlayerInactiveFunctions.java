package it.polimi.ingsw.ps29.controller;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.VaticanChoice;
import it.polimi.ingsw.ps29.model.game.Color;

/**
 * Allow game to go on without all players connected.
 * @author Pietro Melzi
 *
 */

public class PlayerInactiveFunctions {
	
	
	static InfoForView playerInactivePlacement (String player, Color playerColor, int familiar) {
		InfoForView info = new InfoForView(player);
		info.familiar = familiar;
		info.playerColor = playerColor;
		info.space=12;
		
		return info;
	}
	
	static VaticanChoice playerInactiveVatican (String player) {
		VaticanChoice msg= new VaticanChoice(player);
		msg.setSustain(false);
		return msg;
	}
	
	static ActionChoice playerInactiveBonusChoice (String player) {
		ActionChoice msg = new ActionChoice(player);
		msg.setChoice(0, 12);
		return msg;
	}
	
}
