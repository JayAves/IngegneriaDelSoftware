package it.polimi.ingsw.ps29.model.game;

import it.polimi.ingsw.ps29.model.game.familymember.FamilyMember;

public class Move {
	
	private FamilyMember familiar;
	private int servants;
	private Match match;
	
	public Match getMatch () {
		return match;
	}

	public FamilyMember getFamiliar() {
		return familiar;
	}

	public int getServants() {
		return servants;
	}



}
