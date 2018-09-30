package User_Interface;

final class TreeLaucher {
		  public static void main(String[] args) {
		    new Thread(new Runnable() {
		      public void run() {
		        Tree.main();
		        
		      }
		    }).start();

		    try {
		      Thread.sleep(1000);
		    } catch (InterruptedException ex) {
		      //
		    }
		    Tree.InitStatus = true;
		  }
		
}
