package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import javax.annotation.Resource;

import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberCharacterTowerDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberHarvestTowerDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberProductionTowerDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberVenturesTowerDecorator;

public class EmpowermentPlacementEffect extends EmpowermentActionEffect {

	private ArrayList<Resource> discount;
	private String towerType;
	
	public EmpowermentPlacementEffect(int diceEmpowerment, String cardType, ArrayList<Resource> discount) {
		this.diceEmpowerment = diceEmpowerment;
		this.towerType = cardType;
		this.discount = discount;
	}
	
	void addResource (Resource res) {
		discount.add(res);
	}

	@Override
	public void performEffect(Player player) {
		
		switch(towerType){
		case "Harvest" :
			player.setFakeFamiliar(new FakeFamilyMemberHarvestTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		case "Production" :
			player.setFakeFamiliar(new FakeFamilyMemberProductionTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		case "Character":
			player.setFakeFamiliar(new FakeFamilyMemberCharacterTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		case "Ventures":
			player.setFakeFamiliar(new FakeFamilyMemberVenturesTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		case "All" :
			player.setFakeFamiliar(new FakeFamilyMemberHarvestTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			player.setFakeFamiliar(new FakeFamilyMemberProductionTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			player.setFakeFamiliar(new FakeFamilyMemberCharacterTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			player.setFakeFamiliar(new FakeFamilyMemberVenturesTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		}
		//deve notificare lo sconto di risorse quando si prende lo specifico tipo di carta
		
		//boolean che notifica la presenza di uno sconto di risorse da mettere su tutte le torri della board?
		//se il valore è vero si cerca la carta con questo effetto permanente e se ne recuperano le risorse scontate
		//non è un modificatore che vale sempre per la risorsa MA solo quando si paga in una certa torre
		
	}

}
