package User_Interface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import Functionnalities.Debug_class;
import Functionnalities.FileHandler;
import Functionnalities.TreeFileHandler;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.Action;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;



public class Tree extends JPanel {
	public static boolean RectStatus = false;
	public static boolean PaintStatus =true;
	public static boolean modificationspainting = false;
	public static String Title,BackupDirectory,ImageFolder;
	public static boolean state_click = false;
	public static boolean InitStatus = false;
	JProgressBar pbar;
	
	public static int x,y;
	
	public static String TreeString[] = new String[500];
	public Tree() {

		MyMouseListener listener = new MyMouseListener();
	       addMouseListener(listener);
	       addMouseMotionListener(listener);
	       
	       AdjustmentListener listener1 = new MyAdjustmentListener();
	       pane.getHorizontalScrollBar().addAdjustmentListener(listener1);
	       pane.getVerticalScrollBar().addAdjustmentListener(listener1);
	       

		JMenuBar menuBar = new JMenuBar();
		TreeWindow.setJMenuBar(menuBar);


		JMenu mnFile = new JMenu("Fichier");
		menuBar.add(mnFile);
		
		JMenu mnDisplay = new JMenu("Affichage");
		menuBar.add(mnDisplay);
		
		JMenu mnEdit = new JMenu("Edition");
		menuBar.add(mnEdit);
		
		JToolBar toolbar = new JToolBar();
		
		
	    ImageIcon iconPlus = new ImageIcon(getClass().getResource("/plus.png"));
		ImageIcon iconMinus = new ImageIcon(getClass().getResource("/minus.png"));
		
		JButton PlusButton = new JButton(iconPlus);
		PlusButton.addActionListener((ActionEvent event) -> {
			IncreaseSurfaceFunction();
		});
		
		JButton MinusButton = new JButton(iconMinus);
		MinusButton.addActionListener((ActionEvent event) -> {
			DecreaseSurfaceFunction();
		});
		
		toolbar.add(PlusButton);
		toolbar.add(MinusButton);
		
		menuBar.add(toolbar);
		
		JMenuItem mntmAjouterUnePersonne = new JMenuItem("Ajouter une personne");
		mntmAjouterUnePersonne.setAction(actionAddPerson);  
		mnFile.add(mntmAjouterUnePersonne);
		
		JMenuItem mntmOuvrirUnArbre = new JMenuItem("Ouvrir un nouvel arbre Généalogique");
		mntmOuvrirUnArbre.setAction(actionOpenTree); 
		mnFile.add(mntmOuvrirUnArbre);

		JMenuItem mntmCreerUnNouvelArbre = new JMenuItem("Créer un nouvel arbre Généalogique");
		mntmCreerUnNouvelArbre.setAction(actionCreateTree); 
		mnFile.add(mntmCreerUnNouvelArbre);
		

		JMenuItem mntmModifyPath = new JMenuItem("Modifier le répertoire de Backup de l'arbre généalogique");
		mntmModifyPath.setEnabled(false);
		mntmModifyPath.setAction(ActionModifyBackupPath); 
		mnEdit.add(mntmModifyPath);
		
		JMenuItem mntmModifyImgPath = new JMenuItem("Modifier le répertoire d'images de l'arbre généalogique");
		mntmModifyImgPath.setEnabled(false);
		mntmModifyImgPath.setAction(ActionModifyImagePath); 
		mnEdit.add(mntmModifyImgPath);
		
		JMenuItem mntmDecreaseWorkSurface = new JMenuItem("Réduire la surface de travail");
		mntmDecreaseWorkSurface.setAction(DecreaseWorkSurface); 
		mnDisplay.add(mntmDecreaseWorkSurface);
		
		JMenuItem mntmIncreaseWorkSurface = new JMenuItem("Augmenter la surface de travail");
		mntmIncreaseWorkSurface.setAction(IncreaseWorkSurface); 
		mnDisplay.add(mntmIncreaseWorkSurface);
		TreeWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		TreeWindow.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(TreeWindow, 
		            "Etes-vous sûr de vouloir quitter l'application?", "Fermer la fenetre?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	if (Tree.Title != null) {
		        		try {
							TreeFileHandler.BufferedReplacement(2,TreeString[1].split(",")[2],Integer.toString(canvas.getWidth()));
							TreeFileHandler.BufferedReplacement(2,TreeString[1].split(",")[3],Integer.toString(canvas.getHeight()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("error!!!!!!");
							e1.printStackTrace();
						}
		        		Path sourceDirectory= Paths.get(System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/");
			        	TreeFileHandler.Readtxtfile(false);
			        	Path targetDirectory = Paths.get(Tree.TreeString[1].split(",")[1]+Tree.Title+"/");
			        	
			    		if (Files.exists(sourceDirectory)){
			    			try {
	
			    				  copy(sourceDirectory, targetDirectory);
	//							Files.copy(sourceDirectory, targetDirectory);
							} catch (IOException e) {
								// TODO Auto-generated catch block	
								e.printStackTrace();
							}
			    		}
			        	
			            
		        	}
		        	System.exit(0);	
		        }

		        

		    }
		});
		
		
	 
		
	   
