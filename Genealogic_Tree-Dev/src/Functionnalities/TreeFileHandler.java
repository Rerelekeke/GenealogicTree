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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import User_Interface.Tree;

public class TreeFileHandler {

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
	
	public TreeFileHandler()  {
		
		 try {
			 dirName= System.getProperty("user.dir") + "/bin/tree/" + Tree.Title +"/";
			 new File(dirName).mkdirs();
			 fileName = dirName + Tree.Title + ".tree";
			 File file = new File(fileName);


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
				BufferedWritter("Tree Title:" + Tree.Title + ";Backup Directory:" + Tree.BackupDirectory );
				BufferedWritter(System.getProperty("line.separator") + "Name,Surname,Mother,Father,Query,x,y");
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 
	}
	
	
	
	static public boolean CheckIfFileExist(String filename) {
		
		Path path= Paths.get(System.getProperty("user.dir") + "/bin/tree/" + filename+"/");
		if (Files.exists(path)) {
		    return true;
		}
		return false;
	}
	static public String CheckifExistingPerson(String name, String surname) {
		Readtxtfile(false);
		for(int j = 0; j < Tree.TreeString.length && Tree.TreeString[j] != ""; j++) {  
			   if (j>1) {
				   String string = Tree.TreeString[j];
				   System.out.println(string);
					   String parts[] = string.split(",");
					   if(parts[0].equals(name) && parts[1].equals(surname)) {
						   String[] results_temp = parts[4].split(";");
						   String result ="";
						   for (String result_temp :results_temp) {
							   result += "*" + result_temp +"*;" ;
						   }
						   return result;
				   }
			   }
        }
		return "error -404";
	}
	
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
					writer.append(StringToWrite);
					writer.close();

			}
	public static void BufferedModifyer(String LineToModify, String NewLine) throws IOException {				
        RandomAccessFile file = new RandomAccessFile(fileName, "rw");
        String Line;
        String task="";

        i=0;
        while ((Line = file.readLine()) != null) {
        	i++;
            if (Line.equals(LineToModify)) {
                continue;
            }
            if (i>1)task+="\n";
            task+=Line;
        }
        task+=NewLine;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(task);
        file.close();
        writer.close();
}
	
	public static void BufferedModifyerFirst(String LineToModify, String NewLine) throws IOException {				
        RandomAccessFile file = new RandomAccessFile(fileName, "rw");
        String Line;
        String task="";

        i=0;
        while ((Line = file.readLine()) != null) {
        	i++;
            if (Line.equals(LineToModify)) {
                continue;
            }
            if (i>1)task+="\n";
            task+=Line;
        }
        task=NewLine+task;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(task);
        file.close();
        writer.close();
}
	
	public static boolean Readtxtfile(boolean open_state) {
		if (open_state == true){
		      sname= FileHandler.OpenFile(null,"*.tree",System.getProperty("user.dir") + "\\bin\\tree\\",null);
		      if (sname == "-1") return false;
		      
		      Tree.Title = new File(sname).getName().replaceAll(".tree", "");
		      spath = new File(sname).getParent();
		      dirName = spath +"\\";
		      fileName = spath +"\\" + Tree.Title + ".tree";
		}
		else {
			spath = dirName ;
			sname = dirName  + Tree.Title + ".tree";
		}
		
	      String sCurrentLine;
	      br = null;
	      fr = null;

			try {
				fr = new FileReader(sname);
				br = new BufferedReader(fr);

				while (i<499) {
					Tree.TreeString[i] = "";
					i=i+1;
				}

				i=0;
				
				while ((sCurrentLine = br.readLine()) != null) {
					Tree.TreeString[i] = sCurrentLine; 
					i=i+1;
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
			return true;
			 
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
