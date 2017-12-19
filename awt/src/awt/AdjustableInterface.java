/*
 * Name: Peerless
 * Date: 2017 12 18
 * Content: Adjustable interface from java.awt package
 */

package awt;

//Abstract Window Toolkit
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdjustableInterface extends Frame {
	/**
	 * Default Ctor
	 */
  public AdjustableInterface() {
  		//the title of frame, calling the super no argument ctor
    super("AdjustableInterfaceExample");
    /*
     * Specifies that horizontal/vertical scrollbar should be shown 
     * only when the size of the child exceeds the size of the scrollpane 
     * in the horizontal/vertical dimension.
     * Other arguments:
     	SCROLLBARS_AS_NEEDED
     	SCROLLBARS_NEVER
     	SCROLLBARS_ALWAYS
     */
    ScrollPane scroller = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
    //add the canvas on the scroller
    scroller.add(new DrawCanvas());
    Adjustable vadjust = scroller.getVAdjustable();
    Adjustable hadjust = scroller.getHAdjustable();
    /*
     * Returns the ScrollPaneAdjustable object which represents the state of
     * the vertical scrollbar. The declared return type of this method is 
     * Adjustable to maintain backward compatibility.
     * Can be ignored.
     */
    hadjust.setUnitIncrement(10);
    vadjust.setUnitIncrement(10);
    //the width and height of canvas are decided by scroller
    scroller.setSize(100,100);
    //same as this.add(scroller, BorderLayout.NORTH);
    add("North", scroller);
    TextArea text = new TextArea("this is the test of layout manager");
    this.add(text, BorderLayout.CENTER);
    /*
     * Causes this Window to be sized to fit the preferred size and layouts of its 
     * subcomponents. The resulting width and height of the window are automatically 
     * enlarged if either of dimensions is less than the minimum size as specified
     * by the previous call to the setMinimumSize method.
     */
    pack();
    /*
     * An abstract adapter class for receiving window events. The methods in this
     * class are empty. This class exists as convenience for creating listener objects.
     * This abstract class defines null methods for them all, so you can only have 
     * to define methods for events you care about.)
     */
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
      	 //System.out.println("exit window");
        System.exit(-1);
      }
    });
  }

  // No more handleEvent method needed to implement scrolling!
  public static void main(String args[]) {
    	AdjustableInterface test = new AdjustableInterface();
    test.setVisible(true);
  }
}

class DrawCanvas extends Component {
  public Dimension getPreferredSize() {
  	  //Constructs a Dimension and initializes it to the specified width and specified height.
    return new Dimension(200, 200);
  }

  public void paint(Graphics g) {
    // Note: For most efficient repainting, we should check the
    // clip rectangle in the Graphics object to determine the
    // damaged region and only paint that; we don't do that here
    // for simplicity in this example
    /*
     * Gets the bounds of this component in the form of a Rectangle 
     * object. The bounds specify this component's width, height,
     * and location relative to its parent.
     */
    Rectangle r = getBounds();
    g.setColor(Color.black);
    g.fillRect(0, 0, r.width, r.height);
    g.setColor(Color.yellow);
    g.drawLine(0, 0, r.width, r.height);
    g.setColor(Color.white);
    g.drawLine(0, r.height, r.width, 0);
  }
}

