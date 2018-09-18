//package User_Interface;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
// 
//
//
// 
//final class PaintSwing extends JPanel {
// 
//	public static void main(String... args) {
//	    JFrame frame = new JFrame("Swing form");
//	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	 
//	    final JScrollPane pane = new JScrollPane();
//	    frame.add(pane);
//	 
//	    final PaintSwing canvas = new PaintSwing();
//	    pane.setViewportView(canvas);
//	    frame.setVisible(true);
//	
//	    SwingUtilities.invokeLater(new Runnable() {
//	      public void run() {
//	        canvas.setPreferredSize(new Dimension(400, 400));
//	        pane.revalidate();
//	      }
//	    });
//	}
//}
// 
//final class test {
//  public static void main(final String... args) {
//    new Thread(new Runnable() {
//      public void run() {
//        PaintSwing.main(args);
//      }
//    }).start();
// 
//  }
//}