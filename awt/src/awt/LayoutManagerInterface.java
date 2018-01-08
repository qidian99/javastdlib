package awt;

/*
* CustomLayoutDemo.java requires one other file:
*   DiagonalLayout.java
*/

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;

public class LayoutManagerInterface {
    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new DiagonalLayout());

        pane.add(new JButton("Button 1"));
        pane.add(new JButton("Button 2"));
        pane.add(new JButton("Button 3"));
        pane.add(new JButton("Button 4"));
        pane.add(new JButton("Button 5"));
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread. --- Why?
     */
    
    /*
    Causes doRun.run() to be executed asynchronously on the AWT event dispatching thread. 
    This will happen after all pending AWT events have been processed. This method should 
    be used when an application thread needs to update the GUI. In the following example 
    the invokeLater call queues the Runnable object doHelloWorld on the event dispatching 
    thread and then prints a message. 
			 Runnable doHelloWorld = new Runnable() {
			     public void run() {
			         System.out.println("Hello World on " + Thread.currentThread());
			     }
			 };
			
			 SwingUtilities.invokeLater(doHelloWorld);
			 System.out.println("This might well be displayed before the other message.");
 
		If invokeLater is called from the event dispatching thread -- for example, from a JButton's 
		ActionListener -- the doRun.run() will still be deferred until all pending events have been 
		processed. Note that if the doRun.run() throws an uncaught exception the event dispatching 
		thread will unwind (not the current thread). 
		Additional documentation and examples for this method can be found in Concurrency in Swing. 

		As of 1.3 this method is just a cover for java.awt.EventQueue.invokeLater(). 
	
		Unlike the rest of Swing, this method can be invoked from any thread.

     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CustomLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        
        Runnable doHelloWorld = new Runnable() {
			     public void run() {
			         System.out.println("Hello World on " + Thread.currentThread());
			     }
			 };
			
			 javax.swing.SwingUtilities.invokeLater(doHelloWorld);
			 System.out.println("This might well be displayed before the other message.");
    }
}

class DiagonalLayout implements LayoutManager {
    private int vgap;
    private int minWidth = 0, minHeight = 0;
    private int preferredWidth = 0, preferredHeight = 0;
    private boolean sizeUnknown = true;

    public DiagonalLayout() {
        this(5);
    }

    public DiagonalLayout(int v) {
        vgap = v;
    }

    /* Required by LayoutManager. */
    public void addLayoutComponent(String name, Component comp) {
    }

    /* Required by LayoutManager. */
    public void removeLayoutComponent(Component comp) {
    }

    private void setSizes(Container parent) {
        int nComps = parent.getComponentCount();
        Dimension d = null;

        //Reset preferred/minimum width and height.
        preferredWidth = 0;
        preferredHeight = 0;
        minWidth = 0;
        minHeight = 0;

        for (int i = 0; i < nComps; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                d = c.getPreferredSize();

                if (i > 0) {
                    preferredWidth += d.width/2;
                    preferredHeight += vgap;
                } else {
                    preferredWidth = d.width;
                }
                preferredHeight += d.height;

                minWidth = Math.max(c.getMinimumSize().width,
                                    minWidth);
                minHeight = preferredHeight;
            }
        }
    }


    /* Required by LayoutManager. */
    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        setSizes(parent);

        //Always add the container's insets!
        Insets insets = parent.getInsets();
        dim.width = preferredWidth
                    + insets.left + insets.right;
        dim.height = preferredHeight
                     + insets.top + insets.bottom;

        sizeUnknown = false;

        return dim;
    }

    /* Required by LayoutManager. */
    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        //Always add the container's insets!
        Insets insets = parent.getInsets();
        dim.width = minWidth
                    + insets.left + insets.right;
        dim.height = minHeight
                     + insets.top + insets.bottom;

        sizeUnknown = false;

        return dim;
    }

    /* Required by LayoutManager. */
    /*
     * This is called when the panel is first displayed,
     * and every time its size changes.
     * Note: You CAN'T assume preferredLayoutSize or
     * minimumLayoutSize will be called -- in the case
     * of applets, at least, they probably won't be.
     */
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int maxWidth = parent.getWidth()
                       - (insets.left + insets.right);
        int maxHeight = parent.getHeight()
                        - (insets.top + insets.bottom);
        int nComps = parent.getComponentCount();
        int previousWidth = 0, previousHeight = 0;
        int x = 0, y = insets.top;
        int xFudge = 0, yFudge = 0;
        boolean oneColumn = false;

        // Go through the components' sizes, if neither
        // preferredLayoutSize nor minimumLayoutSize has
        // been called.
        if (sizeUnknown) {
            setSizes(parent);
        }

        if (maxWidth <= minWidth) {
            oneColumn = true;
        }

        if (maxWidth != preferredWidth) {
            xFudge = (maxWidth - preferredWidth)/(nComps - 1);
        }

        if (maxHeight > preferredHeight) {
            yFudge = (maxHeight - preferredHeight)/(nComps - 1);
        }

        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                Dimension d = c.getPreferredSize();

                 // increase x and y, if appropriate
                if (i > 0) {
                    if (!oneColumn) {
                        x += previousWidth/2 + xFudge;
                    }
                    y += previousHeight + vgap + yFudge;
                }

                // If x is too large,
                if ((!oneColumn) &&
                    (x + d.width) >
                    (parent.getWidth() - insets.right)) {
                    // reduce x to a reasonable number.
                    x = parent.getWidth()
                        - insets.bottom - d.width;
                }

                // If y is too large,
                if ((y + d.height)
                    > (parent.getHeight() - insets.bottom)) {
                    // do nothing.
                    // Another choice would be to do what we do to x.
                }

                // Set the component's size and position.
                c.setBounds(x, y, d.width, d.height);

                previousWidth = d.width;
                previousHeight = d.height;
            }
        }
    }

    public String toString() {
        String str = "";
        return getClass().getName() + "[vgap=" + vgap + str + "]";
    }
}