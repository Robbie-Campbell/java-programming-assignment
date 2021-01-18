package src.GUIs;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GUISuper {

    private static final Insets insets = new Insets(0, 0, 0, 0);

    // A shorthand function that positions components with gridbaglayout
    public static void addComponent(Container container, Component component, int gridx, int gridy,
      int gridwidth, int gridheight, int anchor, int fill) 
    {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
            anchor, fill, insets, 0, 0);
            container.add(component, gbc);
    }

    // Override the function to include padding
    public static void addComponent(Container container, Component component, int gridx, int gridy,
      int gridwidth, int gridheight, int anchor, int fill, int insetSize) 
    {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
            anchor, fill, new Insets(insetSize, insetSize, insetSize, insetSize), 0, 0);
            container.add(component, gbc);
    }

    // Method to covert the size of an image
    public static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
      BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
      Graphics2D g = resizedImage.createGraphics();
      g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
      g.dispose();
  
      return resizedImage;
  }
}
