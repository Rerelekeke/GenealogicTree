
package User_Interface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import net.coobird.thumbnailator.Thumbnails;


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
	public static int j=0;
	static boolean break_status=true;
	final static JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	static Pic_Browser canvas =null;
//	public BufferedImage ScaledImg;
	
	
   public static void main(String surname, String name) {
	   files = PersonFileHandler.Readtxtfile(surname, name);
	  j=0;
	  
	  FileHandler.Windows_theme();
      f.setSize(800, 300);
      f.setLocation(0,0);
      f.getContentPane().add(PanelPicture);  
      iFrameWidth = f.getWidth();
	  iFrameHeight = f.getHeight();
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      Debug_class.Debug();
	  canvas= new  Pic_Browser();
	  pane.setViewportView(canvas);
	  
	  canvas.setLayout(new FlowLayout());
	  
//      f.setContentPane(InfoPic);
      f.add(pane);
      f.validate();
      
      f.setVisible(true);
      
//      f.pack();
      

    }

   
   
   Pic_Browser() {
	    	 repaint();
	     
   }
   
   private void Repainting(){
	    	 repaint();
	     
   }


   
	public void paintComponent(Graphics g) {
		

		    super.paintComponent(g);
		    break_status=false;
		    if (filetoread) {
		    	f.add(new JPanel(), BorderLayout.NORTH);
		    	f.add(new JScrollPane(), BorderLayout.CENTER);

			    while(j<files.size()) {
			    	x = y = x2 = y2 = 0; 
			        
					try {
						ScaledImg = Thumbnails.of(files.get(j))
						        .size(200, 200)
						        .asBufferedImage();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				    ImageIcon icon = new ImageIcon(ScaledImg);
					JLabel button = new JLabel(icon);
					button.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {

					   	if(e.getButton() == MouseEvent.BUTTON1) {
					   		if( state_click == true) state_click =false;
					   		else {
					   				Desktop desktop = Desktop.getDesktop();
									try {
										desktop.open(files.get(j));
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
					   			}
					   		}
						}
					});
					   canvas.add(button);
					   
					   j++;
					   if (j%5 == 0) {
						   break_status = true;
						   Repainting();
						   break;
					   }
					   
			    }
			    if (break_status==false) filetoread = false;
			    
		    }

	}
	

	
	
}
	
		

