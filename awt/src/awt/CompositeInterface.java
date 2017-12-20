package awt;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CompositeInterface extends JPanel {
/*
 * The AlphaComposite class implements basic alpha compositing rules for 
 * combining source and destination colors to achieve blending and transparency 
 * effects with graphics and images. The specific rules implemented by this class 
 * are the basic set of 12 rules described in T. Porter and T. Duff, "Compositing 
 * Digital Images", SIGGRAPH 84, 253-259. The rest of this documentation assumes 
 * some familiarity with the definitions and concepts outlined in that paper.
 */
  int alphaCompositeRules[] = { AlphaComposite.CLEAR, AlphaComposite.DST,
      AlphaComposite.DST_ATOP, AlphaComposite.DST_IN,
      AlphaComposite.DST_OUT, AlphaComposite.DST_OVER,
      AlphaComposite.DST_ATOP, AlphaComposite.DST_OUT,
      AlphaComposite.SRC, AlphaComposite.SRC_ATOP, AlphaComposite.SRC_IN,
      AlphaComposite.SRC_OUT, AlphaComposite.SRC_OVER };

  public void paint(Graphics g) {
  	/*
  	 * This Graphics2D class extends the Graphics class to provide more sophisticated 
  	 * control over geometry, coordinate transformations, color management, and text 
  	 * layout. This is the fundamental class for rendering 2-dimensional shapes, text 
  	 * and images on the Java(tm) platform.
  	 */
    Graphics2D g2d = (Graphics2D) g;

    for (int x = 20, y = 20, i = 0; i < alphaCompositeRules.length; x += 70, i++) {
      /*
       * Creates an AlphaComposite object with the specified rule and the constant alpha 
       * to multiply with the alpha of the source. The source is multiplied with the 
       * specified alpha before being composited with the destination.
       */
      Composite alphaComposite = AlphaComposite.getInstance(
          alphaCompositeRules[i], 0.8f);
      BufferedImage bufferedImage = new BufferedImage(70, 70,
          BufferedImage.TYPE_INT_ARGB);
      Graphics2D gbi = bufferedImage.createGraphics();
      gbi.setPaint(Color.red);
      gbi.fillRect(0, 0, 40, 40);
      //set composite for the second grid
      gbi.setComposite(alphaComposite);
      gbi.setPaint(Color.black);
      gbi.fillRect(5, 5, 40, 40);
      //null --- observer object to be notified as more of the image is converted.
      g2d.drawImage(bufferedImage, x, y, null);
    }
  }

  public static void main(String[] args) {
  	  //set the title, frame size, etc
    JFrame frame = new JFrame("CompositeExample");
    frame.add(new CompositeInterface());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(910, 110);
    //Sets the location of the window relative to the specified component according to the following scenarios.
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
