package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Base extends JButton implements KeyListener {
    private static final int BASE_WIDTH = 130;
    private static final int BASE_HEIGHT = 15;
    private int x = 325;
    private int y = 400;
    private boolean isRightKeyPressed = false;
    private boolean isLeftKeyPressed = false;

    public boolean isLeftKeyPressed() {
        return isLeftKeyPressed;
    }

    public boolean isRightKeyPressed() {
        return isRightKeyPressed;
    }

    public Base() {
        initializeComponent();
        loadBaseImage();
    }

    private void initializeComponent() {
        setPreferredSize(new Dimension(BASE_WIDTH, BASE_HEIGHT));
        setBounds(x, y, BASE_WIDTH, BASE_HEIGHT);
        setBorderPainted(false);
        setFocusable(true);
        addKeyListener(this);
    }

    private void loadBaseImage() {
        try {
            Image baseImage = ImageIO.read(new File("assets/base2.jpg"))
                    .getScaledInstance(BASE_WIDTH, BASE_HEIGHT, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(baseImage));
        } catch (IOException ex) {
            System.out.println("[ERROR] Unable to load base image");
        }
    }

    public void move(MovementHandler movementHandler) {
        movementHandler.moveBase(this, isLeftKeyPressed, isRightKeyPressed);
        setBounds(x, y, BASE_WIDTH, BASE_HEIGHT);
    }

    // Getters and setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getBASE_WIDTH() { return BASE_WIDTH; }
    public int getBASE_HEIGHT() { return BASE_HEIGHT; }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) isLeftKeyPressed = true;
        if (code == KeyEvent.VK_RIGHT) isRightKeyPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) isLeftKeyPressed = false;
        if (code == KeyEvent.VK_RIGHT) isRightKeyPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

}
// Refactors Applied:
// 1. Extract Method: Separated initialization and image loading
// 2. Move Method: Delegated movement to MovementHandler (Feature En huntingtonlibrary.org/wp-content/uploads/2015/03/IMG_1088-1024x768.jpgFeature Envy mitigation)