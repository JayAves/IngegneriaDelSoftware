package it.polimi.ingsw.ps29.controller;

import it.polimi.ingsw.ps29.messages.ActionChoice;
import it.polimi.ingsw.ps29.messages.BonusChoice;
import it.polimi.ingsw.ps29.model.action.Action;
import it.polimi.ingsw.ps29.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps29.model.action.HarvestAction;
import it.polimi.ingsw.ps29.model.action.LeaderAction;
import it.polimi.ingsw.ps29.model.action.MarketAction;
import it.polimi.ingsw.ps29.model.action.NoAction;
import it.polimi.ingsw.ps29.model.action.ProductionAction;
import it.polimi.ingsw.ps29.model.action.TowerAction;
import it.polimi.ingsw.ps29.model.game.Match;
import it.polimi.ingsw.ps29.model.game.Move;

/**
 * Contains methods to create Actions 
 * @author Pietro Melzi
 * @author Pietro Grotti
 * @author Giovanni Mele
 * @see Action
 */
public class ControllerSupporter {
	
	public static Action createAction (Match model, Move move, ActionChoice arg) {
		Action action = null;
		
		switch (arg.getChoice(0)) {
		case 1:
			action= new HarvestAction(model, move);
			break;
		case 2:
			action= new ProductionAction(model, move);
			break;
		case 3:
		case 4:
		case 5:
		case 6:
			action= new TowerAction(model, move);
			break;
		case 7:
		case 8:
		case 9:
		case 10:
			action= new MarketAction(model, move);
			break;
		case 11:
			action= new CouncilPalaceAction(model, move);
			break;
		case 13 :
			action = new LeaderAction(model, move, arg);
			break;
		default:
			action= new NoAction(model,move);
			break;
		}
		
		return action;
	}
	
	
	public static ActionChoice bonusActionChoice (BonusChoice msg) {
		ActionChoice choice = new ActionChoice (msg.getName());
		
		switch(msg.getBonus().getType().toLowerCase()) {
		case "harvest":
			choice.setChoice(0, 1);
			break;
		case "production":
			choice.setChoice(0, 2);
			break;
		case "territory":
			choice.setChoice(0, 3);
			break;
		case "building":
			choice.setChoice(0, 4);
			break;
		case "character":
			choice.setChoice(0, 5);
			break;
		case "venture":
			choice.setChoice(0, 6);
			break;
		case "all":
			
			switch (msg.getSpace()) {
			case 1:
				choice.setChoice(0, 3);
				break;
			case 2: 
				choice.setChoice(0, 4);
				break;
			case 3:
				choice.setChoice(0, 5);
				break;
			case 4:
				choice.setChoice(0, 6);
				break;
			case 5:
				choice.setChoice(0, 12);
				break;
			}
		}
		
		return choice;
	}

}
