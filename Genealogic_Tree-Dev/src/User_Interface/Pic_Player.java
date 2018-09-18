package User_Interface;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Functionnalities.Debug_class;
import Functionnalities.FileHandler;
import Functionnalities.PersonFileHandler;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JOptionPane;


//Popup de sélection de la photo initiale du personnage 

public class Pic_Player extends JPanel {    // Your class name
	private static final long serialVersionUID = 1L;
	static JPanel PanelPicture = new JPanel();
	static BufferedImage img = null ;
	static BufferedImage ScaledImg = null;
	static int iFrameWidth,iFrameHeight; 
	static JFrame f = new JFrame("A JFrame");
	static JPanel panel_1 = new JPanel();
	static boolean bImgToResize;
	static boolean bCancel = false;
	static public int px, tx, py,ty;
	static public BufferedImage CropImage;
	static Image img1 = null;
	public static boolean RectStatus;
	public static boolean PaintStatus =true;
	public static String Title;
	public static boolean state_click = false;
	public static File file = null;
	public static List<File> files = null;
	public static int x,y,x2,y2,i=0;
	public static JButton NextButton;
	public boolean StatusNextButton = true;
	public boolean StatusPreviousButton = true;
	public boolean statusCreated = false;
	public JButton btnNextButton = null;
	public JButton btnPreviousButton = null;
	public boolean StatusOpenfile=true;
	
	
   public static void main(String surname, String name) {
	   files = PersonFileHandler.Readtxtfile(surname, name);
	   file = files.get(0);
	   FileHandler.Windows_theme();
      f.setSize(800, 700);
      f.setLocation(0,0);
      f.getContentPane().setLayout(null);
      f.getContentPane().add(PanelPicture);      
      f.setBackground(Color.BLACK);
      iFrameWidth = f.getWidth();
	  iFrameHeight = f.getHeight();
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
//      f.addWindowListener(new java.awt.event.WindowAdapter() {
//  	    @Override
//  	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//  	        if (JOptionPane.showConfirmDialog(f, 
//  	            "Etes-vous sûr de vouloir fermer cette page?", "Really Closing?", 
//  	            JOptionPane.YES_NO_OPTION,
//  	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
//  	        	ScaledImg = null;
//  	        	
//  	        }
//  	    }
//  	});
      
      
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
      Pic_Player InfoPic = new  Pic_Player();
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

   Pic_Player() {
//   	f.setResizable(false);
       x = y = x2 = y2 = 0; 
       Open_picture();
   }
   
   


   public  void Open_picture() {
	   bCancel = false;
	      try {
	    	  img = ImageIO.read(file);
	    	  bImgToResize = true;
	      } 
	      catch (IOException e) {
	      	e.printStackTrace();
	  		}	   
	     repaint();
   }

   
	public void paintComponent(Graphics g) {
		
		
		    super.paintComponent(g);

	    if (bCancel==false) {
		    g.setColor(Color.BLACK);
		    if (bImgToResize == true) {
//			     ScaledImg = FileHandler.resize(img, iFrameHeight - 200);
//			     if (ScaledImg.getWidth() > iFrameWidth) {
//			    	 ScaledImg = FileHandler.resize(img, (int)((float) iFrameWidth/ScaledImg.getWidth() *iFrameHeight - 200));
//			     }	     
		    	
		    	try {
					ScaledImg = Thumbnails.of(img)
					        .size(2000, iFrameHeight - 200)
					        .asBufferedImage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    bImgToResize = false;
		    	
		    }
		      g.drawImage(ScaledImg, (iFrameWidth - ScaledImg.getWidth()) /2,20 ,null);
		      panel_1.setBounds(iFrameWidth/2-250, iFrameHeight-100, 500, 30);
		      f.getContentPane().add(panel_1);
		      panel_1.setLayout(null);
		      btnNextButton = new JButton("Suivant");
		      btnPreviousButton = new JButton("Précédent");

		      if (i>files.size() -2)  StatusNextButton = false;
		      if (i < 1 && files.size() > 1) StatusPreviousButton = false;
		      if (i > 0 ) StatusPreviousButton = true;
		      if (i == files.size() -2 )  StatusNextButton = true;

		      if (StatusNextButton == true) {

			      btnNextButton.setBounds(200, 0, 100, 30);
			      btnNextButton.setName("btnNextButton");
			      panel_1.add(btnNextButton);

			       btnNextButton.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent arg0) {
				    		if (i<files.size() -1) {
				    		 i++;
							 file = files.get(i);
							 Open_picture();
				    		}
				    	}
				    });
		      }
		      else {
		    	  
		    	  CleanPanel("btnNextButton");
		    	  
		      }
	
		      
		      if (StatusPreviousButton == true) {

			      btnPreviousButton.setBounds(0 ,0, 100, 30);
			      btnPreviousButton.setName("btnPreviousButton");
			      panel_1.add(btnPreviousButton);

			      btnPreviousButton.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent arg0) {
				    		if (i>0) {
				    		 i--;
							 file = files.get(i);
							 Open_picture();
				    		}
				    	}
				    });
		      }
		      else {
		    	  CleanPanel("btnPreviousButton");
		      }

		      if (StatusOpenfile == true) {
					JButton Openfile = new JButton("Ouvrir la Photo");
				      panel_1.add(Openfile);
				      Openfile.setBounds(400, 0, 100, 30);
				      Openfile.addActionListener(new ActionListener() {
				    	  Desktop desktop = Desktop.getDesktop();
					       					
									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										try {
											desktop.open(file);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
				      });
				      StatusOpenfile = false;
				}
		    
		}
	}
	
	void CleanPanel(String CompoString) {
		Component[] components = panel_1.getComponents();
	  	  for (Component compo : components) {
	  		  if (compo.getName()== CompoString)panel_1.remove(compo);
	  	  }
	}
	
	
}
	
	