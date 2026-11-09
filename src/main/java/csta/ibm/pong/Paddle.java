/*
* Author: Cary
* */

package csta.ibm.pong;

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

    @Override
    public void act() {
        int newY = getY() + dy;
        setY(newY);
    }
}
