package Functionnalities;


import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class RegionSelectorListener extends MouseAdapter {
    final JPanel label;
    int width;
    int height;
    int x, y, x2, y2;
    public RegionSelectorListener(JPanel theLabel) {
        this.label = theLabel;
        theLabel.addMouseListener(this);
    }

    Point origin = null;


    public void mouseReleased(MouseEvent event) {
        if (origin == null) { 
            origin = event.getPoint(); //set it.
        } else {
		    width = event.getX() - origin.x;
		    height = event.getY() - origin.y;
		    System.out.println("Selected X is: "+ origin.x);
		    System.out.println("Selected Y is: "+ origin.y);
		    System.out.println("Selected width is: "+ width);
		    System.out.println("Selected height is: "+ height);
		    Debug_class.Debug();

        }
        
    }


}