package src.GUIs;

import java.awt.*;

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
      int gridwidth, int gridheight, int anchor, int fill, int padx, int pady) 
    {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
            anchor, fill, insets, padx, pady);
            container.add(component, gbc);
    }
}
