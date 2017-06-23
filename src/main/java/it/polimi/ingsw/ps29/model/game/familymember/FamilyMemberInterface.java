package it.polimi.ingsw.ps29.model.game.familymember;

import it.polimi.ingsw.ps29.model.game.Color;
import it.polimi.ingsw.ps29.model.game.DiceColor;

public interface FamilyMemberInterface {
	
	// public int setPower();
	
	public int getPower();
	
	public int getHarvestPower();
	
	public int getProductionPower();
	
	public int getTowerPower();
	
	public int getMarketPower();
	
	public int getCouncilPower();
	
	public DiceColor getFamiliarColor();
	
	public void setPower (int power);
	
	public void setBusy(boolean busy);
	
	public boolean getIfFixed();
	
	public void setFixedPower();
	
	public boolean getBusy ();
	
	public Color getPlayerColor();
	
}
