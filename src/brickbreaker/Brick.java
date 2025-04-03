package brickbreaker;

import javax.swing.*;
import java.awt.*;

public class Brick extends JButton {
    private static final int BRICK_WIDTH = 70;
    private static final int BRICK_HEIGHT = 30;

    public Brick(ImageIcon brickImage) {
        initializeComponent(brickImage);
    }

    private void initializeComponent(ImageIcon brickImage) {
        setPreferredSize(new Dimension(BRICK_WIDTH, BRICK_HEIGHT));
        setBorderPainted(true);
        setFocusable(false);
        if (brickImage != null) {
            setIcon(brickImage);
        } else {
            setBackground(Color.red);
        }
    }

    public int getBRICK_WIDTH() { return BRICK_WIDTH; }
    public int getBRICK_HEIGHT() { return BRICK_HEIGHT; }
}
// Refactors Applied:
// 1. Extract Method: Separated initialization (Anemic Domain Model mitigation)