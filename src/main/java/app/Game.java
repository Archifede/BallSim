

package app;


import data.Ball;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Game extends JPanel {


    Ball ball;

    public Game(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.fillRect(0,1000,2000,50);

        g2d.translate(0, 1000);
        g2d.fillOval(((int) ball.getCoordinates().getX()), -((int) ball.getCoordinates().getY()), 30, 30);





    }


}