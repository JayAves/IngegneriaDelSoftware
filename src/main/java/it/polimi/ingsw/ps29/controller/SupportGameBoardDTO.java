package it.polimi.ingsw.ps29.controller;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.DTO.FamilyMemberDTO;
import it.polimi.ingsw.ps29.model.game.familymember.FamilyMemberInterface;
import it.polimi.ingsw.ps29.model.space.QueueActionSpace;
import it.polimi.ingsw.ps29.model.space.SingleSlotActionSpace;

/**
 * 
 * @author Pietro Melzi
 *
 */
public class SupportGameBoardDTO {
	
	public static FamilyMemberDTO singleSpaceOccupant (SingleSlotActionSpace space) {
		FamilyMemberInterface member = space.getFamiliar();
		return (member!=null) ? new FamilyMemberDTO(member.getPlayerColor(), member.getFamiliarColor()) : null;
	}

	public static ArrayList<FamilyMemberDTO> queueSpaceOccupants (QueueActionSpace space) {
		ArrayList<FamilyMemberInterface> family = space.getQueue();
		ArrayList<FamilyMemberDTO> familyDTO = new ArrayList<FamilyMemberDTO>();
		for (FamilyMemberInterface fam: family)
			familyDTO.add(new FamilyMemberDTO(fam.getPlayerColor(), fam.getFamiliarColor()));
		return familyDTO;
			
	}
}
