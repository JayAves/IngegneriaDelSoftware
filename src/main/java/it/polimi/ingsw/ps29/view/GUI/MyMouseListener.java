package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {

	@Override
	public void mouseClicked (MouseEvent event) {
		Point p = event.getPoint();
		System.out.println(p.getX()+" - "+p.getY());
	}

}
