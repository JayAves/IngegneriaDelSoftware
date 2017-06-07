package it.polimi.ingsw.ps29.view;

import it.polimi.ingsw.ps29.model.DTO.InfoDTO;
import it.polimi.ingsw.ps29.model.cards.effects.ExchangeResourcesEffect;
import it.polimi.ingsw.ps29.view.usermessages.UserExchange;

public class InputOutputGUI implements InputOutput {

	@Override
	public void showUpdatedSituation(String playerName, InfoDTO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] askTypeOfAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askNumberOfServants() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askFamiliarColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserExchange askExchange(ExchangeResourcesEffect effect) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askFloor() {
		// TODO Auto-generated method stub
		return 0;
	}

}
