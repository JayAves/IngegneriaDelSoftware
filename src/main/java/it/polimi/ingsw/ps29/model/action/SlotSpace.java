package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.game.Colour;
import it.polimi.ingsw.ps29.model.game.FamilyMember;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Resource;

public class SlotSpace implements ActionSpace{
	
	private FamilyMember familyMember;
	private ArrayList<Resource> bonus;
	
	public SlotSpace () {
		familyMember = null;
	}
	
	public SlotSpace (ArrayList<Resource> bonus) {
		familyMember = null;
		this.bonus = bonus;
	}
	
	@Override
	public boolean isEmpty () {
		return familyMember == null;
	}

	@Override
	public boolean familiarHere(Colour c) {
		return familyMember.getPlayerColor() == c;
	}
	

}
