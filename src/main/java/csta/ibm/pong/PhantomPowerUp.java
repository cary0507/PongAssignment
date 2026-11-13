/*
* The Power up class
* */

package csta.ibm.pong;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>the PhantomPowerUp class represents the entities that spawns phantom balls when a normal ball collides with
 * it. The hitbox of the power up object is triggered by its visibility</p>
 *
 * @author Cary
 * @since 2026-11-12
 * */
public class PhantomPowerUp extends GameObject {
    private BufferedImage img;
    private int appearChance = 800;  // 1 in 800 chance every tick to make the power up appear
    private int lowX;
    private int lowY;
    private int highX;
    private int highY;

    /**
     * On default, the power  up is not visible
     * */
    public PhantomPowerUp() {
        setVisible(false);
    }

    public void setPossibleZone(int lowX, int lowY, int highX, int highY) {
        this.lowX = lowX;
        this.lowY = lowY;
        this.highX = highX;
        this.highY = highY;
        System.out.println("Power up possible zone set to: (" + lowX + ", " + lowY + ") to (" + highX + ", " + highY + ")");
    }

    /**
     * Setter method to set the image of the power up
     * @param img the buffered image
     * */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

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
        // Reset visibility of the power up after being triggered
        setVisible(false);
        // Return the phantom to the caller
        return phantom;
    }

    /**
     * Override the paint method from GameObject to draw the image instead of the shapes
     * */
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;  // Type cast to g2d
        if (img == null) return;
        g2d.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Override the original collides so that it will only continue checking hitbox when the power up object is
     * visible
     * */
    @Override
    public boolean collides(GameObject o)  {
        return (isVisible() && super.collides(o));
    }

    /***
     * Override the act method to make the power up appear randomly on the screen when it is not visible.
     * The power up will appear at a random location within the specified zone
     * */
    @Override
    public void act() {
        setX(10); setY(10);
        setVisible(true);
        if (!isVisible()) {  // Trying to appear on the screen
            // (int) (Math.random() * (max - min + 1)) + min => (int) (Math.random() *  ̶(̶appearChance  ̶(̶-̶1̶)̶ ̶+̶ ̶1̶ ̶)̶) + 1
            int roll = (int) (Math.random() * appearChance) + 1;
            if (roll == 1) {  // Successfully rolled
                // Random location within a box
                int newX = (int) (Math.random() * (highX - lowX + 1)) + lowX;
                int newY = (int) (Math.random() * (highY - lowY + 1)) + lowY;
                System.out.println("Power up appeared at: (" + newX + ", " + newY + ")");
                setX(newX); setY(newY);
                setVisible(true);  // Appear on the screen
            }
        }
    }
}