		Debug_class.Debug();
		
	}
	

	private static final long serialVersionUID = 1L;
 
	public static JFrame TreeWindow;
	private final Action actionAddPerson = new SwingActionAddPerson(); 
	private final Action actionCreateTree = new SwingActionCreateTree(); 
	private final Action actionOpenTree = new SwingActionOpenTree(); 
	private final Action ActionModifyBackupPath = new SwingActionModifyBackupPath(); 
	private final Action ActionModifyImagePath = new SwingActionModifyImagePath(); 
	private final Action DecreaseWorkSurface = new SwingDecreaseWorkSurface(); 
	private final Action IncreaseWorkSurface = new SwingIncreaseWorkSurface(); 
	final static JScrollPane pane = new JScrollPane();
	final static JPanel panel = new JPanel();	
	public static Tree canvas=null;
	
	   static void main(){
		   TreeWindow = new JFrame("Arbre Généalogique");
		   TreeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  FileHandler.Windows_theme();
	      TreeWindow.setSize(1024, 768);

	      canvas = new Tree();
			
			pane.setViewportView(canvas);


			TreeWindow.add(pane);

		    canvas.setLayout(null);
    
		    TreeWindow.setVisible(true);
		    canvas.setPreferredSize(new Dimension(1024, 768));
		    pane.revalidate();

//		    SwingUtilities.invokeLater(new Runnable() {
//		      public void run() {
//		        
//		        
//		      }
//		    });
//		  
	  }
	   


	private class SwingActionAddPerson extends AbstractAction { 
		private static final long serialVersionUID = 1L;
		public SwingActionAddPerson() {
			putValue(NAME, "Ajouter un personnage");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		    if(Tree.Title == null) {
		    	JOptionPane.showConfirmDialog(null, "Aucun abre généalogique n'est sélectionné. \n Veuillez en ouvrir un ou en créer un via le menu de la page principale.",
		    			"Erreur, absence d'arbre genéalogique sélectionné!!",JOptionPane.DEFAULT_OPTION);	    
		    }
		    else{
		    	Informations.main(null,null,null);
		    }
		}
	}
	
	private class SwingActionCreateTree extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingActionCreateTree() {
			putValue(NAME, "Creer un Arbre généalogique");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			RectStatus = true;
			Genealogic_Tree_Title.main();
		}
	} 

	private class SwingActionOpenTree extends AbstractAction {

		private static final long serialVersionUID = 1L;
		public SwingActionOpenTree() {
			putValue(NAME, "Ouvrir un Arbre généalogique");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			boolean Readtxtfile_result = TreeFileHandler.Readtxtfile(true);
			if (Readtxtfile_result==false) return;
			PaintStatus=false;
			RectStatus = false;
			int[] dimensions = TreeFileHandler.GetTreeHeightAndWidth(TreeString[1]);
			canvas.setPreferredSize(new Dimension(dimensions[0], dimensions[1]));
			pane.revalidate();
			repaint();
		}
	}
	
	private class SwingActionModifyImagePath extends AbstractAction {     
		private static final long serialVersionUID = 1L;
		public SwingActionModifyImagePath() {
			putValue(NAME, "Modifier le dossier d'images");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			new ModificationPathImage();
		}
	}

	private class SwingActionModifyBackupPath extends AbstractAction {     
		private static final long serialVersionUID = 1L;
		public SwingActionModifyBackupPath() {
			putValue(NAME, "Modifier le dossier de sauvegarde");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			new ModificationPathBackup();
		}
	}
	
	private class SwingDecreaseWorkSurface extends AbstractAction {     
		private static final long serialVersionUID = 1L;
		public SwingDecreaseWorkSurface() {
			putValue(NAME, "Diminuer la surface de travail");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			DecreaseSurfaceFunction();
		}
	}
	
	private class SwingIncreaseWorkSurface extends AbstractAction {     
		private static final long serialVersionUID = 1L;
		public SwingIncreaseWorkSurface() {
			putValue(NAME, "Augmenter la surface de travail");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			IncreaseSurfaceFunction();
		}
	}
	
	public void DecreaseSurfaceFunction() {
		int Width = (int) (canvas.getWidth()*0.90);
		int Height = (int) (canvas.getHeight()*0.90);
		canvas.setPreferredSize(new Dimension(Width, Height));
		pane.revalidate();
	}
	public void IncreaseSurfaceFunction() {
		int Width = (int) (canvas.getWidth()*1.10);
		int Height = (int) (canvas.getHeight()*1.10);
		canvas.setPreferredSize(new Dimension(Width, Height));
		pane.revalidate();
	}
	class MyAdjustmentListener implements AdjustmentListener {
		  public void adjustmentValueChanged(AdjustmentEvent evt) {
		    Adjustable source = evt.getAdjustable();
		    if (evt.getValueIsAdjusting()) {
		      return;
		    }
		    int orient = source.getOrientation();
		    if (orient == Adjustable.HORIZONTAL) {
		      System.out.println("from horizontal scrollbar"); 
		    } else {
		      System.out.println("from vertical scrollbar");
		    }
		    int type = evt.getAdjustmentType();
		    switch (type) {
		    case AdjustmentEvent.TRACK:


		    	if(InitStatus && Title != null) {
		    		PaintStatus=false;
		    		repaint();
		    	}

		      break;
		    }
		  }
		}
	class MyMouseListener extends MouseAdapter {
	int[] cells;
    BufferedImage bi;
	  public void mouseMoved(MouseEvent e) {
		  if (RectStatus==true) {
		       x = e.getX();
		       y = e.getY();
		       repaint();
		  }
	    }
	  
	  public void mousePressed(MouseEvent e) {

		  if (RectStatus==true) {
		       TreeWindow.setLayout(null);
			  String temp = Integer.toString((int) (x-Informations.widthImg/2));
			  try {
				TreeFileHandler.BufferedWritter("," + temp );
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			  temp = Integer.toString((int) (y-Informations.heightImg/2));
			  try {
				TreeFileHandler.BufferedWritter("," + temp );
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  RectStatus=false;
			  boolean Readtxtfile_result = TreeFileHandler.Readtxtfile(false);
				if (Readtxtfile_result==false) return;
			  PaintStatus=false;
			  repaint();
	    }	       
	  } 
	}
	public void copy(Path sourceLocation, Path targetLocation)  throws IOException {

  		    try (Stream<Path> files = Files.walk(sourceLocation)) {
  		    	if (Files.exists(targetLocation)) deleteDirectory(targetLocation.toFile());    	
  		        files
  		            .forEach(file -> {
  		                Path relative = sourceLocation.relativize(file);
  		              
  		                try {
  		                	Path temp =  Paths.get(targetLocation.toString() + "/"+ file.getFileName().toString());

	  		                	if (Files.exists(temp) ) {
	  		                		Files.delete(temp);
	  		                		Files.copy(file, targetLocation.resolve(relative));
	  		                	}
	  		                	if (!Files.exists(temp)){
	  		                		Files.copy(file, targetLocation.resolve(relative));
	  		                	}

  		                		
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
  		            });
  		    }
  		}
	public void deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    directoryToBeDeleted.delete();
	}
		
		public void paintComponent(Graphics g) {
			int width,height,originX_mother,originY_mother,originX_father,originY_father;
			int status_parent;
			String string,string1;
			String[] parts,parts1;
			Image img,img1;
		    g.setColor(Color.BLACK);

			if (PaintStatus==false && InitStatus ==true) {
//				if(modificationspainting==false) {
					super.paintComponent(g);
					canvas.removeAll();
//				}
				
//			}
//			else {
				   for(int i = 0; i < TreeString.length && TreeString[i] != ""; i++) {
					   if (i>2) {
						   string = TreeString[i];
						   parts = string.split(",");
						   width = Integer.valueOf(parts[5]);
						   height = Integer.valueOf(parts[6]);
//						   System.out.println(parts[5]);
//						   System.out.println(parts[6]);
//						   System.out.println(System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/"+parts[0]+"_"+parts[1]+".png");
						   status_parent = 0;
						   parts1 =null;
						   originX_mother = 0;
						   originY_mother = 0;
						   originX_father = 0;
						   originY_father = 0;
						   for(int j = 0; j < TreeString.length && TreeString[j] != ""; j++) {  
							   if (j>2) {
								   string1 = TreeString[j];
								   parts1 = string1.split(",");
//								   System.out.println(parts[3].split(" ")[0]);
								   if (parts[3].split(" ").length >1) {System.out.println(parts[3].split(" ")[1]);}
								   if ((parts[2].split(" ")[0].equals(parts1[0]) && parts[2].split(" ")[1].equals(parts1[1]))) {
								   		status_parent = status_parent + 2;
									   originX_mother = Integer.valueOf(parts1[5]);
									   originY_mother = Integer.valueOf(parts1[6]);
								   }
								   if ((parts[3].split(" ")[0].equals(parts1[0]) && parts[3].split(" ")[1].equals(parts1[1]))) {
								   		status_parent = status_parent + 1;
									   originX_father = Integer.valueOf(parts1[5]);
									   originY_father = Integer.valueOf(parts1[6]);
								   }
							   }
						   }
						   try {
//							   System.out.println((System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/"+parts[0]+"_"+parts[1]+".png"));
							   img= ImageIO.read(new File(System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/"+parts[0]+"_"+parts[1]+".png"));
							   ImageIcon icon = new ImageIcon(new File(System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/"+parts[0]+"_"+parts[1]+".png").getAbsolutePath());
							   JLabel button = new JLabel(icon);
							   String name = parts[0];
							   String surname = parts[1];
							   String query= parts[4];
							   button.addMouseListener(new MouseAdapter() {
									   public void mouseClicked(MouseEvent e) {
										   	System.out.println("test");
										   	if(e.getButton() == MouseEvent.BUTTON1) {
										   		if( state_click == true) state_click =false;
										   		else {
//										   			File[] files = FileHandler.MultipleOpenFile(null,TreeFileHandler.CheckifExistingPerson(name,surname));
									    	    	Pic_Player.main(surname, name);
										   		}
									          }
									          if(e.getButton() == MouseEvent.BUTTON3) {
									        	  
									        	  PopUpDemo menu = new PopUpDemo(name,surname,query);
									              menu.show(e.getComponent(), e.getX(), e.getY());
									          }
									   }
								 });
							   canvas.add(button);
							   button.setBounds(width, height, icon.getIconWidth(), icon.getIconHeight());
								JLabel lbl = new JLabel(parts[0]+ " " + parts[1]);
								lbl.setBounds(width +20, height + img.getHeight(null)+5, 200, 20);
								canvas.add(lbl);

								if (status_parent == 1 || status_parent == 2) {
									if (status_parent == 1 ) {
									img1=ImageIO.read(new File(System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/"+parts1[0]+"_"+parts1[1]+".png"));
									g.drawLine(width+img.getWidth(null)/2,height,originX_father+img1.getWidth(null)/2,originY_father+img1.getHeight(null));
									}
									if (status_parent == 2 ) {
									img1=ImageIO.read(new File(System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/"+parts1[0]+"_"+parts1[1]+".png"));
									g.drawLine(width+img.getWidth(null)/2,height,originX_mother+img1.getWidth(null)/2,originY_mother+img1.getHeight(null));
									}
								}
								if (status_parent == 3) {
									img1=ImageIO.read(new File(System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/"+parts1[0]+"_"+parts1[1]+".png"));
									g.drawLine(width+img.getWidth(null)/2,height,originX_mother+img1.getWidth(null)/2,originY_mother+img1.getHeight(null));
									g.drawLine(width+img.getWidth(null)/2,height,originX_father+img1.getWidth(null)/2,originY_father+img1.getHeight(null));
								}	   

						   } catch (IOException e) {

							   e.printStackTrace();
						   }
					   }
				   }
//				   modificationspainting = false;
			}
		   if (RectStatus==true) {
			   super.paintComponent(g);
			   drawPerfectRect(g, x-Informations.widthImg/2, y-Informations.heightImg/2, x+Informations.widthImg/2, y+Informations.heightImg/2);
		   }
		   PaintStatus = true;
		   
		}
	   public  void drawPerfectRect(Graphics g, int x, int y, int x2, int y2) {
	       int px = Math.min(x,x2);
	       int py = Math.min(y,y2);
	       int pw=Math.abs(x-x2);
	       int ph=Math.abs(y-y2);
	       g.drawRect(px, py, pw, ph);
	   }
	}

class PopUpDemo extends JPopupMenu {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuItem OpenFolder;
    JMenuItem OpenDiaporama;
    JMenuItem RefreshPersonFile;
    JMenuItem EditPerson;
    JMenuItem EditPersonLocation;
    public PopUpDemo(String name, String surname, String query){
    	OpenDiaporama = new JMenuItem("Ouvrir un diaporama");
        OpenDiaporama.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent ev) {
    	    	Pic_Player.main(surname,name);
               }
    	});
        add(OpenDiaporama);
        
    	OpenFolder=new JMenuItem("Ouvrir la liste de photos liée ");
    	OpenFolder.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent ev) {
					Pic_Browser.main(surname,name);
    	    }
    	});
    	
    	add(OpenFolder);
        EditPerson = new JMenuItem("Modifier les informations concernant cette personne");
        EditPerson.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent ev) {
				   Informations.main(query,name,surname);
               }
    	});
        add(EditPerson);
        
        RefreshPersonFile = new JMenuItem("Actualiser la liste de photos liées");
        RefreshPersonFile.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent ev) {
    	    	try {
    	    		if (JOptionPane.showConfirmDialog(null, 
    	      	            "Etes-vous sûr de vouloir actualiser la liste de photos liées? \n"
    	      	            + "Si oui, une fenêtre d'information vous confirmera la bonne fin de l'actualisation", "Demande de confirmation d'actualisation", 
    	      	            JOptionPane.YES_NO_OPTION,
    	      	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
						try {
							{
    	    			FileHandler.ExifContaining(Tree.ImageFolder, query,surname,name);
    	    			JOptionPane.showConfirmDialog(null, "La liste de photos liées est actualisée \n" + FileHandler.ErrorList,
        		    			"Fin actualisation photo liée",JOptionPane.DEFAULT_OPTION);	  
    					
    	      	        }
						} catch (HeadlessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    	    		
					
//				} catch (ImageProcessingException e) {
//					// TODO Auto-generated catch block
////					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					JOptionPane.showConfirmDialog(null, "L'actualisation des photos s'est terminée avec des erreurs, veuilez contacter le dévelopeur pour de plus amples informations",
    		    			"ERREUR",JOptionPane.DEFAULT_OPTION);	   
				}
               }
    	});
        add(RefreshPersonFile);
        
        
        Tree.state_click = true;
        
    }

    

}


