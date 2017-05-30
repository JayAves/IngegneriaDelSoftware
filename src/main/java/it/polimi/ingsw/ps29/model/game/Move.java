package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Move {
	
	private String player;
	private String space;
	private int servants;
	private FamilyMember familiar;
	private int floor;
	
	public Move (String player, String space, int servants, FamilyMember familiar, int floor) {
		super();
		this.player = player;
		this.space = space;
		this.servants = servants;
		this.familiar = familiar;
		this.floor=floor;
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

	public String getPlayer() {
		return player;
	}

	public int getFloor() {
		return floor;
	}
}
