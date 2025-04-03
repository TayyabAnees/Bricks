package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 500;
    private final Base base;
    private final Ball ball;
    private final ArrayList<Brick> bricks = new ArrayList<>();
    private final BackgroundImageLabel backgroundImageLabel;
    private final Timer timer;
    private final Settings gameSettings;
    private final CollisionHandler collisionHandler;
    private final BrickPatternFactory brickPatternFactory;
    private final MovementHandler movementHandler;
    private int level = 1;

    public GamePanel() {
        gameSettings = Settings.getInstance();
        collisionHandler = new CollisionHandler();
        brickPatternFactory = new BrickPatternFactory();
        movementHandler = new MovementHandler(PANEL_WIDTH, PANEL_HEIGHT);
        base = new Base();
        ball = new Ball();
        backgroundImageLabel = new BackgroundImageLabel();
        initializePanel();
        timer = new Timer(16, this);
        timer.start();
    }

    private void initializePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(new BorderLayout());
        backgroundImageLabel.add(ball);
        backgroundImageLabel.add(base);
        bricks.addAll(brickPatternFactory.createBrickPattern(level));
        bricks.forEach(backgroundImageLabel::add);
        add(backgroundImageLabel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        movementHandler.moveGameObjects(base, ball);
        handleCollisions();
        updateLevel();
        checkGameOver();
    }

    private void handleCollisions() {
        collisionHandler.checkCollisionsWithBase(base, ball);
        Brick collidedBrick = collisionHandler.checkCollisionsWithBricks(bricks, ball);
        if (collidedBrick != null) {
            if (gameSettings.isSoundEffectsOn()) {
                AudioPlayback.playSoundEffect();
            }
            backgroundImageLabel.remove(collidedBrick);
        }
    }

    private void updateLevel() {
        if (bricks.isEmpty()) {
            level++;
            bricks.addAll(brickPatternFactory.createBrickPattern(level));
            bricks.forEach(backgroundImageLabel::add);
        }
    }

    private void checkGameOver() {
        if (ball.getY() >= PANEL_HEIGHT) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Gameover");
        }
    }
}
// Refactors Applied:
// 1. Extract Class: Moved collision logic to CollisionHandler (Feature Envy, Shotgun Surgery)
// 2. Extract Class: Moved brick pattern creation to BrickPatternFactory (Duplicated Code, Combinatorial Explosion)
// 3. Extract Class: Moved movement logic to MovementHandler (Feature Envy)
// 4. Extract Method: Split actionPerformed into smaller methods (Conditional Complexity)