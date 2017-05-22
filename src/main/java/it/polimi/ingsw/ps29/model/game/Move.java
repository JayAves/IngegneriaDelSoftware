package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;
import it.polimi.ingsw.ps29.model.space.ActionSpace;

public class Move {
	
	private int match;
	private Player player;
	private ActionSpace space;
	private int servants;
	private FamilyMember familiar;		
	
	public Move (int match, Player player, ActionSpace space, int servants, FamilyMember familiar) {
		super();
		this.match = match;
		this.player = player;
		this.space = space;
		this.servants = servants;
		this.familiar = familiar;
	}

	public int getMatch () {
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
