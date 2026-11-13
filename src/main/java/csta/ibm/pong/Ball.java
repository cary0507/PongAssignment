/*
* Author: Cary
* */

package csta.ibm.pong;

import java.awt.*;

/**
 * <p>A class representing the ball in the Pong game. It extends the GameObject class and handles the ball's movement,
 * velocity, and collision behavior with paddles and walls.</p>
 *
 * @author Cary
 * @since 2026-10-28
 * */
public class Ball extends GameObject {
    private int dx;
    private int dy;
    private int maxDy;
    private final double FRICTION = 0.1;  // Friction coefficient to reduce the ball's vertical velocity when bouncing
    // off the paddle with opposite direction

    /**
     * Set the default horizontal and vertical velocity of the ball
     * @param dx the horizontal velocity of the ball
     * @param dy the vertical velocity of the ball
     * */
    public void setVelocity(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        maxDy = (int) (Math.abs(dx) * 1.2 + 0.5);  // Set the maximum vertical velocity to 1.5x the initial horizontal
        // velocity, then round the result
    }

    /**
     * Get the horizontal velocity of the ball
     * */
    public int getDX() {
        return dx;
    }

    /**
     * Get the vertical velocity of the ball
     * */
    public int getDY() {
        return dy;
    }

    /**
     * Bounce the ball off the paddle and adjust the vertical velocity based on the paddle's vertical velocity
     * If ball and paddle have the same direction for dy, the ball's dy will increase, otherwise it will set ball's
     * dy to the paddle's dy
     * <a href="https://www.mathsisfun.com/algebra/vectors-dot-product.html">Reference for dot product</a>
     * @param paddleDY the vertical velocity of the paddle
     * */
    public void bouncePaddle(int paddleDY) {
        // System.out.println(paddleDY);
        dx = -dx;
        // Dot product of the paddle's vertical velocity and the ball's vertical velocity to determine the new
        // vertical velocity's direction of the ball
        int dotProduct = dy * paddleDY;
        if (dotProduct > 0) {
            // If the ball and paddle are moving in the same direction, increase the ball's vertical velocity
            int resultant = Math.abs(dy) + Math.abs(paddleDY);
            resultant = (int) (resultant * (1 - FRICTION));  // Apply friction to the resultant velocity
            int newDy = Math.min(maxDy, resultant);
            int direction = paddleDY / Math.abs(paddleDY);  // Direction of unit vectors
            dy = direction * newDy;
        } else if (dotProduct == 0) {
            // One of ball or paddle are not moving
            dy += paddleDY;
        } else {
            // If the ball and paddle are moving in opposite directions, set the ball's vertical velocity to the
            // paddle's vertical velocity
            dy = (int) (paddleDY * (1 - FRICTION));
        }
    }

    /**
     * Bounce the ball of the wall by reversing the vertical velocity
     * */
    public void bounceY() {
        dy = -dy;
    }

    /**
     * During every game tick, update the ball's position according to its current x and y velocities
     * */
    @Override
    public void act() {
        // Get the next position
        int newX = getX() + dx;
        int newY = getY() + dy;
        // Move it to the next position
        setX(newX);
        setY(newY);
    }

    /**
     * Override the paint method to draw the ball as a filled circle
     * Referencing examples from Google Classroom
     * */
    @Override
    public void paint(Graphics g) {
        g.setColor(this.c);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), getWidth(), getHeight());
    }

//    /**
//     * Override the setColor method to set the color of the ball
//     * Referencing examples from Google Classroom
//     * */
//    @Override
//    public void setColor(Color c) {
//        this.c = c;
//    }
}