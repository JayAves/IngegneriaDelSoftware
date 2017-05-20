package it.polimi.ingsw.ps29.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.Effect;

public class BonusAndMalusPlayer {
	
	private int [] towerValueModifier;
	private int productionValueModifier;
	private int harvestValueModifier;
	private ArrayList <Effect> permanentEffect;
	private int diceModifier;
	
	public int [] getTowerValueModifier() {
		return towerValueModifier;
	}
	public void setTowerValueModifier(int [] towerValueModifier) {
		this.towerValueModifier = towerValueModifier;
	}
	public int getProductionValueModifier() {
		return productionValueModifier;
	}
	public void setProductionValueModifier(int productionValueModifier) {
		this.productionValueModifier = productionValueModifier;
	}
	public int getHarvestValueModifier() {
		return harvestValueModifier;
	}
	public void setHarvestValueModifier(int harvestValueModifier) {
		this.harvestValueModifier = harvestValueModifier;
	}
	public int getDiceModifier() {
		return diceModifier;
	}
	public void setDiceModifier(int diceModifier) {
		this.diceModifier = diceModifier;
	}
	
	public BonusAndMalusPlayer(){
		
		this.towerValueModifier= new int[4];
		for(int i: towerValueModifier){
			towerValueModifier[i]=0;
			}
		this.productionValueModifier=0;
		this.harvestValueModifier=0;
		this.diceModifier=0;
		
	}
	
	

}
