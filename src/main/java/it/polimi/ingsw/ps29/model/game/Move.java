package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Move {
	
	private Player player;
	private String space;
	private int floor;
	private int servants;
	private FamilyMember familiar;
	
	
	public Move (Player player, String space, int floor, int servants, FamilyMember familiar) {
		super();
		this.setPlayer(player);
		this.setSpace(space);
		this.setFloor(floor);
		this.setServants(servants);
		this.setFamiliar(familiar);
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public int getServants() {
		return servants;
	}

	public void setServants(int servants) {
		this.servants = servants;
	}

	public FamilyMember getFamiliar() {
		return familiar;
	}

	public void setFamiliar(FamilyMember familiar) {
		this.familiar = familiar;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

}
