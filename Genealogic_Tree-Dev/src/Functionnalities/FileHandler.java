package Functionnalities;


import java.awt.FileDialog;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.adobe.xmp.*;

//import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

public class FileHandler {
	public static String ErrorList;
	private static String file_name;

	public static String OpenFile(JFrame f,String filter,String directory,String extensions) {
		
	      FileDialog fd = new FileDialog(f, "Test", FileDialog.LOAD);
	      file_name =null;
	      if (directory!=null)fd.setDirectory(directory);
	      if (filter!=null )fd.setFile(filter);
	      if (extensions!=null ) {
	    	  fd.setFile(extensions);
	      }
	      fd.setVisible(true);
	      String name = fd.getDirectory() + fd.getFile();
	      file_name=name;
	      if (fd.getFile() == null) {file_name ="-1";}
        return file_name;
	}
	
	public static void ExifContaining(String dir, String filter, String surname, String name) throws IOException {
//		List<File> results = new ArrayList<>();
		String[] filters = filter.split(";");
		ErrorList="";
		String error="Cependant, il fût impossible de lire les fichiers suivants:";
		
		List<File> filesInFolder = null;
		try {
			filesInFolder = Files.walk(Paths.get(dir))
			        .filter(p -> p.getFileName().toString().endsWith(".jpg") || p.getFileName().toString().endsWith(".jpeg")
			        		  || p.getFileName().toString().endsWith(".png") || p.getFileName().toString().endsWith(".JPG") 
							  || p.getFileName().toString().endsWith(".PNG") || p.getFileName().toString().endsWith(".J"))
			        .map(Path::toFile)
			        .collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur");
			e.printStackTrace();
		}
		
		new PersonFileHandler(surname,name);
		boolean statuswritten = false;
		for(File file: filesInFolder) {
			
			if (file.getName().equals("2010-08-02-Z-Dutch_county-DSCF9101.JPG")) {
				System.out.println("c'est la merde");
			}
//			System.out.println(file.getName())à;
			statuswritten = false;
//			if(file.getName().contains("2014-12-25-Noël-DSCN0643")) {
//				System.out.println("test");
//			}
			Metadata metadata;
			try {
				metadata = ImageMetadataReader.readMetadata(file);
			} catch (ImageProcessingException | IOException e) {
				error=error+ "\n" + file.getAbsolutePath();
				ErrorList = error;
//				e.printStackTrace();
				continue;
			}
			for (String word : filters) {
				if (file.getName().contains(word)) {
		    		PersonFileHandler.BufferedWritter(file.getAbsolutePath()+"\n");
		    		statuswritten = true;
		    		break;
		    	}
	    	}
			boolean breaktag = false;
			if (statuswritten == true) continue;
				for (Directory directory : metadata.getDirectories()) {

				    for (Tag tag : directory.getTags()) {
//				    	error=tag.getTagName();
				    	if (tag.getTagName().contains("Keywords")){
					    	for (String word : filters) {
						    	 if( tag.getDescription().contains(word)) {
						    		PersonFileHandler.BufferedWritter(file.getAbsolutePath()+"\n");
						    		breaktag = true;
						    		break;
						    	}
					    	}
				    	}
				    	
				    	if (breaktag == true) break;
//				    	error="";
				    }
				}
				
			
			}
		
	}

	
	
	public static File[] MultipleOpenFile(JFrame f,String filter) {
		
	      FileDialog fd = new FileDialog(f, "Test", FileDialog.LOAD);
	      if (filter!=null) {
	    	  fd.setFile(filter);
	    	  fd.setMultipleMode(true);
	      }
	      fd.setVisible(true);
	      File[] name = fd.getFiles();

      return name;
	}
	
	 public static void Windows_theme() {
		 try {
		     UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		 } 
		 catch (Exception e) { }

		 }

	public static BufferedImage resize(BufferedImage img, int newH) { 
		int newW;
		if (newH < img.getHeight()) {
			newW = img.getWidth()/ (img.getHeight()/newH);
		}
		else {
			newW = img.getWidth()* (newH/img.getHeight());
		}
	    
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
	    
	    
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    
	    g2d.dispose();

	    return dimg;
	}

	public static  BufferedImage cropImage(BufferedImage src, int x, int y , int x1, int y1) {
	      BufferedImage dest = src.getSubimage(0, 0, x1-x, y1-y);
	      return dest; 
	   }

	
	
}



