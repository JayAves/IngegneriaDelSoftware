package it.polimi.ingsw.ps29.DTO;

import java.io.Serializable;

/**
 * DTO implementation of object PersonalBonusTile
 * @author Pietro Melzi
 * @see it.polimi.ingsw.ps29.model.game.PersonalBonusTile
 *
 */
public class PersonalBonusTileDTO implements Serializable {
	
	//auto-generated serialVersionUID
	private static final long serialVersionUID = -381967889558860052L;
	private int id;
	private String tileToString;

	public PersonalBonusTileDTO(int id, String tileToString) {
		this.id = id;
		this.tileToString = tileToString;
	}
	
	@Override
	public String toString () {
		return tileToString;
	}
	
	public int getId (){
		return id;
	}

}
