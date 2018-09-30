package Functionnalities;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import User_Interface.Tree;

public class PersonFileHandler {
	public static String fileName;
	static String sname;
	static String spath;
	static int i=0;
	static BufferedImage img = null ; 
	static String dirName;
	public static RandomAccessFile stream;
	public static FileChannel channel;
	public static int close_state;
    public static BufferedReader br = null;
    public static FileReader fr = null;
	
	public PersonFileHandler(String surname, String name)  {
		
		 try {
			 dirName= System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/";
			 fileName = dirName + surname + "_" + name + ".csv";
			 File file = new File(fileName);

			 if(file.exists())file.delete();
	             boolean fvar;
	             fvar = file.createNewFile();
		     if (fvar){
		          System.out.println("File has been created successfully");
		     }
		     else{
		          System.out.println("File already present at the specified location");
		     }
	    	} catch (IOException e) {
	    		System.out.println("Exception Occurred:");
		  }

		 
		 	try {
				BufferedWritter("Person Title: " + surname + " " + name +"\n");
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 
	}
	
	
	
//	static public boolean CheckIfFileExist(String filename) {
//		
//		Path path= Paths.get(System.getProperty("user.dir") + "/bin/tree/" + filename+"/");
//		if (Files.exists(path)) {
//		    return true;
//		}
//		return false;
//	}
	
	static public void Lockandwrite() 
  		  throws IOException {

  		    stream = new RandomAccessFile(fileName, "rw");
  		    channel = stream.getChannel();
  		 
  		    FileLock lock = null;
  		    try {
  		        lock = channel.tryLock();
  		    } catch (final OverlappingFileLockException e) {
  		        stream.close();
  		        channel.close();
  		    }
  		  

  		    lock.release();
  		 
  		    stream.close();
  		    channel.close();
  		}
	
	public static void BufferedWritter(String StringToWrite) throws IOException {				

					Lockandwrite();
					BufferedWriter writer = null;
					writer = new BufferedWriter(new FileWriter(fileName, true));
//					System.out.println(fileName);
					writer.append(StringToWrite);
					writer.close();

			}
	
	
	public static List<File> Readtxtfile(String surname, String name) {
		List<File> files = new ArrayList<File>();
		dirName= System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/";
			fileName = dirName + surname + "_" + name + ".csv";
		
		
	      String sCurrentLine;
	      br = null;
	      fr = null;

			try {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);

				sCurrentLine = br.readLine();
				while ((sCurrentLine = br.readLine()) != null) {
					files.add(new File(sCurrentLine)); 
				}

			} catch (IOException e1) {

				e1.printStackTrace();

			} finally {


				try {
					if (br != null)
							br.close();

					if (fr != null)
						fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						

				
			}
			return files;
			 
	}
	public static void Treedisplay() {
		i=2;

		
		while (Tree.TreeString[i] != "") {
			String[] parts = Tree.TreeString[i].split(",");
			Persondisplay(parts[0],parts[1],parts[2],parts[3]);
			i=i+1;
		}
		
	}
	public static void Persondisplay( String FirstName, String LastName, String Mother, String Father)   {
		try {
			img=ImageIO.read(new File(spath +"/" + FirstName + "_" + LastName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ImageIcon icon=new ImageIcon(img);

        JLabel lbl=new JLabel();
        lbl.setIcon(icon);

        lbl.setText(FirstName+ " " +LastName);
        Tree.TreeWindow.add(lbl);
        Tree.TreeWindow.setVisible(true);
        Tree.TreeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	static void Close_file() throws IOException{
		if (close_state == 11) br.close();
		if (close_state == 12) fr.close();
		if (close_state == 13) {
			fr.close();
			br.close();
		}
		
	}
	
}
