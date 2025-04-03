package brickbreaker;

import javax.swing.*;

public class GameFrame extends JFrame {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 500;
    private final Settings gameSettings;

    public GameFrame() {
        gameSettings = Settings.getInstance();
        initializeFrame();
        if (gameSettings.isBackgroundMusicOn()) {
            playBackgroundMusic();
        }
    }

    private void initializeFrame() {
        setTitle("Brick Breaker");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("assets/icon.png").getImage());
        add(new GamePanel());
    }

    private void playBackgroundMusic() {
        new Thread(AudioPlayback::playMusic).start();
    }
}
// Refactors Applied:
// 1. Extract Method: Separated frame initialization and music playback