package Functionnalities;


public class Debug_class {
	public static boolean Debug;

	public static void Debug () {
		if (Debug == true) {
				javax.swing.JOptionPane.showMessageDialog(null,getMethodeFullName());
				javax.swing.JOptionPane.showMessageDialog(null,Debug);
		}
	}
	
	public static void Msgbox (String Msgbox) {
				javax.swing.JOptionPane.showMessageDialog(null,Msgbox);
	}
	

	  public static String getMethodeFullName()
	  {
	          Throwable t = new Throwable();
	          t.fillInStackTrace();
	          StackTraceElement e = t.getStackTrace()[2];
	          String className = e.getClassName();
	          String functionName = e.getMethodName();
	          return className + "." + functionName;
	  }
	  /**
	   *
	   * @return Renvoie le nom de la fonction que l'on est en trai d'executer
	   */
	  public static String getMethodeName()
	  {
	          Throwable t = new Throwable();
	          t.fillInStackTrace();
	          StackTraceElement e = t.getStackTrace()[1];
	          String functionName = e.getMethodName();
	          return functionName ;
	  }
}
