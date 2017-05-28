package it.polimi.ingsw.ps29.model.game.familymember;

import java.util.HashMap;

import it.polimi.ingsw.ps29.model.game.resources.Resource;

public class FamilySet {

	private HashMap <String, FamilyMember> familyMembers;
	
	public FamilyMember getFamilyMember(String member){
		return familyMembers.get(member);
	}
}
