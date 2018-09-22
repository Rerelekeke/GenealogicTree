package User_Interface;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
public class JLabelsInJJScrollPane extends JFrame {
	
	 
	    public JLabelsInJJScrollPane() {
	        JPanel jPanel = new JPanel();
	        JPanel jPanel2 = new JPanel();
	        for (int i = 0; i < 50; i++) {
	            JLabel jLabel = new JLabel("Label" + i);
	            jLabel.setBounds(i, i*4, 200, 20);
	            jLabel.addMouseListener(new MouseAdapter() {
					   public void mouseClicked(MouseEvent e) {
						   	System.out.println("test");
						   	
					   }
				 });
	            jPanel2.add(jLabel);
	            jPanel.add(Box.createRigidArea(new Dimension(1, 5)));
	            
	        }
	        jPanel.add(jPanel2);
	        jPanel2.setLayout(null);
	        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
	        jPanel.setLayout(boxLayout);
	        JScrollPane jScrollPane = new JScrollPane(jPanel2);
	        add(jScrollPane);
	    }
	 
	    public static void main(String[] args) {
	        JLabelsInJJScrollPane jLabelsInJJScrollPane = new JLabelsInJJScrollPane();
	        jLabelsInJJScrollPane.setSize(150, 150);
	        jLabelsInJJScrollPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        jLabelsInJJScrollPane.setVisible(true);
	    }
	}

