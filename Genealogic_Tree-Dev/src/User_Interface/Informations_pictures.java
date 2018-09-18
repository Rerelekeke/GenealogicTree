
package  User_Interface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Functionnalities.Debug_class;
import Functionnalities.FileHandler;
import User_Interface.Informations;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

import com.drew.imaging.ImageProcessingException;

import javax.swing.JButton;

//Popup de s�lection de la photo initiale du personnage 

public class Informations_pictures extends JPanel {    // Your class name
	private static final long serialVersionUID = 1L;
	static JPanel PanelPicture = new JPanel();
	static BufferedImage img = null ;
	static BufferedImage ScaledImg = null;
	static int iFrameWidth,iFrameHeight; 
	static JFrame f = new JFrame("A JFrame");
	static JPanel panel_1 = new JPanel();
	static boolean bImgToResize;
	static boolean bCancel = false;
	int x, y, x2, y2;
	static public int px, tx, py,ty;
	static JButton btnNewButton = null;
	static public BufferedImage CropImage;
	static Image img1 = null;
	static String textQuery;
	
	
   public static void main(String textQueryTmp) {
	   textQuery = textQueryTmp;
	   FileHandler.Windows_theme();
      f.setSize(800, 700);
      f.setLocation(0,0);
      f.getContentPane().setLayout(null);
      f.getContentPane().add(PanelPicture);      
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      f.addWindowListener(new java.awt.event.WindowAdapter() {
  	    @Override
  	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
  	        if (JOptionPane.showConfirmDialog(f, 
  	            "Etes-vous s�r de vouloir fermer cette page?", "Really Closing?", 
  	            JOptionPane.YES_NO_OPTION,
  	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
  	        	ScaledImg = null;
  	        	
  	        }
  	    }
  	});
      
      
      f.addComponentListener(new ComponentListener() {
		  public void componentResized(ComponentEvent e) {
			  bImgToResize = true;
			  iFrameWidth = f.getWidth();
			  iFrameHeight = f.getHeight();
		  }
		public void componentHidden(ComponentEvent arg0) {}
		public void componentMoved(ComponentEvent arg0) {}
		public void componentShown(ComponentEvent arg0) {}
		});

      Debug_class.Debug();
      Informations_pictures InfoPic = new  Informations_pictures();
      f.setContentPane(InfoPic);
    if (bCancel == true) {
    	InfoPic.removeAll();
    	f.removeAll();
    	f.setVisible(false); //you can't see me!
    	f.dispose(); //Destroy the JFrame object
    	bCancel = false;
    	return;
    	}
      f.setVisible(true);

    }
   
   

   Informations_pictures() {
   	f.setResizable(false);
       x = y = x2 = y2 = 0; // 
       Debug_class.Debug = false;
       MyMouseListener listener = new MyMouseListener();
       addMouseListener(listener);
       addMouseMotionListener(listener);

       Informations_pictures.Open_picture();
       
       
   }
   
   


   public static void Open_picture() {
	   bCancel = false;
//	   FileHandler Fichier = new FileHandler();
	      String sname = FileHandler.OpenFile(f,"*" + textQuery.replace(";", "*;*") + "*",null,null);
//	      try {
//			FileHandler.OpenFile();
//		} catch (ImageProcessingException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	      try {
	    	  if (sname =="-1") {System.out.println("return");
	    	  bCancel = true;
	    	  return;}
	    	  
	    	  img = ImageIO.read(new File(sname));
	    	  
	    	  bImgToResize = true;
	    	  btnNewButton = new JButton("New button");
	      } 
	      catch (IOException e) {
	      	e.printStackTrace();
	  		}	      
   }

   public void setStartPoint(int x, int y) {
       this.x = x;
       this.y = y;

   }

   public void setEndPoint(int x, int y) {
       x2 = (x);
       y2 = (y);
   }

   public  void drawPerfectRect(Graphics g, int px, int y, int tx, int y2) {

       
       int pw=Math.abs(tx-px);
       int ph=Math.abs(ty-py);
       if ((px >= (iFrameWidth - ScaledImg.getWidth()) /2) && (px <= ((iFrameWidth + ScaledImg.getWidth()) /2)) ) {
       g.drawRect(px, py, pw, ph);
       }
   }
   
   class MyMouseListener extends MouseAdapter {
       public void mousePressed(MouseEvent e) {
           setStartPoint(e.getX(), e.getY());
       }
       public void mouseDragged(MouseEvent e) {
           setEndPoint(e.getX(), e.getY());
           repaint();
       }

       public void mouseReleased(MouseEvent e) {
           setEndPoint(e.getX(), e.getY());
           repaint();
       }
   }
	public void paintComponent(Graphics g) {
		
		    super.paintComponent(g);

	    if (bCancel==false) {
		    g.setColor(Color.BLACK);
		    if (bImgToResize == true) {
			     ScaledImg = FileHandler.resize(img, iFrameHeight - 200);
			     if (ScaledImg.getWidth() > iFrameWidth) {
			    	 ScaledImg = FileHandler.resize(img, (int)((float) iFrameWidth/ScaledImg.getWidth() *iFrameHeight - 200));
			     }	     
			    bImgToResize = false;
		    	
		    }
		      g.drawImage(ScaledImg, (iFrameWidth - ScaledImg.getWidth()) /2,(iFrameHeight - ScaledImg.getHeight()) /2,null);
	
		      panel_1.setBounds(iFrameWidth/2-100, iFrameHeight-100, 200, 30);
		      f.getContentPane().add(panel_1);
		      panel_1.setLayout(null);
		      
		      JButton btnNewButton_1 = new JButton("S�lectionner l'image");
		      btnNewButton_1.setBounds(0, 0, 200, 30);
		      panel_1.add(btnNewButton_1);
		      
		      
		        px = Math.min(x,x2);
		       if( px <((iFrameWidth - ScaledImg.getWidth()) /2)) {px = ((iFrameWidth - ScaledImg.getWidth()) /2);}
		       tx = Math.max(x,x2);
		       if( tx >((iFrameWidth + ScaledImg.getWidth()) /2)) {tx = ((iFrameWidth + ScaledImg.getWidth()) /2);}
		       py = Math.min(y,y2);
		       if( py <((iFrameHeight - ScaledImg.getHeight()) /2)) {py = ((iFrameHeight - ScaledImg.getHeight()) /2);}
		       ty = Math.max(y,y2);
		       if( ty >((iFrameHeight + ScaledImg.getHeight()) /2)) {ty = ((iFrameHeight + ScaledImg.getHeight()) /2);}
		      
		       
		       btnNewButton_1.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent arg0) {
			    		Informations.status_panel = false;

			    		if (tx-px!=0 && ty-py != 0) {
			
		    			Informations.ImageSelected(ScaledImg.getSubimage(px-(iFrameWidth - ScaledImg.getWidth()) /2, py-(iFrameHeight - ScaledImg.getHeight()) /2, tx-px, ty-py));
			    		}
			    		else {
			    			Informations.ImageSelected(ScaledImg.getSubimage(0, 0, ScaledImg.getWidth(), ScaledImg.getHeight()));
			    		}

			    		ScaledImg = null;
			    		 bImgToResize = false;
			    		f.setVisible(false);
			    		
			    	}
			    });
	
		    drawPerfectRect(g, px, py, tx, ty);
		    
		}
	}
}