package gui;


import data.Ball;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    private Ball ball;
    private BallSimGraphics ballSimGraphics;

    public GamePanel(Ball ball) {

        this.ball = ball;
        this.ballSimGraphics = new BallSimGraphics();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    @SuppressWarnings("restriction") // for eclipse
    public void paint(Graphics g) {
        super.paint(g);
        ballSimGraphics.g2d(g);
        ballSimGraphics.useCartesianOrigin(this.getHeight());
        ballSimGraphics.fillAjustedOval(ball);

    }


    public Ball getBall() {
        return ball;
    }

    private class BallSimGraphics {
        private Graphics2D g2d;

        /**
         * Use the cartesien origin instead of the computer science origin
         *
         * @param height of containing panel
         */
        private void useCartesianOrigin(int height) {
            g2d.translate(0, height - 1);
        }

        /**
         * The method fillOval takes 2 arguments: x,y. However, x,y ARE NOT the center of the circle. They are actually the top-left
         * corner of the smallest square that can contain the oval. Since we are drawing a circle, need to transform the
         * the coordinates of the ball (which are the center of the ball) in coordinates that can be used to draw the circle. Simply,
         * remove half the diameter from the real x coordinate and remove half the diameter from the real y coordinate.
         */
        private void fillAjustedOval(Ball ball) {
            int diameter = (int) ball.getDiameter();
            int ajustedX = (int) ball.getCoordinates().getX() - diameter / 2;
            int ajustedY = -1 * (int) ball.getCoordinates().getY() - diameter / 2;
            this.g2d.fillOval(ajustedX, ajustedY, diameter, diameter);
        }


        private void g2d(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(
                    RenderingHints.KEY_FRACTIONALMETRICS,
                    RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            this.g2d = g2d;
        }
    }


}