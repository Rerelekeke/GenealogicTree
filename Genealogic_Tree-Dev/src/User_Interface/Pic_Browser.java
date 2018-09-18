
package User_Interface;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Functionnalities.Debug_class;
import Functionnalities.FileHandler;
import Functionnalities.PersonFileHandler;
import Functionnalities.TreeFileHandler;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


//Popup de sélection de la photo initiale du personnage 

public class Pic_Browser extends JPanel {    // Your class name
	private static final long serialVersionUID = 1L;
	static JPanel PanelPicture = new JPanel();
	static BufferedImage img = null ;
	static BufferedImage ScaledImg = null;
	static int iFrameWidth,iFrameHeight; 
	static JFrame f = new JFrame("A JFrame");

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
	public static List<File> files;
	public static int x,y,x2,y2,i=0;
	public static JButton NextButton;
	public boolean StatusNextButton = true;
	public boolean StatusPreviousButton = true;
	public boolean statusCreated = false;
	public JButton btnNextButton = null;
	public JButton btnPreviousButton = null;
	public boolean StatusOpenfile=true;
	boolean filetoread=true;
//	public BufferedImage ScaledImg;
	
	
   public static void main(String surname, String name) {
	   files = PersonFileHandler.Readtxtfile(surname, name);
	  
	   FileHandler.Windows_theme();
      f.setSize(800, 700);
      f.setLocation(0,0);
      f.getContentPane().add(PanelPicture);  
      
      PanelPicture.setAutoscrolls(true);
//      PanelPicture.se
      iFrameWidth = f.getWidth();
	  iFrameHeight = f.getHeight();
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      	
      f.addWindowListener(new java.awt.event.WindowAdapter() {
 
  	});
      

//      JScrollPane myJScrollPane = new JScrollPane(myJScrollPane,
//    	         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//    	         JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      
//      
//      JScrollPane scrollpane = new JScrollPane(PanelPicture);
//      scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//      scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//      f.getContentPane().add(scrollpane);
     

      Debug_class.Debug();

    	  Pic_Browser InfoPic = new  Pic_Browser();
          f.setContentPane(InfoPic);


      f.setVisible(true);

    }

   
   
   Pic_Browser() {

	     repaint();
   }
   
   


   
	public void paintComponent(Graphics g) {
		

		    super.paintComponent(g);

		    if (filetoread) {
		    	f.add(new JPanel(), BorderLayout.NORTH);
		    	f.add(new JScrollPane(), BorderLayout.CENTER);
//		      JScrollPane scrollpane = new JScrollPane(null);
//		      scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		      scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		      f.getContentPane().add(scrollpane);
			    for(File filetmp :files) {
			    	x = y = x2 = y2 = 0; 
//			        try {
//			 	    	  img = ImageIO.read(filetmp);
//			 	      } 
//			 	      catch (IOException e) {
//			 	      	e.printStackTrace();
//			 	  		}	  
//			        
			        
					try {
						ScaledImg = Thumbnails.of(filetmp)
						        .size(200, 200)
						        .asBufferedImage();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//			        Thumbnails.of(filetmp.listFiles())
//			        .size(640, 480)
//			        .outputFormat("jpg")
//			        .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
//			        BufferedImage ScaledImg = Scalr.resize(img, Method.QUALITY, 
//                            150, 100, Scalr.OP_ANTIALIAS);
//				     ScaledImg = FileHandler.resize(img, 150); 
				     
//			    ImageIcon icon = new ImageIcon(filetmp.getAbsolutePath());
			    ImageIcon icon = new ImageIcon(ScaledImg);
				JLabel button = new JLabel(icon);
				button.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {

				   	if(e.getButton() == MouseEvent.BUTTON1) {
				   		if( state_click == true) state_click =false;
				   		else {
//					   			File[] files = FileHandler.MultipleOpenFile(null,TreeFileHandler.CheckifExistingPerson(name,surname));
				   		  Desktop desktop = Desktop.getDesktop();

									try {
										desktop.open(filetmp);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							
							
				   		}
			          }
			          if(e.getButton() == MouseEvent.BUTTON3) {
			        	  
//				        	  PopUpDemo menu = new PopUpDemo(name,surname);
//				              menu.show(e.getComponent(), e.getX(), e.getY());
			          }
			   }
				 });
			   f.add(button);
			   filetoread = false;
			    }
			    
		    }
//		   button.setBounds(width, height, icon.getIconWidth(), icon.getIconHeight());
//		      g.drawImage(ScaledImg, (iFrameWidth - ScaledImg.getWidth()) /2,20 ,null);
//		      panel_1.setBounds(iFrameWidth/2-250, iFrameHeight-100, 500, 30);
//		      f.getContentPane().add(panel_1);

	}
	

	
	
}
	
		

