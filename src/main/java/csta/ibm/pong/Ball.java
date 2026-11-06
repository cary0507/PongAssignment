package csta.ibm.pong;

public class Ball extends GameObject {
    private int dx;
    private int dy;
    private int maxDy;

    public void setVelocity(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        maxDy = 4;
    }

    public void bouncePaddle(int paddleDY) {
        dx = -dx;
        int newSpeed = Math.min(Math.abs(paddleDY), maxDy);
        if (paddleDY < 0) newSpeed = -newSpeed;
        dy += newSpeed;
    }

    public void bounceY() {
        dy = -dy;
    }

    @Override
    public void act() {
        // Get the next position
        int nexX = getX() + dx;
        int nexY = getY() + dy;
        // Move it to the next position
        setX(nexX);
        setY(nexY);
    }
}