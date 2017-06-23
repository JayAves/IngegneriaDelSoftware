package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {
	
	private GUICore gui;
	private CoordinateHandler coord;
	
	public MyMouseListener(GUICore gui) {
		this.gui = gui;
		this.coord = new CoordinateHandler();
	}


	@Override
	public void mouseClicked (MouseEvent event) {
		
		Point p = event.getPoint();
		System.out.println(p.getX()+" - "+p.getY());
		
		//indice da 0 a 15 che mi dice su quale carta ho cliccato
		int index = coord.getIndexCard(p);
		int idCard = gui.getTowers().getIdCard(index);
		
		gui.zoomImage(idCard);
	}

}
