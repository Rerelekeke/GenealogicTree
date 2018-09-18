/**
 * 
 */
/**
 * @author remit
 *
 */
package User_Interface;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import java.awt.image.BufferedImage;
import Functionnalities.FileHandler;
import Functionnalities.ImagePane;
import Functionnalities.TreeFileHandler;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;


public class Informations extends JPanel {
	public Informations() {
	}
	
	private static JTextField textFieldQueryFilterPicture;
	private static JTextField textLastName;
	private static JTextField textFirstName;
	private static JTextField textFieldFather;
	private static JTextField textFieldMother;
	public static Integer widthImg=0;
	public static Integer heightImg=0;
	static BufferedImage resizedImage = null;
	public static boolean status_panel = false;
	static BufferedImage CroppedImageintOld=null;
	public static JPanel panel_picture = null;
	static BufferedImage CroppedImage;
	static boolean bCroppedImageStatus;
	static int i;
	static JFrame InfoWindow;
	static String LineToModify=null;
	static JLabel button;
	static Graphics2D g ;

	
	  public static void main(String query,String name, String surname){

  			FileHandler.Windows_theme();
  			//Mise en place de la fenetre d'informations
		    InfoWindow = new JFrame();
		    InfoWindow.setTitle("Informations");
		    InfoWindow.setSize(676, 618);
		    InfoWindow.setLocationRelativeTo(null);
		    InfoWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    InfoWindow.getContentPane().setLayout(null);
		    // Fin de la mise en place de la fenetre d'informations

		    
		    textFieldQueryFilterPicture = new JTextField();
		    textFieldQueryFilterPicture.setBounds(80, 220, 376, 107);
		    InfoWindow.getContentPane().add(textFieldQueryFilterPicture);
		    textFieldQueryFilterPicture.setColumns(10);
		    
		    JLabel lblNewLabelQUery = new JLabel("<html> Requ\u00EAte de recherche des photos : <br> Lister les mots d�crivant la personne, s�parer les mots ou expressions que vous voulez rechercher par des poinits virgules</html>");
		    lblNewLabelQUery.setHorizontalAlignment(SwingConstants.CENTER);
		    lblNewLabelQUery.setBounds(141, 170, 300, 50);
		    InfoWindow.getContentPane().add(lblNewLabelQUery);
		    	    		    
		    
		    JLabel lblNewLabelTtile = new JLabel("Cr\u00E9er une nouvelle personne");
		    lblNewLabelTtile.setHorizontalAlignment(SwingConstants.CENTER);
		    lblNewLabelTtile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    lblNewLabelTtile.setBounds(141, 10, 383, 51);
		    InfoWindow.getContentPane().add(lblNewLabelTtile);
		    
		    JLabel lblName = new JLabel("Nom :");
		    lblName.setBounds(389, 100, 45, 13);
		    textLastName = new JTextField();
		    textLastName.setText("Nom");
		    textLastName.setBounds(444, 100, 150, 19);
		    InfoWindow.getContentPane().add(textLastName);
		    textLastName.setColumns(10);
		    FocusFunction(textLastName,"Nom");
		    
		    InfoWindow.getContentPane().add(lblName);
		    
		    JLabel lblFirstName = new JLabel("Pr\u00E9nom :");
		    lblFirstName.setBounds(80, 100, 45, 13);
		    InfoWindow.getContentPane().add(lblFirstName);
		    textFirstName = new JTextField();
		    textFirstName.setText("Pr\u00E9nom");
		    textFirstName.setColumns(10);
		    textFirstName.setBounds(145, 100, 150, 19);
		    FocusFunction(textFirstName,"Pr\u00E9nom");
		    InfoWindow.getContentPane().add(textFirstName);
		    
		    JLabel labelFather = new JLabel("P\u00E8re :");
		    labelFather.setBounds(80, 130, 45, 13);
		    InfoWindow.getContentPane().add(labelFather);
		    textFieldFather = new JTextField();
		    textFieldFather.setText("P\u00E8re");
		    textFieldFather.setColumns(10);
		    textFieldFather.setBounds(145, 130, 150, 19);
		    FocusFunction(textFieldFather,"P\u00E8re");
		    InfoWindow.getContentPane().add(textFieldFather);
		    
		    JLabel lblMother = new JLabel("M\u00E8re :");
		    lblMother.setBounds(389, 130, 45, 13);
		    InfoWindow.getContentPane().add(lblMother);
		    textFieldMother = new JTextField();
		    textFieldMother.setText("M\u00E8re");
		    textFieldMother.setColumns(10);
		    textFieldMother.setBounds(444, 130, 150, 19);
		    FocusFunction(textFieldMother,"M\u00E8re");
		    InfoWindow.getContentPane().add(textFieldMother);
		    		    
		    JButton btnOpenPicture = new JButton("Selectionner une image");
		    btnOpenPicture.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		Informations_pictures.main(textFieldQueryFilterPicture.getText());
		    	}
		    });
		    btnOpenPicture.setBounds(266, 350, 141, 21);
		    InfoWindow.getContentPane().add(btnOpenPicture);
		    
		    JButton btnAutoFillQuery = new JButton("<html> Remplissage automatique <br> du champ de requ�te <html>");
		    btnAutoFillQuery.setHorizontalAlignment(SwingConstants.CENTER);
		    btnAutoFillQuery.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
