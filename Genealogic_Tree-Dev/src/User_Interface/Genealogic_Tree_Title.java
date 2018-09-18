package User_Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import Functionnalities.Debug_class;
import Functionnalities.FileHandler;
import Functionnalities.TreeFileHandler;


import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Genealogic_Tree_Title {
	static JFrame f = new JFrame("A JFrame");
	private static JTextField textFieldTreeTitle;
	static TreeFileHandler Treeclass;
	private static JTextField textFieldPath;
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main() {
		 FileHandler.Windows_theme();
	      f.setTitle("Cr\u00E9ation de l'abre g\u00E9n\u00E9alogique");
	      f.setSize(376, 291);
	      f.setLocation(0,0);
	      f.getContentPane().setLayout(null);
	      
	      textFieldTreeTitle = new JTextField();
	      textFieldTreeTitle.setBounds(195, 33, 148, 19);
	      f.getContentPane().add(textFieldTreeTitle);
	      textFieldTreeTitle.setColumns(10);
	      
	      JLabel lblTreetitle = new JLabel("Titre de l'arbre g\u00E9alogique:");
	      lblTreetitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
	      lblTreetitle.setBounds(37, 35, 186, 13);
	      f.getContentPane().add(lblTreetitle);
	      
	      JButton btnEnregistrer = new JButton("Enregistrer");
	      btnEnregistrer.setBounds(97, 211, 85, 21);
	      btnEnregistrer.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if (TreeFileHandler.CheckIfFileExist(textFieldTreeTitle.getText()) == true){
		    			JOptionPane.showConfirmDialog(null, "Un arbre généalogique possédant le même nom existe déjà. \n Veuillez changer de nom.",
				    			"Erreur, l'arbre genéalogique existe déjà!!",JOptionPane.DEFAULT_OPTION);
		    		}
		    		else {
		
			    		Path path= Paths.get(textFieldPath.getText()+"/");
	
			    		if (Files.exists(path)){
			    			Tree.Title =textFieldTreeTitle.getText();
			    			Tree.BackupDirectory = textFieldPath.getText()+"/";
			    			Treeclass = new TreeFileHandler();
				    		f.setVisible(false);
			    		}
			    		else {
			    			JOptionPane.showConfirmDialog(null, "Merci de renseigner un dossier pour enregistrer le backup",
					    			"Erreur, l'arbre genéalogique existe déjà!!",JOptionPane.DEFAULT_OPTION);
			    		}
			    		
		    		}
		    	}
		    });
	      f.getContentPane().add(btnEnregistrer);
	      
	      JButton btnAnnuler = new JButton("Annuler");
	      btnAnnuler.setBounds(191, 211, 85, 21);
	      btnAnnuler.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		f.dispose();
		    		f.setVisible(false);
		    	}
		    });
	      f.getContentPane().add(btnAnnuler);
	      
	      JButton btnParcourir = new JButton("Parcourir");
	      btnParcourir.setBounds(246, 154, 85, 21);
	      f.getContentPane().add(btnParcourir);
	      btnParcourir.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
//		    		FileHandler.OpenFile(f, null, null, null);
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
	      
	      
	      JLabel lblLabreGnalogiqueEst = new JLabel("<html>L'abre g\u00E9n\u00E9alogique est enregistrer dans le dossier bin/tree du fichier executable. <br> Cependant, pour des raisons de securité, il vous est possible de renseigner un dosier contenant des backups</html>");
	      lblLabreGnalogiqueEst.setBounds(25, 74, 282, 70);
	      f.getContentPane().add(lblLabreGnalogiqueEst);
	      
	      textFieldPath = new JTextField();
	      textFieldPath.setColumns(10);
	      textFieldPath.setBounds(20, 155, 203, 19);
	      f.getContentPane().add(textFieldPath);
	      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	      Debug_class.Debug();
	      f.setVisible(true);
	}
}
