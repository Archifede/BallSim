package gui;

import javax.swing.*;
import java.awt.*;

/**
 * This is the frame of the Ball bouncing simulation.
 *
 * @author Only Brad
 */
public class GameFrame extends JFrame {

    private static final long serialVersionUID = -7927156597267134363L;
    private static final double multiplier = 0.75;

    public GameFrame() {

        this.setTitle("Physic Simulator");
        setScreenSizeFromMonitor();
        setBorderOnPrimaryPanelInsideTheFrame();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private void setScreenSizeFromMonitor() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        this.setSize((int) (width * multiplier), (int) (height * multiplier));
    }

    private void setBorderOnPrimaryPanelInsideTheFrame() {
        JPanel pane = (JPanel) this.getContentPane();
        pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

}
