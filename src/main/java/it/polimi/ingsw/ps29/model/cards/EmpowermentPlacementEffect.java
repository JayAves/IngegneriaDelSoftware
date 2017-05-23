package it.polimi.ingsw.ps29.model.cards;

import java.util.ArrayList;

import javax.annotation.Resource;

import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.ResourceOld;

public class EmpowermentPlacementEffect extends EmpowermentDiceEffect {

	private ArrayList<Resource> discount;
	private ArrayList<CardType> placementType;
	
	public EmpowermentPlacementEffect(int diceEmpowerment, ArrayList<Resource> discount, ArrayList<CardType> placementType) {
		super(diceEmpowerment);
		// TODO Auto-generated constructor stub
		this.discount=discount;
		this.placementType=placementType;
	}

}
