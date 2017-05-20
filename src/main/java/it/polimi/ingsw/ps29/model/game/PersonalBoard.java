package it.polimi.ingsw.ps29.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps29.model.cards.BuildingCard;
import it.polimi.ingsw.ps29.model.cards.CharacterCard;
import it.polimi.ingsw.ps29.model.cards.TerritoryCard;
import it.polimi.ingsw.ps29.model.cards.VentureCard;
import it.polimi.ingsw.ps29.model.provvisorio.packageAlternativoRisorse.Resource;

public class PersonalBoard {
	private ArrayList <TerritoryCard> territorySlot;
	private ArrayList <BuildingCard> buildingSlot;
	private ArrayList <CharacterCard> characterSlot;
	private ArrayList <VentureCard> ventureSlot;
	private PersonalBonusTile personalTile;
	private Resource [] resources;

}
