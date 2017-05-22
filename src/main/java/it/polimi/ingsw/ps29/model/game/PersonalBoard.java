package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.BuildingCard;
import it.polimi.ingsw.ps29.model.cards.CharacterCard;
import it.polimi.ingsw.ps29.model.cards.TerritoryCard;
import it.polimi.ingsw.ps29.model.cards.VentureCard;
import it.polimi.ingsw.ps29.model.game.resources.Resources;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Coin;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.FaithPoints;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.MilitaryPoints;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Resource;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Servants;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Stone;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.VictoryPoints;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Wood;

public class PersonalBoard {
	
	private ArrayList <TerritoryCard> territorySlot;
	private ArrayList <BuildingCard> buildingSlot;
	private ArrayList <CharacterCard> characterSlot;
	private ArrayList <VentureCard> ventureSlot;
	private PersonalBonusTile personalTile;
	//private ArrayList<Resource> resources;
	private Resources resources;
	
	private void addCharacter(CharacterCard card){
		characterSlot.add(card);
	}
	
	private void addBuilding(BuildingCard card){
		buildingSlot.add(card);
	}
	
	private void addTerritory(TerritoryCard card){
		territorySlot.add(card);
	}
	
	public void addVenture(VentureCard card){
		ventureSlot.add(card);
	}

	public PersonalBoard(PersonalBonusTile personalTile) {
		
		this.personalTile = personalTile;
		/*resources.add(new Coin(0));
		resources.add(new FaithPoints(0));
		resources.add(new MilitaryPoints(0));
		resources.add(new Servants(0));
		resources.add(new Stone(0));
		resources.add(new VictoryPoints(0));
		resources.add(new Wood(0));*/
		this.resources=new Resources();
		
	}
	
	public ArrayList<TerritoryCard> getTerritorySlot() {
		return territorySlot;
		
	}

	public ArrayList<BuildingCard> getBuildingSlot() {
		// TODO Auto-generated method stub
		return buildingSlot;
	}
	
	public PersonalBonusTile getPersonalBonusTile() {
		// TODO Auto-generated method stub
		return personalTile;
	}

}
