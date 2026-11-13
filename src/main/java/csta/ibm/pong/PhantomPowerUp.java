/*
* The Power up class
* */

package csta.ibm.pong;

import java.awt.*;

/**
 * <p>the PhantomPowerUp class represents the entities that spawns phantom balls when a normal ball collides with
 * it. The hitbox of the power up object is triggered by its visibility</p>
 *
 * @author Cary
 * @since 2026-11-12
 * */
public class PhantomPowerUp extends GameObject {
    /**
     * Add a ball phantom to the game. The phantom will bounce off the paddles once and then disappear.
     * The phantom should have same attributes as the trigger ball except that phantom's dx is smaller than the ball's,
     * and opposite dy comparing to the trigger ball
     *
     * @param triggerBall the ball who triggers this power up (Should be the main ball)
     * */
    public Ball addBallPhantom(Ball triggerBall) {
        Ball phantom = new Ball();
        // Same size as trigger ball
        phantom.setSize(triggerBall.getSize());
        // Spawn at the trigger ball's location
        phantom.setX(triggerBall.getX());
        phantom.setY(triggerBall.getY());
        // Set the velocities for the phantom
        int phantomDx = (int) (triggerBall.getDX() * 0.6);  // Slower than the triggering ball
        int phantomDy = -triggerBall.getDY();  // Opposite vertical direction of the triggering ball
        phantom.setVelocity(phantomDx, phantomDy);  // Slower than normal ball
        phantom.setColor(Color.GRAY);  // Different color to distinguish from normal ball
        // Return the phantom to the caller
        return phantom;
    }

    /**
     * Override the original collides so that it will only continue checking hitbox when the power up object is
     * visible
     * */
    @Override
    public boolean collides(GameObject o)  {
        return (isVisible() && super.collides(o));
    }

    @Override
    public void act() {

    }
}
