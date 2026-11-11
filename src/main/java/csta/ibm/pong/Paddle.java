/*
* The paddle class
* */

package csta.ibm.pong;

/**
 * The Paddle class of the Pong game. It extends the GameObject class and represents a player's paddle,
 * handling its movement and speed.
 *
 * @author Cary
 * @since 2026-10-28
 * */
public class Paddle extends GameObject {
    // Separate ySpeed and dy so that when the ball hits the paddle, know the magnitude for paddle's current y change
    private int ySpeed;
    private int dy;

    /**
     * Sets the speed of the paddle in the y direction
     * @param ySpeed the speed of the paddle in the y direction
     * */
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * Gets the velocity of the paddle in the y direction
     * @return the velocity of the paddle in the y direction
     * */
    public int getDY() {
        return dy;
    }

    /**
     * Move the paddle up and update the direction of vertical velocity vy
     * */
    public void moveUp() {
        dy = -ySpeed;
    }

    /**
     * Move the paddle down and update the direction of vertical velocity vy
     * */
    public void moveDown() {
        dy = ySpeed;
    }

    /**
     * Update the paddle's vertical velocity to 0 when the paddle is at rest
     * */
    public void atRest() {
        dy = 0;
    }

    /**
     * Update the paddle's position based on its current vertical velocity.
     * This method is called during each game tick to move the paddle up or down.
     * */
    @Override
    public void act() {
        int newY = getY() + dy;
        setY(newY);
    }
}
