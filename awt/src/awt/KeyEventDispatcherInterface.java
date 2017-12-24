package awt;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.HashSet;

public class KeyEventDispatcherInterface {
	/*
	This class implements the Set interface, backed by a hash table (actually a HashMap instance). 
	It makes no guarantees as to the iteration order of the set; in particular, it does not 
	guarantee that the order will remain constant over time. This class permits the null element.
	 */
  public HashSet<Component> comps = new HashSet<Component>();
  /*
  Hash table based implementation of the Map interface. This implementation provides all of the 
  optional map operations, and permits null values and the null key. (The HashMap class is roughly 
  equivalent to Hashtable, except that it is unsynchronized and permits nulls.) This class makes 
  no guarantees as to the order of the map; in particular, it does not guarantee that the order 
  will remain constant over time.
   */
  public HashMap<Integer, Character> swaps = new HashMap<Integer, Character>();

  public static void main(String[] args) {
    Runnable r = new Runnable() {
      public void run() {
      	 //GUI components for text
        Frame frame = new Frame();
        TextField tf = new TextField();
        frame.add(tf, BorderLayout.NORTH);
        TextField tf2 = new TextField();
        frame.add(tf2, BorderLayout.SOUTH);
        KeyEventDispatcherInterface med = new KeyEventDispatcherInterface();
        med.swaps.put(new Integer(KeyEvent.VK_F4), new Character(
            '\u00bd'));
        med.comps.add(tf);
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addKeyEventDispatcher(new KeyEventDispatcher() {
              public boolean dispatchKeyEvent(KeyEvent e) {
                // This example converts all typed keys to upper
                // case
                if (e.getID() == KeyEvent.KEY_TYPED) {
                  e.setKeyChar(Character.toUpperCase(e
                      .getKeyChar()));
                }
                // If the key should not be dispatched to the
                // focused component, set discardEvent to true
                boolean discardEvent = false;
                return discardEvent;
              }
            });
        frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        });
        frame.pack();
        frame.setVisible(true);
      }
    };
		/*
		EventQueue is a platform-independent class that queues events, both from the 
		underlying peer classes and from trusted application classes.
		
		Causes runnable to have its run method called in the dispatch thread of the system 
		EventQueue. This will happen after all pending events are processed.
		 */
    EventQueue.invokeLater(r);
  }
}