package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;

/**
 * Set of user's choices about an Action.
 * @author Pietro Grotti
 * @author Pietro Melzi
 * @see it.polimi.ing sw.ps29.model.action.Action
 *
 */
public class Move {
	
	private Player player;
	private String space;
	private int floor;
	private int servants;
	private FamilyMemberInterface familiar;
	
	
	public Move (Player player, String space, int floor, int servants, FamilyMemberInterface familiar) {
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

	public FamilyMemberInterface getFamiliar() {
		return familiar;
	}

	public void setFamiliar(FamilyMemberInterface familiar) {
		this.familiar = familiar;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

}
