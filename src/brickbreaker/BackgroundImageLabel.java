package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundImageLabel extends JLabel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;

    public BackgroundImageLabel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBounds(0, 0, WIDTH, HEIGHT);
        loadBackgroundImage();
    }

    // Extract Method: Separate image loading logic
    private void loadBackgroundImage() {
        try {
            Image backgroundImage = ImageIO.read(new File("assets/bg.jpg"));
            setIcon(new ImageIcon(backgroundImage));
        } catch (IOException ex) {
            System.out.println("[ERROR] Unable to load background image");
        }
    }
}
// Refactors Applied:
// 1. Extract Method: Separated image loading for better readability