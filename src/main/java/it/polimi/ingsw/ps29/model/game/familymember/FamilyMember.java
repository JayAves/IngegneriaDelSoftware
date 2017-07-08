package it.polimi.ingsw.ps29.model.game.familymember;

import it.polimi.ingsw.ps29.model.game.PlayerColor;
import it.polimi.ingsw.ps29.model.game.DiceColor;

/**
 * Familiar.
 * @author Giovanni Mele
 * @author Pietro Melzi
 * @author Pietro Grotti
 *
 */
public class FamilyMember implements FamilyMemberInterface{

	private int power;
	private boolean busy;
	private DiceColor familiarColor;
	private PlayerColor playerColor;
    private boolean fixedPower;
	public FamilyMember (DiceColor familiarColor, PlayerColor playerColor) {
		this.busy = false;
		this.familiarColor = familiarColor;
		this.playerColor = playerColor;
		fixedPower = false;
	}
	
	
	public int getHarvestPower(){
		 return getPower();
	}
	
	public int getProductionPower(){
		return getPower();
	}
	
	public int getTowerPower(){
		return getPower();
	}
	
	public int getMarketPower(){
		return getPower();
	}
	
	public int getCouncilPower(){
		return getPower();
	}

	public int getPower() {
		return this.power;
	}

	@Override
	public DiceColor getFamiliarColor() {
		return familiarColor;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public void setPower (int power) {
		this.power = power;
	}

	public void setBusy(boolean busy) {
		// TODO Auto-generated method stub
		this.busy=busy;
	}
	
	public boolean getBusy () {
		return busy;
	}
	
	public boolean getIfFixed(){
		return fixedPower;
	}
	
	public void setFixedPower(){
		fixedPower = true;
	}
	
	@Override
	public String toString() {
		return "FamilyMember [power=" + power + ", busy=" + busy + ", familiarColor=" + familiarColor + ", playerColor="
				+ playerColor + "]";
	}

}
