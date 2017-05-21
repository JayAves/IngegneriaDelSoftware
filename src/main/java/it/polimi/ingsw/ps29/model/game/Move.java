package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.space.ActionSpace;

public class Move {
	
	private FamilyMember familiar;
	private int servants;
	private Match match;
	private ActionSpace space;
	private Player player;
	
	public Match getMatch () {
		return match;
	}

	public FamilyMember getFamiliar() {
		return familiar;
	}

	public int getServants() {
		return servants;
	}
	
	public ActionSpace getSpace () {
		return space;
	}

	public Player getPlayer() {
		return player;
	}


}
