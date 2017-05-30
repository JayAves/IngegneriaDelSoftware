package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Move {
	
	private int match;
	private Player player;
	private String space;
	private int servants;
	private FamilyMember familiar;
	private int floor;
	
	public Move (int match, Player player, String space, int servants, FamilyMember familiar, int floor) {
		super();
		this.match = match;
		this.player = player;
		this.space = space;
		this.servants = servants;
		this.familiar = familiar;
		this.floor=floor;
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
	
	public String getSpace () {
		return space;
	}

	public Player getPlayer() {
		return player;
	}

	public int getFloor() {
		return floor;
	}
}
