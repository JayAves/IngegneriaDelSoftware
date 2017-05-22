package it.polimi.ingsw.ps29.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps29.view.View;

public class Controller implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(!(o instanceof View)) {
			throw new IllegalArgumentException();
		}
	
	}

}
