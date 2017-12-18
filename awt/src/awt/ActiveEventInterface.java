/* 
 * Name: Peerless
 * Date: 2017 12 17
 * Content: java standard library -- awt interfaces
 */

package awt;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;


/*
 * An interface for events that know how to dispatch themselves. By 
 * implementing this interface an event can be placed upon the event 
 * queue and its dispatch() method will be called when the event is
 * dispatched, using the EventDispatchThread.
 * 
 * dispath()
 * 
 * avoid deadlock --- when a thread is executing in a critical section,
 *   call other sychronized code --- an ActiveEvent can be created to
 *   run the second section of code at later time
 *   priority: first thread first( finished work and exited monitors),
 *   then second thread
 * 
 * avoid calling untrusted code from a critical thread
 * (e.g. call into user code from a system thread)
 *
 */

public class ActiveEventInterface {
	 public static void main(String[] args) {
		 example();
		 /* we can create many different dialogs at the same time:
		    example();
		  * if they are meant for reuse, then inclue:
		  * jdialog.setVisible(false) or jdialog.dispose()
		  */
	 }

  /**
   * The sample example given. 
   */
  public static void example() {
  	  //create a custom dialog and invoke many class methods
    final JDialog jdialog = new JDialog();
    //title of the dialog
    jdialog.setTitle("ActiveEventExample");
    //button to add actionListener
    JButton button = new JButton("Test Dispatch");
    jdialog.add(button);
    button.addActionListener(new ActionListener() {
    	//we should implement the inherited abstract method actionPerformed(AE e)
    		public void actionPerformed(ActionEvent e) {
    		  Long now = System.currentTimeMillis();
	      EventQueue theQueue = jdialog.getToolkit().getSystemEventQueue();
	      /*
	       * java EventQueue.invokeLater: 
	         http://blog.csdn.net/zengxiantao1994/article/details/52933249
	       */
	      System.out.println("At least 5000 millis");
	      while (System.currentTimeMillis() - now < 5000l) {
	        try {
	          // This is essentially the body of EventDispatchThread
	          AWTEvent event = theQueue.getNextEvent();
	          Object src = event.getSource();
	          if (event instanceof ActiveEvent) {
	            ((ActiveEvent) event).dispatch();
	            //System.out.println("dispatch active event!");
	          } else if (src instanceof Component) {
	            ((Component) src).dispatchEvent(event);
	            /*
	             * Menu components receive and process AWT events, just
	             * as components do, through the method processEvent. 
	             */
	          } else if (src instanceof MenuComponent) {
	            ((MenuComponent) src).dispatchEvent(event);
	          }
	        } catch (Exception ex) {
	          ex.printStackTrace();
	        }
    		  }
        System.out.println("end");

      }
    });
    /* we can also add components to jdialog by calling the add(comp a)
     * method of jdialog:
	    JTextField text = new JTextField("test");
	    jdialog.add(text);
    */
    
    
    /*
     * Causes this Window to be sized to fit the preferred size and layouts 
     * of its subcomponents. The resulting width and height of the window are 
     * automatically enlarged if either of dimensions is less than the minimum 
     * size as specified by the previous call to the setMinimumSize method.
     */
    jdialog.pack();
    jdialog.setVisible(true);
  }
}