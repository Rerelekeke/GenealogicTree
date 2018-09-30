package User_Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;
import Functionnalities.Debug_class;
import Functionnalities.FileHandler;
import Functionnalities.TreeFileHandler;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;



public class ModificationPathImage {



		static String OldPathImage;
		static JFrame f = new JFrame("A JFrame");
		static TreeFileHandler Treeclass;
		private static JTextField textFieldPath;
		/**
		 * @wbp.parser.entryPoint
		 */
		
		public ModificationPathImage() {
			main();
		}
		public void main() {
			 FileHandler.Windows_theme();
		      f.setTitle("Modification du Dossier d'images");
		      f.setSize(376, 182);
		      f.setLocation(0,0);
		      f.getContentPane().setLayout(null);
		      
		      
		      JButton btnEnregistrer = new JButton("Modifier");
		      btnEnregistrer.setBounds(100, 95, 85, 21);
		      btnEnregistrer.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent arg0) {
			 
			
				    		Path path= Paths.get(textFieldPath.getText()+"/");
		
				    		if (Files.exists(path)){
				    			TreeFileHandler.Readtxtfile(false);
				    			OldPathImage = Tree.ImageFolder;
				    			Tree.ImageFolder = textFieldPath.getText()+"/";
				    			try {
									TreeFileHandler.BufferedReplacement(2,OldPathImage,Tree.ImageFolder);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				    			
					    		f.setVisible(false);
				    		}
				    		else {
				    			JOptionPane.showConfirmDialog(null, "Merci de renseigner un dossier pour enregistrer le backup",
						    			"Erreur, l'arbre genéalogique existe déjà!!",JOptionPane.DEFAULT_OPTION);
				    		}
				    		
			    		
			    	}
			    });
		      f.getContentPane().add(btnEnregistrer);
		      
		      JButton btnAnnuler = new JButton("Annuler");
		      btnAnnuler.setBounds(194, 95, 85, 21);
		      btnAnnuler.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent arg0) {
			    		f.dispose();
			    		f.setVisible(false);
			    	}
			    });
		      f.getContentPane().add(btnAnnuler);
		      
		      JButton btnParcourir = new JButton("Parcourir");
		      btnParcourir.setBounds(248, 27, 85, 21);
		      f.getContentPane().add(btnParcourir);
		      btnParcourir.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent arg0) {
//			    		FileHandler.OpenFile(f, null, null, null);
			    		 JFileChooser chooser = new JFileChooser();
			    		    chooser.setCurrentDirectory(new java.io.File("."));
			    		    chooser.setDialogTitle("choosertitle");
			    		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    		    chooser.setAcceptAllFileFilterUsed(false);
			    		    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			    		    	textFieldPath.setText(chooser.getSelectedFile().getAbsolutePath());
			    		    } else {
			    		      System.out.println("No Selection ");
			    		    }
			    		  }
		    		
			    });
		      
		      textFieldPath = new JTextField();
		      textFieldPath.setColumns(10);
		      textFieldPath.setBounds(22, 28, 203, 19);
		      f.getContentPane().add(textFieldPath);
		      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		      Debug_class.Debug();
		      f.setVisible(true);
		}
	}


