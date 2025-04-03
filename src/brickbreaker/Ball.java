package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball extends JButton {
    private static final int BALL_WIDTH = 20;
    private static final int BALL_HEIGHT = 15;
    private int xVelocity = 4;
    private int yVelocity = -4;
    private int x = 325;
    private int y = 350;

    public Ball() {
        initializeComponent();
        loadBallImage();
    }

    // Extract Method: Separate initialization
    private void initializeComponent() {
        setPreferredSize(new Dimension(BALL_WIDTH, BALL_HEIGHT));
        setBounds(x, y, BALL_WIDTH, BALL_HEIGHT);
        setBackground(new Color(255, 255, 255, 0));
        setBorderPainted(false);
        setFocusable(false);
    }

    // Extract Method: Separate image loading
    private void loadBallImage() {
        try {
            Image ballImage = ImageIO.read(new File("assets/ball.png"))
                    .getScaledInstance(BALL_WIDTH, BALL_HEIGHT, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(ballImage));
        } catch (IOException ex) {
            System.out.println("[ERROR] Unable to load ball image");
        }
    }

    public void move(MovementHandler movementHandler) {
        movementHandler.moveBall(this);
        setBounds(x, y, BALL_WIDTH, BALL_HEIGHT);
    }

    // Getters and setters
    public int getXVelocity() { return xVelocity; }
    public void setXVelocity(int xVelocity) { this.xVelocity = xVelocity; }
    public int getYVelocity() { return yVelocity; }
    public void setYVelocity(int yVelocity) { this.yVelocity = yVelocity; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getBALL_WIDTH() { return BALL_WIDTH; }
    public int getBALL_HEIGHT() { return BALL_HEIGHT; }
}
// Refactors Applied:
// 1. Extract Method: Separated initialization and image loading
// 2. Move Method: Delegated movement to MovementHandler (Feature Envy mitigation)