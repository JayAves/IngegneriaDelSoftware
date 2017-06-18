package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;



import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberCharacterTowerDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberHarvestTowerDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberProductionTowerDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberVenturesTowerDecorator;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

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
		towerType = towerType.toLowerCase();
		switch(towerType){
		case "territory" :
			player.setFakeFamiliar(new FakeFamilyMemberHarvestTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		case "building" :
			player.setFakeFamiliar(new FakeFamilyMemberProductionTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		case "character":
			player.setFakeFamiliar(new FakeFamilyMemberCharacterTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		case "ventures":
			player.setFakeFamiliar(new FakeFamilyMemberVenturesTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		case "all" :
			player.setFakeFamiliar(new FakeFamilyMemberHarvestTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			player.setFakeFamiliar(new FakeFamilyMemberProductionTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			player.setFakeFamiliar(new FakeFamilyMemberCharacterTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			player.setFakeFamiliar(new FakeFamilyMemberVenturesTowerDecorator(player.getFakeFamiliar(), diceEmpowerment));
			break;
		default:
		    break;
		}
		//deve notificare lo sconto di risorse quando si prende lo specifico tipo di carta
		
		//boolean che notifica la presenza di uno sconto di risorse da mettere su tutte le torri della board?
		//se il valore è vero si cerca la carta con questo effetto permanente e se ne recuperano le risorse scontate
		//non è un modificatore che vale sempre per la risorsa MA solo quando si paga in una certa torre
		
	}
	
	@Override 
	public String toString () {
		String msg = super.toString()+"Dice empowerment of "+diceEmpowerment+"for "+towerType.toUpperCase()+" with discount: ";
		for (Resource res: discount)
			msg+= res.toString()+", ";
		return msg;
	}

}
