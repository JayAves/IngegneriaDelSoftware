package it.polimi.ingsw.ps29.controller;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.InfoForView;
import it.polimi.ingsw.ps29.messages.VaticanChoice;
import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.space.CouncilPalaceArea;

/**
 * Allow game to go on without all players connected.
 * @author Pietro Melzi
 *
 */

public class PlayerInactiveFunctions {
	
	
	static InfoForView playerInactivePlacement (String player, Match model, Color playerColor, int familiar) {
		FamilyMemberInterface fam = getFamiliar(model.getBoard().getPlayerByName(player), familiar);
		((CouncilPalaceArea)model.getBoard().getSpace("NoAction")).placeFamiliar(fam, false);
		fam.setBusy(true);
		
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
	
	static FamilyMemberInterface getFamiliar (Player player, int familiar) {
		switch(familiar) {
		case 1:
			return player.getFamiliarByColor(DiceColor.BLACK);
		case 2:
			return player.getFamiliarByColor(DiceColor.WHITE);
		case 3:
			return player.getFamiliarByColor(DiceColor.ORANGE);
		default:
			return player.getFamiliarByColor(DiceColor.NEUTRAL);
		}
	}
	
}
