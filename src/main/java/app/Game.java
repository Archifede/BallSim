package app;


import data.Ball;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Game extends JPanel {

    private Ball ball;

    public Game(Ball ball) {
    
    	this.ball = ball;
    	this.setBorder(BorderFactory.createLineBorder(Color.BLACK));   
    }

    @Override
    @SuppressWarnings("restriction") // for eclipse
    public void paint(Graphics g) {
        
    	super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        
        /* the method fillOval takes 2 arguments: x,y. However, x,y ARE NOT the center of the circle. They are actually the top-left
         * corner of the smallest square that can contain the oval. Since we are drawing a circle, need to transform the
         * the coordinates of the ball (which are the center of the ball) in coordinates that can be used to draw the circle. Simply, 
         * remove half the diameter from the real x coordinate and remove half the diameter from the real y coordinate.
         */
        int diameter = (int) this.ball.getDiameter();
		int x = (int) this.ball.getCoordinates().getX() - diameter/2;
        int y = (int) -this.ball.getCoordinates().getY() - diameter/2;
       
        /* use the cartesien origin instead of the computer science origin */
        g2d.translate(0,this.getHeight()-1);
        g2d.fillOval(x,y,diameter,diameter);
    }


}