//		    		String text = textFieldQueryFilterPicture.getText() ;
		    		if (textFieldQueryFilterPicture.getText().equals("")) {
		    			textFieldQueryFilterPicture.setText(textFirstName.getText() + " " + textLastName.getText() + ";" + textLastName.getText()+ " " +textFirstName.getText() );
		    		}
		    		else {
		    		textFieldQueryFilterPicture.setText(textFieldQueryFilterPicture.getText() + ";" + textFirstName.getText() + " " + textLastName.getText() + ";" + textLastName.getText()+ " " +textFirstName.getText() );
		    		}
	    		}
		    });
		    btnAutoFillQuery.setBounds(480, 240, 160, 50);
		    InfoWindow.getContentPane().add(btnAutoFillQuery);
		    
		    
		    
		    JButton btnCreateNewPerson = new JButton("Cr\u00E9er une nouvelle personne");
		    if (name!=null && surname!=null ) {
				btnCreateNewPerson.setText("Modifier cette personne");
		    }
		    btnCreateNewPerson.addActionListener(new ActionListener() {

		    	public void actionPerformed(ActionEvent arg0) {
		    		
		    		if (!TreeFileHandler.CheckifExistingPerson( textFirstName.getText(),textLastName.getText()).equals("error -404") && name==null && surname==null ) 
		    			{
		    				
			    			JOptionPane.showConfirmDialog(null, "Une personne poss�dant ce nom et ce pr�nom existe d�j� dans cet arbre g�n�alogique. "
			    					+ "\nVeuillez changer un des champs suivants: \n		- Nom \n		- Pr�nom",
			    					"?",JOptionPane.DEFAULT_OPTION);
			    			return;
		    			}
		    		Tree.main(null);
		    		
		    								if (name!=null && surname!=null && LineToModify!=null) {
		    									btnCreateNewPerson.setText("Modifier cette personne");
		    									try {
													TreeFileHandler.BufferedModifyer(LineToModify,System.getProperty("line.separator") + textFirstName.getText() + "," + textLastName.getText() +"," + textFieldMother.getText() +"," + textFieldFather.getText() + "," + textFieldQueryFilterPicture.getText().replaceAll(";;",";"));
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
		    								}
		    								else {
									  			try {
													TreeFileHandler.BufferedWritter(System.getProperty("line.separator") + textFirstName.getText() + "," + textLastName.getText() +"," + textFieldMother.getText() +"," + textFieldFather.getText() + "," + textFieldQueryFilterPicture.getText().replaceAll(";;",";")  );
												} catch (IOException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
		    								}
									  			try {
									  				ImageIO.write(resizedImage,"png",new File(System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/" +textFirstName.getText()+"_"+textLastName.getText()+".png"));
									  			} catch ( Exception e) {
									  			   e.printStackTrace();
									  			}
									  			
		    		 InfoWindow.setVisible(false);
		    		 Tree.RectStatus=true;
		    		 Tree.PaintStatus=true;
		    		 try {
		    	    		if (JOptionPane.showConfirmDialog(null, 
		    	      	            "Etes-vous s�r de vouloir actualiser la liste de photos li�es? \n"
		    	      	            + "Si oui, une fen�tre d'information vous confirmera la bonne fin de l'actualisation", "Demande de confirmation d'actualisation", 
		    	      	            JOptionPane.YES_NO_OPTION,
		    	      	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
								try {
									{
		    	    			FileHandler.ExifContaining("D:\\Images\\Photo", query,surname,name);
		    	    			JOptionPane.showConfirmDialog(null, "La liste de photos li�es est actualis�e \n" + FileHandler.ErrorList,
		        		    			"Fin actualisation photo li�e",JOptionPane.DEFAULT_OPTION);	  
		    					
		    	      	        }
								} catch (HeadlessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		    	    		
							
//						} catch (ImageProcessingException e) {
//							// TODO Auto-generated catch block
////							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
							JOptionPane.showConfirmDialog(null, "L'actualisation des photos s'est termin�e avec des erreurs, veuilez contacter le d�velopeur pour de plus amples informations",
		    		    			"ERREUR",JOptionPane.DEFAULT_OPTION);	   
						}

		    	}
		    });
		    btnCreateNewPerson.setBounds(246, 550, 180, 21);
		    InfoWindow.getContentPane().add(btnCreateNewPerson);
		    
		    if (name !=null && surname!=null) {
	               for(int j = 0; j < Tree.TreeString.length && Tree.TreeString[j] != ""; j++) {  
					   if (j>1) {
						   String string = Tree.TreeString[j];
						   String parts[] = string.split(",");
						   if(parts[0].equals(name) && parts[1].equals(surname)) {
							   LineToModify = string;
							   textLastName.setText(surname);
							   textFirstName.setText(name);
							   textFieldFather.setText(parts[3]);
							   textFieldMother.setText(parts[2]);
							   textFieldQueryFilterPicture.setText(parts[4]);
						   }
					   }
	               }
           BufferedImage img;
				try {
					img = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/"+name+"_"+surname+".png"));
					ImageSelected (img);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		    
		    
		    InfoWindow.setVisible(true);
	  }
	  private static void FocusFunction(JTextField test_objet , String Default_Text ) {
		  test_objet.setForeground(Color.gray);
		  test_objet.addFocusListener(new FocusListener() {
		    	public void focusGained(FocusEvent e) {
		    		if (test_objet.getText().toString().equals(Default_Text)) {
		    			test_objet.setText("");
		    			test_objet.setForeground(Color.black);
		    		}
				}
		    	public void focusLost(FocusEvent e) {
		    		if (test_objet.getText().toString().equals("")) {
		    			test_objet.setText(Default_Text);
		    			test_objet.setForeground(Color.gray);
		    		}
				}
		    });
	  }

	public static void ImageSelected (BufferedImage CroppedImageint) {

		  System.out.println("resetimg");
		  resizedImage = null;
		  System.out.println("status_panel" + status_panel);
		  
		  if (status_panel== true) {
			  panel_picture.removeAll();
			  panel_picture.revalidate();
//			  return;
		  }
		  resizedImage = null;
		  if (CroppedImageint.getHeight()> 150) {
			   resizedImage = FileHandler.resize(CroppedImageint,150);
		  }
		  else {
			   resizedImage = new BufferedImage( CroppedImageint.getWidth()*150/CroppedImageint.getHeight(),150,CroppedImageint.getType());
			    g = resizedImage.createGraphics();
			   g.drawImage(CroppedImageint, 0, 0, CroppedImageint.getWidth()*150/CroppedImageint.getHeight() , 150 , null); 	

		  }


		  panel_picture = null;
		  panel_picture = new ImagePane(resizedImage);		  
		  widthImg=resizedImage.getWidth();
		  heightImg=resizedImage.getHeight();
		  panel_picture.setBounds((InfoWindow.getWidth()- widthImg)/2, 380, widthImg,heightImg);
		  InfoWindow.getContentPane().add(panel_picture);
		  InfoWindow.setVisible(false);
		  InfoWindow.setVisible(true); 
		  status_panel =  true;

	  }
	

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
		  widthImg=resizedImage.getWidth();
		  heightImg=resizedImage.getHeight();
       g.drawImage(resizedImage,(InfoWindow.getWidth()- widthImg)/2,83,null);
     }
	  
	  
	  

}