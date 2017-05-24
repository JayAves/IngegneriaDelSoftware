package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import javax.annotation.Resource;

import it.polimi.ingsw.ps29.model.cards.CardType;
import it.polimi.ingsw.ps29.model.game.Player;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.ResourceOld;

public class EmpowermentPlacementEffect extends EmpowermentActionEffect {

	private ArrayList<Resource> discount;
	private String cardType;
	
	public EmpowermentPlacementEffect(int diceEmpowerment, String cardType) {
		this.diceEmpowerment = diceEmpowerment;
		this.cardType = cardType;
		discount = new ArrayList <Resource> ();
	}
	
	void addResource (Resource res) {
		discount.add(res);
	}

	@Override
	void performEffect(Player player) {
		//deve applicare il valore aggiuntivo del dado alla singola torre (dipende da cardType, ev. All)
		//deve notificare lo sconto di risorse quando si prende lo specifico tipo di carta
		//boolean che notifica la presenza di uno sconto di risorse da mettere su tutte le torri della board?
		//se il valore è vero si cerca la carta con questo effetto permanente e se ne recuperano le risorse scontate
		
	}

}
