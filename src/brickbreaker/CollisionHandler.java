package brickbreaker;

import java.util.ArrayList;
import java.util.Iterator;

public class CollisionHandler {
    private long lastCollisionTime = 0;
    private static final long COLLISION_COOLDOWN = 100;

    public void checkCollisionsWithBase(Base base, Ball ball) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastCollisionTime) <= COLLISION_COOLDOWN) return;

        if (isTopCollision(base, ball)) {
            handleTopCollision(ball);
        } else if (isLeftCollision(base, ball)) {
            handleSideCollision(ball, true);
        } else if (isRightCollision(base, ball)) {
            handleSideCollision(ball, false);
        }
    }

    public Brick checkCollisionsWithBricks(ArrayList<Brick> bricks, Ball ball) {
        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            Brick brick = iterator.next();
            if (isBrickCollision(brick, ball)) {
                iterator.remove();
                adjustBallVelocity(ball, brick);
                return brick;
            }
        }
        return null;
    }

    private boolean isTopCollision(Base base, Ball ball) {
        return ball.getX() >= base.getX() && ball.getX() <= base.getX() + base.getBASE_WIDTH() &&
                ball.getY() + ball.getBALL_HEIGHT() >= base.getY() &&
                ball.getY() + ball.getBALL_HEIGHT() <= base.getY() + base.getBASE_HEIGHT();
    }

    private boolean isLeftCollision(Base base, Ball ball) {
        return ball.getX() + ball.getBALL_WIDTH() >= base.getX() &&
                ball.getX() + ball.getBALL_WIDTH() <= base.getX() + base.getBASE_WIDTH() / 4 &&
                ball.getY() >= base.getY() && ball.getY() <= base.getY() + base.getBASE_HEIGHT();
    }

    private boolean isRightCollision(Base base, Ball ball) {
        return ball.getX() <= base.getX() + base.getBASE_WIDTH() &&
                ball.getX() >= base.getX() + base.getBASE_WIDTH() - base.getBASE_WIDTH() / 4 &&
                ball.getY() + ball.getBALL_HEIGHT() >= base.getY() &&
                ball.getY() + ball.getBALL_HEIGHT() <= base.getY() + base.getBASE_HEIGHT();
    }

    private void handleTopCollision(Ball ball) {
        ball.setYVelocity(-ball.getYVelocity());
        if (ball.getX() > 400) {
            ball.setXVelocity(-ball.getXVelocity());
        }
        lastCollisionTime = System.currentTimeMillis();
    }

    private void handleSideCollision(Ball ball, boolean isLeft) {
        ball.setXVelocity(-ball.getXVelocity());
        ball.setYVelocity(-ball.getYVelocity());
        lastCollisionTime = System.currentTimeMillis();
    }

    private boolean isBrickCollision(Brick brick, Ball ball) {
        java.awt.Rectangle bounds = brick.getBounds();
        return (ball.getX() >= bounds.x && ball.getX() <= bounds.x + bounds.width &&
                ball.getY() <= bounds.y + bounds.height && ball.getY() >= bounds.y) ||
                (ball.getX() + ball.getBALL_WIDTH() >= bounds.x && ball.getX() + ball.getBALL_WIDTH() <= bounds.x + 5 &&
                        ball.getY() > bounds.y && ball.getY() < bounds.y + bounds.height) ||
                (ball.getX() <= bounds.x + bounds.width && ball.getY() >= bounds.y &&
                        ball.getX() >= bounds.x && ball.getY() < bounds.y + bounds.height) ||
                (ball.getX() >= bounds.x && ball.getX() <= bounds.x + bounds.width &&
                        ball.getY() + ball.getBALL_HEIGHT() >= bounds.y && ball.getY() + ball.getBALL_HEIGHT() <= bounds.y + bounds.height);
    }

    private void adjustBallVelocity(Ball ball, Brick brick) {
        java.awt.Rectangle bounds = brick.getBounds();
        if (ball.getY() <= bounds.y + bounds.height && ball.getY() >= bounds.y) {
            ball.setYVelocity(-ball.getYVelocity());
        } else if (ball.getX() + ball.getBALL_WIDTH() >= bounds.x && ball.getX() + ball.getBALL_WIDTH() <= bounds.x + 5) {
            ball.setXVelocity(-ball.getXVelocity());
        } else if (ball.getX() <= bounds.x + bounds.width && ball.getX() >= bounds.x) {
            ball.setXVelocity(-ball.getXVelocity());
        } else {
            ball.setYVelocity(-ball.getYVelocity());
        }
    }
}
// New class to handle collision logic