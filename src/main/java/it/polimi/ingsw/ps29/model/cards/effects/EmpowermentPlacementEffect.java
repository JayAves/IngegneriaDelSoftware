package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;



import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberCharacterTowerDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberHarvestTowerDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberProductionTowerDecorator;
import it.polimi.ingsw.ps29.model.game.familymember.FakeFamilyMemberVenturesTowerDecorator;
import it.polimi.ingsw.ps29.model.game.resources.Resource;

/**
 * CARD EFFECT: Empowers familiars in tower placement.
 * @author Pietro Melzi
 * @author Giovanni Mele
 * @author Pietro Grotti
 *
 */
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
		case "venture":
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

		
	}
	
	public ArrayList<Resource> getDiscount () {
		return discount;
	}
	
	@Override 
	public String toString () {
		String msg = super.toString()+"Dice empowerment of "+diceEmpowerment+"for "+towerType.toUpperCase()+" with discount: ";
		for (Resource res: discount)
			msg+= res.toString()+", ";
		return msg;
	}
	
	public String getTowerType () {
		return towerType;
	}

}
