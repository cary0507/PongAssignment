package csta.ibm.pong;

public class Paddle extends GameObject {
    // Separate ySpeed and dy so that when the ball hits the paddle, know the magnitude for paddle's current y change
    private int ySpeed;
    private int dy;

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getDY() {
        return dy;
    }

    public void moveUp() {
        dy = -ySpeed;
    }
    public void moveDown() {
        dy = ySpeed;
    }

    public void atRest() {
        dy = 0;
    }

    @Override
    public void act() {
        int newY = getY() + dy;
        setY(newY);
    }
}
