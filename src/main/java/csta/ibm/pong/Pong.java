/*
* Author: Cary
* Description: A Pong game where 2 players can play on the same keyboard.
* Player wins if the other player's paddle missed the ball
* */

package csta.ibm.pong;  // This can access package-private classes like _WinDialog

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main class for the Pong game. It extends the Game class and implements the game logic, including player controls,
 * ball movement, collision detection, and score tracking.
 * */
public class Pong extends Game {
    /* Have to initialize objects outside of setup because setup will be also used as a reset method for now
    * If initialize inside setup, everytime the game reset it will create new objects.
    * */
    private Ball ball;
    private Paddle player1;  // Left paddle
    private Paddle player2;  // Right paddle
    private JLabel scores;  // Display score

    private int CENTER_X;
    private int CENTER_Y;

    // First player reaching 3 scores wins
    private final int WIN_SCORE = 3;
    private int p1Score = 0;
    private int p2Score = 0;

    public Pong() {
        super();
        super.setResizable(false);  // Disable resizing
    }

    /**
     * Quick way to calculate the left or top position from a pivot
     * */
    private static int pivotOffset(int pivot, int size) {
        return pivot - size / 2;
    }

    /**
     * Reset the ball to the center of the screen, and reset the paddles to the edges of the screen
     * */
    public void resetPosition() {
        // Ball setup
        int ballRadius = 10;
        int ballLeft = pivotOffset(CENTER_X, ballRadius);  // Pivot ball to the screen center
        int ballTop = pivotOffset(CENTER_Y, ballRadius);
        ball.setSize(ballRadius, ballRadius);
        ball.setX(ballLeft); ball.setY(ballTop);
        ball.setVelocity(-5, 0);

        // Paddles setup
        int initialPaddleSpeed = 5;
        int paddleWidth = 10;
        int paddleHeight = 100;
        int paddleTop = pivotOffset(CENTER_Y, paddleHeight);
        int paddleXOffset = 20;  // Distance away from screen bounds
        // Player 1
        player1.setSize(paddleWidth, paddleHeight);
        player1.setX(paddleXOffset); player1.setY(paddleTop);
        player1.setYSpeed(initialPaddleSpeed);
        // Player 2
        int rPaddleLeft = getFieldWidth() - paddleWidth - paddleXOffset;
        player2.setSize(paddleWidth, paddleHeight);
        player2.setX(rPaddleLeft); player2.setY(paddleTop);
        player2.setYSpeed(initialPaddleSpeed);
    }

    /**
     * Override p1Wins so it quits the game after pressing the OK button
     * */
    @Override
    public void p1Wins() {
        _WinDialog d = new _WinDialog(this, "Player 1 Wins!");
        d.ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);  // When OK button pressed, quit the game
                setVisible(false);
            }
        });
        d.setVisible(true);
    }

    /**
     * Override p2Wins so it quits the game after pressing the OK button
     * */
    @Override
    public void p2Wins() {
        _WinDialog d = new _WinDialog(this, "Player 2 Wins!");
        d.ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                setVisible(false);
            }
        });
        d.setVisible(true);
    }

    /**
     * Initialize all objects and call resetPosition to set the initial positions of the ball and paddles.
     * Also sets up the score display.
     * */
    @Override
    public void setup() {
        setDelay(10);
        CENTER_X = getFieldWidth() / 2;
        CENTER_Y = getFieldHeight() / 2;
        // Text setup
        scores = new JLabel();
        int scoresWidth = 160;
        int scoresHeight = 50;
        int scoreLeft = pivotOffset(CENTER_X, scoresWidth);
        scores.setForeground(Color.white);
        scores.setBounds(scoreLeft, 0, scoresWidth, scoresHeight);
        scores.setHorizontalAlignment(SwingConstants.CENTER);
        // Initialize game objects
        ball = new Ball();
        player1 = new Paddle();
        player2 = new Paddle();
        // Setup locations
        resetPosition();
        // Add to Pong
        add(scores);
        add(ball);
        add(player1);
        add(player2);
    }

    /**
     * The main loop of the game. It updates the score display, checks for collisions and out-of-bounds conditions,
     * handles player input, and updates the positions of the ball and paddles accordingly.
     * */
    @Override
    public void act() {
        // Display text and repaint the canva
        scores.setText("Player 1   " + p1Score + " | " + p2Score + "   Player 2");
        repaint();
        // Top & bottom bounds
        if (ball.getY() <= 0 || ball.getY() + ball.getHeight() >= getFieldHeight()) {
            ball.bounceY();
        }
        // Out of left bound: player 2 gets score
        if (ball.getX() <= 0) {
            p2Score++;
            resetPosition();  // Reset game board
        }
        // Out of right bound: player 1 gets score
        if (ball.getX() + ball.getWidth() >= getFieldWidth()) {
            p1Score++;
            resetPosition();
        }

        // Check if any player won
        if (p1Score >= WIN_SCORE) {
            stopGame();  // Pause the game
            p1Wins();
        }
        if (p2Score >= WIN_SCORE) {
            stopGame();
            p2Wins();
        }

        // Ball colliding with paddle
        if (ball.collides(player1)) {
            ball.bouncePaddle(player1.getDY());
        }
        if (ball.collides(player2)) {
            ball.bouncePaddle(player2.getDY());
        }

        // Player 1 keypress
        if (ZKeyPressed()) {
            if (player1.getY() < 0) {  // Stop moving and restrain to the top of the screen
                player1.setY(0);
            } else {
                player1.moveUp();
            }
        } else if (XKeyPressed()) {
            if (player1.getY() + player1.getHeight() > getFieldHeight()) {  // Restrain to the bottom
                player1.setY(getFieldHeight() - player1.getHeight());
            } else  {
                player1.moveDown();
            }
        } else {  // None of z or x key is pressed
            player1.atRest();
        }
        // Player 2 keypress
        if (NKeyPressed()) {
            if (player2.getY() < 0) {
                player2.setY(0);
            } else {
                player2.moveUp();
            }
        } else if (MKeyPressed()) {
            if (player2.getY() + player2.getHeight() > getFieldHeight()) {
                player2.setY(getFieldHeight() - player2.getHeight());
            } else   {
                player2.moveDown();
            }
        } else {
            player2.atRest();
        }
    }

    public static void main(String[] args) {
        Pong p = new Pong();
        p.resize(800, 600);
        p.setVisible(true);
        p.initComponents();
    }
}
