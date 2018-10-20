package client.loader.gui;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

/**
 * BonBom
 * Date: 4.2.2018
 * Time: 16.25
 */

public class BotFrame extends JFrame {
    Applet applet;
    JPanel panel;

    public BotFrame(Applet applet) {
        super("SnekBot");
        this.applet = applet;
    }


    public void init() {
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("./snake.png").getImage());

        applet.setSize(new Dimension(800, 600));

        panel = new JPanel();
        panel.add(applet);
        this.add(panel);

        this.pack();
        this.setVisible(true);
    }

}
