package Functionnalities;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
 
public class ImagePane extends JPanel {
     
    private static final long   serialVersionUID    = 1L;
     
    protected Image buffer;    
    protected Image buffernew;   
    boolean status=false;
     
    public ImagePane(Image buffer){
    	this.buffer = null;
        this.buffer = buffer;
        buffernew=null;
        buffernew = buffer;
        repaint();
        
    }  
         
    public void paintComponent(Graphics g) {
    	System.out.println(status);
    	if (status == false) {
    		g.drawImage(buffernew,0,0,null);
    	}
    	status= true;
    }
}