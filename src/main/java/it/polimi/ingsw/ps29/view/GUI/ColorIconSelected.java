package it.polimi.ingsw.ps29.view.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class ColorIconSelected extends ColorIcon {

	public ColorIconSelected(int size, Color color) {
		super(size, color);
	}
	
	@Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.BLUE);
        g2d.fillOval(1, 1, size+6, size+6);
        g2d.setColor(color);
        g2d.fillOval(4, 4, size, size);
    }

}
