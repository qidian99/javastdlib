package awt;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CompositeContextInterface extends JPanel implements Composite {

  int alphaCompositeRules[] = { AlphaComposite.CLEAR, AlphaComposite.DST,
      AlphaComposite.DST_ATOP, AlphaComposite.DST_IN,
      AlphaComposite.DST_OUT, AlphaComposite.DST_OVER,
      AlphaComposite.DST_ATOP, AlphaComposite.DST_OUT,
      AlphaComposite.SRC, AlphaComposite.SRC_ATOP, AlphaComposite.SRC_IN,
      AlphaComposite.SRC_OUT, AlphaComposite.SRC_OVER };

  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    for (int x = 20, y = 20, i = 0; i < alphaCompositeRules.length; x += 70, i++) {
      Composite alphaComposite = AlphaComposite.getInstance(
          alphaCompositeRules[i], 0.8f);
      // the size of buffered image can be set to be 45,45
      BufferedImage bufferedImage = new BufferedImage(70, 70,
          BufferedImage.TYPE_INT_ARGB);
      Graphics2D gbi = bufferedImage.createGraphics();
      gbi.setPaint(Color.red);
      gbi.fillRect(0, 0, 40, 40);
      gbi.setComposite(alphaComposite);
      gbi.setPaint(Color.black);	
      gbi.fillRect(5, 5, 40, 40);
      g2d.drawImage(bufferedImage, x, y, null);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("CompositeExample");
    frame.add(new CompositeContextInterface());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(910, 110);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  @Override
  /**
   * Creates a context containing state that is used to perform the compositing 
   * operation. In a multi-threaded environment, several contexts can exist 
   * simultaneously for a single Composite object.
   */
  public CompositeContext createContext(ColorModel srcColorModel,
      ColorModel dstColorModel, RenderingHints hints) {
    // TODO Auto-generated method stub
    return null;
  }
}