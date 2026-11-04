import csta.ibm.pong.GameObject;

public class Ball extends GameObject {
    private int dx;
    private int dy;

    public void setVelocity(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void bouncePaddle(int paddleDY) {
        dx = -dx;
        dy += paddleDY;  // Sliding
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