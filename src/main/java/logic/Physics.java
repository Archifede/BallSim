package logic;

import data.Ball;
import javafx.geometry.Point2D;

/**
 * @author Victor
 * @since 08 mai 17h40
 */
public class Physics {
    public static double speed2kinetic(double speed, double mass) {
        return 0.5 * mass * Math.pow(speed, 2);
    }

    public static double kinetic2Celerity(double kineticEnergy, double mass) {
        return Math.sqrt(2 * (kineticEnergy / mass));
    }

    public static final double GRAVITATIONNAL_ACCELERATION = 9.81;

    public static void apply(Ball ball, double deltaT) {


        Point2D celerity = ball.getCelerity();
        Point2D coordinates = ball.getCoordinates();
        double tau = ball.mass / ball.friction;
        double g = GRAVITATIONNAL_ACCELERATION;


        coordinates = new Point2D(
                -tau * (celerity.getX()) * Math.exp(-deltaT / tau) + coordinates.getX() + tau * (celerity.getX()),
                -tau * (celerity.getY() + tau * g) * Math.exp(-deltaT / tau) - tau * g * deltaT + coordinates.getY() + tau * (celerity.getY() + tau * g));
        celerity = new Point2D(
                celerity.getX() * Math.exp(-deltaT / tau),
                (celerity.getY() + tau * g) * Math.exp(-deltaT / tau) - tau * g);

        ball.setCelerity(celerity);
        ball.setCoordinates(coordinates);
    }


}