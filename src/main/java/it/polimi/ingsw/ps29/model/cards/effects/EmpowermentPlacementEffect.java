package it.polimi.ingsw.ps29.model.cards.effects;

import java.util.ArrayList;

import javax.annotation.Resource;

import it.polimi.ingsw.ps29.model.game.Player;

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
	public void performEffect(Player player) {
		//deve applicare il valore aggiuntivo del dado alla singola torre (dipende da cardType, ev. All)
		//sia positivo che negativo
		//deve notificare lo sconto di risorse quando si prende lo specifico tipo di carta
		
		//boolean che notifica la presenza di uno sconto di risorse da mettere su tutte le torri della board?
		//se il valore è vero si cerca la carta con questo effetto permanente e se ne recuperano le risorse scontate
		//non è un modificatore che vale sempre per la risorsa MA solo quando si paga in una certa torre
		
	}

}
