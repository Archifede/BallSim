package app;


import data.Ball;
import javafx.geometry.Point2D;
import logic.Physics;

/**
 * @author Victor
 * @since 09 mai 00h43
 */
public class ConsoleApp {
    public void startSim() {
        Ball ball;
        int width, height;
        width = 1920;
        height=1080;
        double secondPerTick = 1;
        ball = new Ball(Point2D.ZERO, 100, 40,30);
        ball.launch(100000, 60);
        for (int tick = 0; tick <= 10; tick++) {
            System.out.println("At " + tick + "s [ " + ball +"]");
            Physics.apply(ball, secondPerTick, 1920, 1080);


        }

    }
}
